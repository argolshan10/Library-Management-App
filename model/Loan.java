package model;

public class Loan {

    public String book ;
    public String student ;
    public String loanDate ;
    public String dueDate ;
    public void getBook(String book){
        this.book = book ;
    }
    public void  getStudent(String student){
        this.student = student ;
    }
    public void getLoanDate(String loanDate){
        this.loanDate = loanDate ;
    }
    public void getDuedate(String dueDate){
        this.dueDate = dueDate ;
    }
    public String getLoanISBN(){return book  ; }
    @Override
    public String toString() {
        return student + ","  + book + "," + loanDate  + ","+ dueDate;
    }
}
