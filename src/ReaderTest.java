/*
 *author: Guruprem Rajpal
 *date: 23 feb 2021
 *Description: This assignment is to create the Reader.java class that will be used throughout the rest of the project.
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {
    Reader reader;
    int cardnumber;
    String name;
    String phonenumber;
    String equal1;
    String equal2;
    Book book;
    String bookname;


    @BeforeEach
    void setUp() {
        System.out.println("Processing setup");
        cardnumber= 1;
        name= "Drew Clinkenbeard";
        phonenumber= "831-582-4007";
        bookname="Hello world";
        reader= new Reader(1234,"Guruprem","808-536-4000");
        book = new Book("1337", "Headfirst Java", "education",1337,"Booch", LocalDate.of(2020,02,27));
    }

    @AfterEach
    void tearDown() {
        System.out.println("Processing teardown");
        reader = null;
        cardnumber= 0;
        name = null;
        phonenumber= null;
        equal1=null;
        equal2= null;
        book= null;
        bookname= null;
    }

    @Test
    void getCardNumber() {
        assertNotEquals(cardnumber, reader.getCardNumber());
    }

    @Test
    void setCardNumber() {
        assertNotEquals(cardnumber, reader.getCardNumber());
        reader.setCardNumber(100);
        assertNotEquals(cardnumber, reader.getCardNumber());
    }

    @Test
    void getName() {
        assertNotEquals(name, reader.getName());
    }

    @Test
    void setName() {
        assertNotEquals(name, reader.getName());
        reader.setName("Guru");
        assertNotEquals(name, reader.getName());
    }

    @Test
    void getPhone() {
        assertNotEquals(phonenumber, reader.getPhone());
    }

    @Test
    void setPhone() {
        assertNotEquals(phonenumber, reader.getPhone());
        reader.setPhone("808-536-4000");
        assertNotEquals(phonenumber, reader.getPhone());
    }

    @Test
    void getBooks() {
        assertNotEquals(bookname, reader.getBooks());
    }

    @Test
    void setBooks() {
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
    void addBook() {
        assertEquals(reader.addBook(book), Code.SUCCESS);
        assertNotEquals(reader.addBook(book), Code.SUCCESS);
        assertEquals(reader.addBook(book), Code.BOOK_ALREADY_CHECKED_OUT_ERROR);
    }

    @Test
    void removeBook() {
        assertEquals(reader.removeBook(book), Code.READER_DOESNT_HAVE_BOOK_ERROR);
        reader.addBook(book);
        assertEquals(reader.removeBook(book), Code.SUCCESS);
    }

    @Test
    void getBookCount() {
        assertEquals(reader.getBookCount(),0);
        reader.addBook(book);
        assertEquals(reader.getBookCount(),1);
        reader.removeBook(book);
        assertEquals(reader.getBookCount(),0);
    }

    @Test
    void hasBook() {
        assertFalse(reader.hasBook(book));
        reader.addBook(book);
        assertTrue(reader.hasBook(book));
    }

    @Test
    void main() {
    }
}
