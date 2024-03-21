package org.example.collections.libraryService;

import org.example.collections.entity.Book;
import org.example.collections.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService libraryService;
    private List<Book> bookList;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        bookList = Arrays.asList(
                new Book("book1", "author1", 1200, 1L),
                new Book("book2", "author2", 2000, 2L),
                new Book("book3", "author3", 1992, 3L),
                new Book("book4", "author4", 1951, 4L),
                new Book("book5", "author5", 2012, 5L)
        );

        userList = Arrays.asList(
                new User("user1", 1200, 1L),
                new User("user2", 2000, 2L),
                new User("user3", 1992, 3L),
                new User("user4", 1951, 4L),
                new User("user5", 2012, 5L)
        );

        libraryService = new LibraryService(userList, bookList);
    }

    @Test
    void getAllBooksReturnsAllExistingBooks() {
        assertEquals(bookList, libraryService.getAllBooks());
    }

    @Test
    void testTakeBookDoesNotThrowException() {
        User user = userList.get(0);
        Book book = bookList.get(0);

        assertDoesNotThrow(() -> libraryService.takeBook(user.userId(), book.bookId()));
    }

    @Test
    void testTakeBookThrowsException() {
        User user = userList.get(0);

        assertThrows(IllegalArgumentException.class, () -> libraryService.takeBook(user.userId(), 10L));
    }

    @Test
    void testTakeBookAlreadyTakenByTheSameUser() {

        User user = userList.get(0);
        Book book = bookList.get(0);

        libraryService.takeBook(user.userId(), book.bookId());

        assertThrows(IllegalArgumentException.class, () -> libraryService.takeBook(user.userId(), book.bookId()));
    }

    @Test
    void testGetUserBooksReturnsEmptyListWhenNoBooksAreTaken() {
        User user = userList.get(0);
        assertTrue(libraryService.getUserBooks(user.userId()).isEmpty());
    }

    @Test
    void testGetUserBooksReturnsAllBooksTakenByUser() {
        Book book = bookList.get(0);
        User user = userList.get(0);

        libraryService.takeBook(user.userId(), book.bookId());

        List<Book> userBooks = libraryService.getUserBooks(user.userId());

        assertEquals(1, userBooks.size());

        assertEquals(book, userBooks.get(0));
    }

    @Test
    void testGetAvailableBooks() {
        User user = userList.get(0);
        Book book = bookList.get(0);

        libraryService.takeBook(user.userId(), book.bookId());
        List<Book> availableBooks = libraryService.getAvailableBooks();
        assertEquals(4, availableBooks.size());

        assertFalse(availableBooks.contains(book));
    }

    @Test
    void returnBook() {
        User user = userList.get(0);
        Book book = bookList.get(0);

        libraryService.takeBook(user.userId(), book.bookId());

        assertEquals(4, libraryService.getAvailableBooks().size());

        libraryService.returnBook(user.userId(), book.bookId());

        assertEquals(5, libraryService.getAvailableBooks().size());
    }
}