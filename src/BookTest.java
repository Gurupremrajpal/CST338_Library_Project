/*
 *author: Guruprem Rajpal
 *date: 18 feb 2021
 *Description: This assignment is to create the Book.java class that will be used throughout the rest of the project.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;

class BookTest {

    Book book;
    String isbn;
    String title;
    String subject;
    int pagecount;
    String author;
    LocalDate time;
    LocalDate date;
    String name1;
    String name2;

    @BeforeEach
    void setUp() {
        System.out.println("Processing setup");
        isbn= "1337";
        title= "Headfirst Java";
        subject="education";
        pagecount= 1337;
        author = "Grady Booch";
        time=  LocalDate.of(2021,02,19);
        book = new Book("","","",0,"",null);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Processing teardown");
        book = null;
        isbn = null;
        title= null;
        subject= null;
        pagecount= 0;
        author= null;
        time = null;
        name1= null;
        name2= null;
    }

    @Test
    void getISBN() {
        assertNotEquals(isbn, book.getISBN());
    }

    @Test
    void setISBN() {
        assertNotEquals(isbn, book.getISBN());
        book.setISBN("1234");
        assertNotEquals(isbn, book.getISBN());
    }

    @Test
    void getTitle() {
        assertNotEquals(title, book.getTitle());
    }

    @Test
    void setTitle() {
        assertNotEquals(title, book.getTitle());
        book.setTitle("Computer Science");
        assertNotEquals(title, book.getTitle());
    }

    @Test
    void getSubject() {
        assertNotEquals(subject, book.getSubject());
    }

    @Test
    void setSubject() {
        assertNotEquals(subject, book.getSubject());
        book.setSubject("Computer");
        assertNotEquals(title, book.getSubject());
    }

    @Test
    void getPageCount() {
        assertNotEquals(pagecount, book.getPageCount());
    }

    @Test
    void setPageCount() {
        assertNotEquals(pagecount, book.getPageCount());
        book.setPageCount(999);
        assertNotEquals(pagecount, book.getPageCount());
    }

    @Test
    void getAuthor() {
        assertNotEquals(author, book.getAuthor());
    }

    @Test
    void setAuthor() {
        assertNotEquals(author, book.getAuthor());
        book.setAuthor("Guru");
        assertNotEquals(author, book.getAuthor());
    }

    @Test
    void getDueDate() {
        assertNotEquals(time, book.getDueDate());
    }

    @Test
    void setDueDate() {
        assertNotEquals(time, book.getDueDate());
        book.setDueDate(date);
        assertNotEquals(time, book.getDueDate());
    }


    @Test
    void testEquals() {
        String name1= new String("Firstname");
        String name2= name1;
    }

    @Test
    void testToString() {
        System.out.println("Processing ToString");
    }

    @Test
    void main() {
    }
}