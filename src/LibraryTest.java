/*
 *author: Guruprem Rajpal
 *date: 25 March 2021
 *Description: This assignment is to create the Library.java class that will be used throughout the rest of the project.
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    String libraryName;
    int libraryCard;

    List<Reader> readers;
    HashMap<String, Shelf> shelves;
    HashMap<Book, Integer> books;

    String equal1;
    String equal2;


    @BeforeEach
    void setUp() {
        System.out.println("Processing setup");
        libraryCard=1;
        libraryName="NY LIBRARY";
        Library l = new Library(libraryName);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Processing teardown");
        libraryCard= 0;
        libraryName= null;
        equal1=null;
        equal2=null;
    }

    @Test
    void init() {
    }

    @Test
    void testInit() {
    }

    @Test
    void addBook() {
    }

    @Test
    void returnBook() {
    }

    @Test
    void listBooks() {
    }

    @Test
    void getLibraryCardNumber() {
    }

    @Test
    void addReader() {
    }

    @Test
    void removeReader() {
    }

    @Test
    void getReaderByCard() {
    }

    @Test
    void convertInt() {
    }

    @Test
    void listReaders() {
    }

    @Test
    void testListReaders() {
    }

    @Test
    void getShelf() {
    }

    @Test
    void testGetShelf() {
    }

    @Test
    void convertDate() {
    }

//    @Test
//    void addShelf() {
//    }

    @Test
    void testAddShelf() {
    }

    @Test
    void listShelves() {
    }

    @Test
    void getBookByISBN() {
    }

    @Test
    void testReturnBook() {
    }

    @Test
    void checkOutBook() {
    }

    @Test
    void main() {
    }
}