package model;

public class Category {
    public String bookName ;
    public String name ;
    public String description  ;
    public void getBookName(String bookName){
        this.bookName = bookName ;
    }
    public void setDescription(String description){
        this.description = description ;
    }
    public void setName(String name){this.name = name ; }
    @Override
    public String toString(){
        return bookName + "," + name + "," + description ;
    }
}
