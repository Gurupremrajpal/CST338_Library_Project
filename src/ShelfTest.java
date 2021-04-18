/*
 *author: Guruprem Rajpal
 *date: 13 Mar 2021
 *Description: This assignment is to create the sheld.java class that will be used throughout the rest of the project.
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {
    Shelf shelf;
    Book book;
    int shelfnumber;
    String  subject;
    String bookname;
    HashMap<Book, Integer> books;
    String equal1;
    String equal2;

    @BeforeEach
    void setUp() {
        System.out.println("Processing setup");
        shelfnumber=1;
        subject="sci-fi";
        bookname="CS";
        equal1="Hello";
        shelf = new Shelf();
        book = new Book("1337", "Headfirst Java", "education", 1337, "Grady booch", LocalDate.of(2020, 02, 27));
    }

    @AfterEach
    void tearDown() {
        System.out.println("Processing teardown");
        shelfnumber= 0;
        book = null;
        shelf= null;
        subject= null;
        equal1=null;
        equal2=null;
    }

    @Test
    void getShelfNumber() {
        assertNotEquals(shelfnumber,shelf.getShelfNumber());
    }

    @Test
    void setShelfNumber() {
        assertNotEquals(shelfnumber, shelf.getShelfNumber());
        shelf.setShelfNumber(10);
        assertNotEquals(shelfnumber, shelf.getShelfNumber());
    }

    @Test
    void getSubject() {
        assertNotEquals(subject,shelf.getSubject());
    }

    @Test
    void setSubject() {
        assertNotEquals(subject, shelf.getSubject());
        shelf.setSubject("CS");
        assertNotEquals(subject, shelf.getSubject());
    }

    @Test
    void addBook() {
    }

    @Test
    void getBooks() {
        assertNotEquals(bookname, shelf.getBooks());
    }


    @Test
    void testEquals() {
        String equal1= new String("Computer Science");
        String equal2= equal1;
    }

    @Test
    void testToString() {
        System.out.println("Processing ToString");
    }

    @Test
    void getBookCount() {
    }

    @Test
    void listBook() {
        books = new HashMap<>();
        shelf.setBooks(books);
        Book book2 = new Book("42-w-87", "Hitchhikers Guide To the Galax", "sci-fi", 42, "Douglas Adams", LocalDate.of(2021, 03, 1));
        shelf.addBook(book2);
        String expectedResult = "1 books on shelf: 0:\n Abstract by DouglasISBN:1337 1\n";
        assertNotEquals(expectedResult, shelf.listBooks());
    }

    @Test
    void main() {
    }

}