/*
 *author: Guruprem Rajpal
 *date: 25 March 2021
 *Description: This assignment is to create the Library.java class that will be used throughout the rest of the project.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Library {

    public static final int LENDING_LIMIT = 5;
    private String name;
    private static int libraryCard;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;
    private HashMap<Book, Integer> books;

    public Library(String name) {
        this.name = name;
        books = new HashMap<Book, Integer>();
        shelves = new HashMap<String, Shelf>();
        readers = new ArrayList<Reader>();
    }

    public Code init(String fileName) {
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            int bookNo = convertInt(sc.nextLine(), Code.BOOK_COUNT_ERROR);
            if (bookNo < 0) {
                return Code.BOOK_COUNT_ERROR;
            }
            Code c = initBooks(bookNo, sc);
            if (c != Code.SUCCESS) {
                return c;
            }

            int shelvesNo = convertInt(sc.nextLine(), Code.SHELF_NUMBER_PARSE_ERROR);
            if (shelvesNo < 0) {
                return Code.SHELF_NUMBER_PARSE_ERROR;
            }
            c = initShelves(shelvesNo, sc);
            if (c != Code.SUCCESS) {
                return c;
            }
            int readerNo = convertInt(sc.nextLine(), Code.READER_COUNT_ERROR);
            if (readerNo < 0) {
                return Code.READER_COUNT_ERROR;
            }

            c = initReaders(readerNo, sc);
            if (c != Code.SUCCESS) {
                return c;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Code.FILE_NOT_FOUND_ERROR;
        }
        return Code.SUCCESS;
    }

    private Code initBooks(int bookCount, Scanner sc) {
        if (bookCount < 1) {
            return Code.LIBRARY_ERROR;
        }
        System.out.println("parsing " + bookCount + " books");
        for (int i = 0; i < bookCount; i++) {
            String bookLine = sc.nextLine();
            System.out.println("parsing book: " + bookLine);
            String attr[] = bookLine.split(",");
            Book b;
            int page = convertInt(attr[3], Code.PAGE_COUNT_ERROR);
            LocalDate date = convertDate(attr[5], Code.DATE_CONVERSION_ERROR);
            if (date == null) {
                return Code.DATE_CONVERSION_ERROR;
            }
            if (page < 0) {
                return Code.PAGE_COUNT_ERROR;
            }
            b = new Book(attr[0], attr[1], attr[2], page, attr[4], date);
            addBook(b);
            //System.out.println(b);
        }
        System.out.println("SUCCESS");
        listBooks();
        return Code.SUCCESS;
    }

    public Code addBook(Book newBook) {
        if (books.containsKey(newBook)) {
            books.put(newBook, books.get(newBook) + 1);
            System.out.println(books.get(newBook) + " copies of " + newBook.getTitle() + " in the stacks");
        } else {
            books.put(newBook, 1);
            System.out.println(newBook + " added to the stacks.");
        }

        if (shelves.containsKey(newBook.getSubject())) {
            shelves.get(newBook.getSubject()).addBook(newBook);
            return Code.SUCCESS;
        }

        System.out.println("No shelf for " + newBook.getSubject() + " books");
        return Code.SHELF_EXISTS_ERROR;
    }

    public Code returnBook(Reader r, Book b) {
        if (r.getBooks().isEmpty() || !r.getBooks().contains(b)) {
            System.out.println(r.getName() + " doesn't have "
                    + b.getTitle() + " checked out");
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }
        Code c = r.removeBook(b);
        if (c == Code.SUCCESS) {
            System.out.println(r.getName() + " is returning " + b);
            return returnBook(b);
        } else {
            System.out.println("Could not return " + b);
            return c;
        }
    }

    public int listBooks() {
        int sum = 0;
        for (Book b : books.keySet()) {
            System.out.println(books.get(b) + " copies of " + b);
            sum += books.get(b);
        }
        return sum;
    }

    private Code errorCode(int codeNumber) {

        Code[] codes = Code.values();
        for (int i = 0; i < codes.length; i++) {
            if (codes[i].getCode() == codeNumber) {
                return codes[i];
            }
        }
        return Code.UNKNOWN_ERROR;
    }

    public static int getLibraryCardNumber() {
        return libraryCard + 1;
    }

    public Code addReader(Reader r) {
        if (readers.contains(r)) {
            System.out.println(r.getName() + " already has an account!");
            return Code.READER_ALREADY_EXISTS_ERROR;
        }

        for (Reader reader : readers) {
            if (reader.getCardNumber() == r.getCardNumber()) {
                System.out.println(reader.getName() + " and " + r.getName() + " have the same card number!");
                return Code.READER_CARD_NUMBER_ERROR;
            }
        }

        readers.add(r);
        System.out.println(r.getName() + " added to the library!");
        if (r.getCardNumber() > this.libraryCard) {
            libraryCard = r.getCardNumber();
        }
        return Code.SUCCESS;

    }

    public Code removeReader(Reader r) {

        if (readers.contains(r)) {
            if (r.getBooks().isEmpty()) {
                readers.remove(r);
                return Code.SUCCESS;
            } else {
                System.out.println(r.getName() + " must return all books!\"");
                return Code.READER_STILL_HAS_BOOKS_ERROR;
            }
        } else {
            System.out.println(r + " is not part of this Library");
            return Code.READER_NOT_IN_LIBRARY_ERROR;
        }

    }

    public Reader getReaderByCard(int cardNumber) {
        for (Reader r : readers) {
            if (r.getCardNumber() == cardNumber) {
                return r;
            }
        }
        System.out.println("Could not find a reader with card #" + cardNumber);
        return null;
    }

    public int convertInt(String recordCountString, Code code) {
        int res = 0;
        try {
            res = Integer.parseInt(recordCountString);
            return res;
        } catch (NumberFormatException e) {
            System.out.println("Value which caused the error: " + recordCountString + "\n"
                    + "Error message: " + code.getMessage());
            return code.getCode();
        }
    }

    public int listReaders() {
        for (Reader r : readers) {
            System.out.println(r);
        }
        return readers.size();
    }

    public int listReaders(Boolean showBooks) {
        if (showBooks) {
            for (Reader r : readers) {
                System.out.println(r.getName() + "(#" + r.getCardNumber() + ") has the following books:"
                        + r.getBooks());

            }
        }
        else
            listReaders();
        return readers.size();
    }

    public Shelf getShelf(String subject) {

        if (shelves.keySet().contains(subject)) {
            return shelves.get(subject);
        } else {
            System.out.println("No shelf for " + subject + " books");
            return null;
        }
    }

    public Shelf getShelf(Integer shelfNumber) {

        for (Shelf s : shelves.values()) {
            if (s.getShelfNumber() == shelfNumber) {
                return s;
            }
        }

        System.out.println("No shelf number " + shelfNumber + " found");
        return null;

    }

    public LocalDate convertDate(String date, Code errorCode) {
        if (date.equals("0000")) {
            LocalDate d = LocalDate.of(1970, 1, 1);
            return d;

        }
        String attr[] = date.split("-");
        if (attr.length != 3) {
            System.out.println("ERROR: date conversion error, could not parse "
                    + date + "\n"
                    + "Using default date (01-jan-1970)");
            LocalDate d = LocalDate.of(1970, 1, 1);
            return d;
        }

        if ((Integer.parseInt(attr[0]) < 0)
                || (Integer.parseInt(attr[1]) < 0)
                || (Integer.parseInt(attr[2]) < 0)) {
            System.out.println("Error converting date: Year " + attr[0] + "\n"
                    + "Error converting date: Month " + attr[1] + "\n"
                    + "Error converting date: Day " + attr[2] + "\n"
                    + "Using default date (01-jan-1970)");
            LocalDate d = LocalDate.of(1970, 1, 1);
            return d;
        }

        return LocalDate.of(Integer.parseInt(attr[0]), Integer.parseInt(attr[1]), Integer.parseInt(attr[2]));

    }

    public Code addShelf(String shelfSubject)
    {
        Shelf sh = new Shelf();
        sh.setShelfNumber(shelves.size() + 1);
        sh.setSubject(shelfSubject);
        return addShelf(sh);
    }

    public Code addShelf(Shelf shelf) {
        if (shelves.values().contains(shelf)) {
            System.out.println("ERROR: Shelf already exists " + shelf);
            return Code.SHELF_EXISTS_ERROR;
        }

        shelves.put(shelf.getSubject(), shelf);
        for (Book b : books.keySet()) {
            if (b.getSubject().equals(shelf.getSubject())) {
                addBookToShelf(b, shelf);
            }
        }
        return Code.SUCCESS;
    }

    public Code listShelves(boolean showbooks) {
        //If the boolean showbooks is true, call the 'listbooks' method for each shelf.
        if (showbooks) {

            for (Shelf s : shelves.values()) {
                System.out.println(s.listBooks());
            }
        } //If the boolean showbooks is false display the toString of the each shelf object
        else {
            for (Shelf s : shelves.values()) {
                System.out.println(s);
            }
        }
        return Code.SUCCESS;
    }

    public Book getBookByISBN(String isbn) {
        for (Book b : books.keySet()) {
            if (b.getISBN().equals(isbn)) {
                return b;
            }
        }
        System.out.println("ERROR: Could not find a book with isbn: " + isbn);
        return null;
    }

    private Code addBookToShelf(Book book, Shelf shelf) {
        if (!book.getSubject().equals(shelf.getSubject())) {
            return Code.SHELF_SUBJECT_MISMATCH_ERROR;
        }
        Code res = Code.SUCCESS;
        for (int i = 0; i < books.get(book); i++) {
            res = shelf.addBook(book);
            if (res != Code.SUCCESS) {
                System.out.println("Could not add " + book + " to shelf " + shelf);
            } else {
                System.out.println(book + " added to shelf " + shelf);
            }
        }

        return res;
    }

    public Code returnBook(Book book) {
        String subj = book.getSubject();
        Shelf s = shelves.get(subj);
        if (s == null) {
            System.out.println("No shelf for " + book);
            return Code.SHELF_EXISTS_ERROR;
        } else {
            s.addBook(book);
            return Code.SUCCESS;
        }

    }

    private Code initShelves(int shelvesNo, Scanner sc) {
        if (shelvesNo < 1) {
            return Code.SHELF_COUNT_ERROR;
        }
        System.out.println("parsing " + shelvesNo + " shelves");

        for (int i = 0; i < shelvesNo; i++) {
            String shelvesLine = sc.nextLine();
            String attr[] = shelvesLine.split(",");
            Shelf s;
            int shNumber = convertInt(attr[0], Code.SHELF_NUMBER_PARSE_ERROR);

            if (shNumber < 0) {
                return Code.SHELF_NUMBER_PARSE_ERROR;
            }
        }
        for (Book b : books.keySet()) {
            for (int i = 0; i < books.get(b); i++) {
                System.out.println(b + " added to shelf " + shelves.get(b.getSubject()));
            }
        }

        System.out.println("SUCCESS");
        listShelves(true);
        return Code.SUCCESS;
    }

    private Code initReaders(int readerNo, Scanner sc) {
        if (readerNo <= 0) {
            return Code.READER_COUNT_ERROR;
        }

        for (int i = 0; i < readerNo; i++) {
            String readerLine = sc.nextLine();
            String attr[] = readerLine.split(",");
            Reader r;
            int cardNumber = convertInt(attr[0], Code.READER_CARD_NUMBER_ERROR);
            r = new Reader(cardNumber, attr[1], attr[2]);
            int numberOfbooks = convertInt(attr[3], Code.READER_COUNT_ERROR);

            readers.add(r);
            //System.out.println(r);
            for (int j = 0; j < numberOfbooks; j++) {
                String isbn = attr[4 + 2 * j];
                String date = attr[4 + 2 * j + 1];
                Book b = getBookByISBN(isbn);
                if (b == null) {
                    System.out.println("ERROR");
                }
                b.setDueDate(convertDate(date, Code.DATE_CONVERSION_ERROR));
                checkOutBook(r, b);
                System.out.println("SUCCESS");

            }
        }
        System.out.println("SUCCESS");
        return Code.SUCCESS;
    }

    public Code checkOutBook(Reader r, Book b) {
        if (!readers.contains(r)) {
            System.out.println(r.getName() + " doesn't have an account here");
            return Code.READER_NOT_IN_LIBRARY_ERROR;
        }
        if (r.getBooks().size() == LENDING_LIMIT) {
            System.out.println(r.getName() + " has reached the lending limit, " + LENDING_LIMIT);
            return Code.BOOK_LIMIT_REACHED_ERROR;
        }
        if (!books.keySet().contains(b)) {
            System.out.println("ERROR: could not find " + b);
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }

        String shelfName = b.getSubject();
        Shelf shelf = shelves.get(shelfName);
        if (shelf == null) {
            System.out.println("no shelf for " + b + " books!");
            return Code.SHELF_EXISTS_ERROR;
        }
        if (shelf.getBooks().get(b) < 1) {
            System.out.println("ERROR: no copies of " + b + " remain");
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        Code res = r.addBook(b);
        if (res != Code.SUCCESS) {
            System.out.println("Couldn't checkout " + b);
            return res;
        }
        res = shelf.removeBook(b);
        if (res == Code.SUCCESS) {
            System.out.println(b + " checked out successfully");
        }
        return res;

    }
    public static void main (String[] args){

    }
}
