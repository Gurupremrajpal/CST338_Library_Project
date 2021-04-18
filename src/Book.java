/*
 *author: Guruprem Rajpal
 *date: 18 feb 2021
 *Description: This assignment is to create the Book.java class that will be used throughout the rest of the project.
 */

import java.time.LocalDate;


public class Book {
    public static final int ISBN_ =0;
    public static final int TITLE_ = 1;
    public static final int SUBJECT_ = 2;
    public static final int PAGE_COUNT_=3;
    public static final int AUTHOR_=4;
    public static final int DUE_DATE_=5;

    private String ISBN;
    private String Title;
    private String Subject;
    private int PageCount;
    private String Author;
    private LocalDate DueDate;



    public Book(String ISBN, String Title, String Subject, int PageCount, String Author, LocalDate DueDate) {
        this.ISBN = ISBN;
        this.Title = Title;
        this.Subject = Subject;
        this.PageCount = PageCount;
        this.Author = Author;
        this.DueDate = DueDate;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getPageCount() != book.getPageCount()) return false;
        if (getISBN() != null ? !getISBN().equals(book.getISBN()) : book.getISBN() != null) return false;
        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
        if (getSubject() != null ? !getSubject().equals(book.getSubject()) : book.getSubject() != null) return false;
        return getAuthor() != null ? getAuthor().equals(book.getAuthor()) : book.getAuthor() == null;
    }

    @Override
    public int hashCode() {
        int result = getISBN() != null ? getISBN().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        result = 31 * result + getPageCount();
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return Title + "by" + Author + "ISBN:" + ISBN;
    }

    public static void main(String[] args) {
    }
}
