package model;

public class Book {
    public String title;
    public String author;
    public String isbn;
    public String publicationYear;
    public boolean isBorrowed;
    public String getBookISBN(){
        return isbn ;
    }
    public String getBookName(){
        return title ;
    }
    public void setDetails(String title ,String  author ,String  isbn ,String publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }
    public boolean borrowBook(){
        isBorrowed = true;
        return isBorrowed;
    }
    @Override
    public String toString() {
//        String result = (isBorrowed) ? "It's borrowed" : "It's not borrowed" ;
        return title + ","  + author + "," +  isbn + ","+ publicationYear + ',' + isBorrowed;
    }
}
