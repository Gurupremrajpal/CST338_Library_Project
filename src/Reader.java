/*
 *author: Guruprem Rajpal
 *date: 23 feb 2021
 *Description: This assignment is to create the Reader.java class that will be used throughout the rest of the project.
 */



import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static final int CARD_NUMBER_=0;
    public static final int NAME_=1;
    public static final int PHONE_=2;
    public static final int BOOK_COUNT_=3;
    public static final int BOOK_START_=4;

    private int cardNumber;
    private String name;
    private String phone;
    private List<Book> books;

    public Reader(int cardNumber, String name, String phone) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.phone = phone;
        books = new ArrayList<Book>();
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        if (getCardNumber() != reader.getCardNumber()) return false;
        if (getName() != null ? !getName().equals(reader.getName()) : reader.getName() != null) return false;
        return getPhone() != null ? getPhone().equals(reader.getPhone()) : reader.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getCardNumber();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + "(#" + cardNumber + ") has checked out {" + books + "}";
    }

    public Code addBook(Book book) {
        if(books.contains(book))
            return Code.BOOK_ALREADY_CHECKED_OUT_ERROR;
        else{
            books.add(book);
            return Code.SUCCESS;
        }
    }

    public Code removeBook(Book book){
        if(books.contains(book)){
            books.remove(book);
            return Code.SUCCESS;
        }
        else if (!books.contains(book)){
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }
        else
            return Code.READER_COULD_NOT_REMOVE_BOOK_ERROR;
    }

    public int getBookCount(){
        return books.size();
    }

    public boolean hasBook(Book book){
        return (books.contains(book));
    }

    public static void main(String[] args) {
    }

}
