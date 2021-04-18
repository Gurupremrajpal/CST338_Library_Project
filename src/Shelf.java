/*
 *author: Guruprem Rajpal
 *date: 13 Mar 2021
 *Description: This assignment is to create the sheld.java class that will be used throughout the rest of the project.
 */
import java.util.HashMap;
import java.util.Map;

public class Shelf {
    public static final int SHELF_NUMBER_ = 0;
    public static final int SUBJECT_ =1;

    private int shelfNumber;
    private String subject;
    private HashMap<Book, Integer> books;

    public Shelf() {
        books = new HashMap<Book, Integer>();
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public HashMap<Book, Integer> getBooks() {
        return books;
    }

    public void setBooks(HashMap<Book, Integer> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shelf shelf = (Shelf) o;

        if (getShelfNumber() != shelf.getShelfNumber()) return false;
        return getSubject() != null ? getSubject().equals(shelf.getSubject()) : shelf.getSubject() == null;
    }

    @Override
    public int hashCode() {
        int result = getShelfNumber();
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return shelfNumber +":"+ subject;
    }

    public int getBookCount(Book book){
        if(books.containsKey(book)){
            return books.get(book);
        }
        return -1;
    }

    public Code addBook(Book book) {
        if (books.containsKey(book)) {
            int num  =  books.get(book);
            books.put(book, num=num+1 );
            System.out.println(book.toString() + " added to shelf " + this.toString());
            return Code.SUCCESS;
        }  else {
            return Code.SHELF_SUBJECT_MISMATCH_ERROR;
        }
    }
    Code removeBook(Book book){
        if(!books.containsKey(book)){
            System.out.println(book.getTitle() + " is not on shelf " + getSubject());
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }  else {
            int num = books.get(book);
            num=num-1;
            books.put(book,num);
            System.out.println(book.getTitle()+ " successfully removed from shelf " + getSubject());
            return Code.SUCCESS;
        }
    }
    public String listBooks() {

        String sm = "<totalBookCount>" + " books on shelf: " + this.toString() + "\n";
        int num = 0;
        for (Map.Entry<Book, Integer> book : books.entrySet()) {
            num = num + getBookCount(book.getKey());
            sm = sm.concat(book.getKey() + " " + book.getValue() + "\n");
        }
        return sm;
    }

    public static void main(String[] args) {
    }

}
