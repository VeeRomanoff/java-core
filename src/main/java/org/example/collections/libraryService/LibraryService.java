package org.example.collections.libraryService;

import org.example.collections.entity.Book;
import org.example.collections.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {

    private final List<User> userList;
    private final List<Book> bookList;
    private Map<Long, List<Long>> userIdToBookMap = new HashMap<>();

    public LibraryService(List<User> userList, List<Book> bookList) {
        this.userList = userList;
        this.bookList = bookList;
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public void takeBook(Long userId, Long bookId) {

        Book bookToTake = bookList.stream()
                .filter(book -> book.bookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("the book with id " + bookId + " does not exist."));

        List<Long> userBooks = userIdToBookMap.computeIfAbsent(userId, k -> new ArrayList<>());

        if (userBooks.contains(bookId)) {
            throw new IllegalArgumentException("the book with id " + bookId + " is already taken by this user.");
        }

        userIdToBookMap.get(userId).add(bookId);
    }

    public List<Book> getUserBooks(Long userId) {
        List<Long> userBookIds = userIdToBookMap.getOrDefault(userId, Collections.emptyList());

        return bookList.stream()
                .filter(book -> userBookIds.contains(book.bookId()))
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks() {

        Set<Long> takenBooks = userIdToBookMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        return bookList.stream()
                .filter(book -> !takenBooks.contains(book.bookId()))
                .collect(Collectors.toList());
    }

    public void returnBook(Long userId, Long bookId) {
        if (!userIdToBookMap.containsKey(userId)) {
            throw new IllegalArgumentException("user didn't take any books");
        }

        List<Long> userBooks = userIdToBookMap.get(userId);
        userIdToBookMap.get(userId).remove(bookId);
    }
}
