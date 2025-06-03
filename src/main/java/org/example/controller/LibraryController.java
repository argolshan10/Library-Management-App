package org.example.controller ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.model.* ;
import org.example.dataaccess.FileDataModel ;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner ;
import java.util.ArrayList;

public class LibraryController {
    public static final String studentFileName = "students.txt";
    public static final String bookFileName = "books.txt";
    public static final String loanFileName = "borrows.txt";
    public static final String categoryFileName = "categories.txt" ;
    private int counter = 0;
    private int count = 0;
    private String[] bookISBN;
    private String[] studentID;
    private List<Student> students;
    private List<Loan> loans;
    private List<Book> books;
    private List<Category> categories ;
    private FileDataModel filedatamodel;

    public LibraryController() {
        this.students = new ArrayList<>();
        this.filedatamodel = new FileDataModel("students.txt", bookFileName, loanFileName , categoryFileName);
        loanStudentsFromFile();
        loanBooksFromFile();
        loadLoansFromFile();
        loadcategoriesFromFile() ;
    }

    public String addGraduateStudent(String StudentID, String firstName, String lastName, String major, String supervisor, String thesisTitle) {
        GraduateStudent newGraduateStudent = new GraduateStudent(StudentID, firstName, lastName, major, supervisor, thesisTitle);
        students.add(newGraduateStudent);
        return saveStudentstoFile() ;
    }

    public String addUndergraduateStudent(String undergraduateID, String undergraduateFirstName,
                                        String undergraduateLastName, String undergraduateMajor, String undergraduateYear) {
        UndergraduateStudent newUndergraduateStudent = new UndergraduateStudent(undergraduateID, undergraduateFirstName, undergraduateLastName, undergraduateMajor, undergraduateYear);
        students.add(newUndergraduateStudent);
        return saveStudentstoFile() ;
    }

    private String saveStudentstoFile() {
        return filedatamodel.saveStudents(students, studentFileName );
    }

    private void loanStudentsFromFile() {
        this.students = filedatamodel.loadStudents();

    }

    private void loadLoansFromFile() {
        this.loans = filedatamodel.loadLoans();

    }

    private void loadcategoriesFromFile(){
        this.categories = filedatamodel.loadCategories() ;
    }

    private void loanBooksFromFile() {
        this.books = filedatamodel.loadBooks();

    }

    public String searchBookbyISBN(String isbnToSearch) {
        StringBuilder result = new StringBuilder();

        for (Book book : books) {
            if (book.getBookISBN().equalsIgnoreCase(isbnToSearch)) {
                result.append("Book Found:")
                        .append(" | Title: ").append(book.title)
                        .append(" | Author: ").append(book.author)
                        .append(" | ISBN: ").append(book.isbn)
                        .append(" | Publication Year: ").append(book.publicationYear)
                        .append(" | Borrowed: ").append(book.isBorrowed)
                        .append("\n");
            }
        }

        if (result.length() == 0) {
            return "Book was not found.";
        } else {
            return result.toString();
        }
    }

    public String searchBookbyName(String nametoSearch) {
        StringBuilder result = new StringBuilder();

        for (Book book : books) {
            if (book.title.equalsIgnoreCase(nametoSearch)) {
                result.append("Book Found:")
                        .append(" | Title: ").append(book.title)
                        .append(" | Author: ").append(book.author)
                        .append(" | ISBN: ").append(book.isbn)
                        .append(" | Publication Year: ").append(book.publicationYear)
                        .append(" | Borrowed: ").append(book.isBorrowed)
                        .append("\n");
            }
        }

        if (result.length() == 0) {
            return "Book was not found.";
        } else {
            return result.toString();
        }
    }

    public String searchStudent(String id) {
        StringBuilder result = new StringBuilder();

        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(id)) {
                if (student instanceof GraduateStudent) {
                    GraduateStudent grad = (GraduateStudent) student;
                    result.append("ID: ").append(grad.studentID).append(" |  ")
                            .append("First Name: ").append(grad.firstName).append(" |  ")
                            .append("Last Name: ").append(grad.lastName).append(" |  ")
                            .append("Major: ").append(grad.major).append(" | ")
                            .append("Supervisor: ").append(grad.supervisor).append(" |  ")
                            .append("Thesis Title: ").append(grad.thesisTitle).append("\n");
                } else if (student instanceof UndergraduateStudent) {
                    UndergraduateStudent undergrad = (UndergraduateStudent) student;
                    result.append("ID: ").append(undergrad.studentID).append(" |  ")
                            .append("First Name: ").append(undergrad.firstName).append(" |  ")
                            .append("Last Name: ").append(undergrad.lastName).append(" |  ")
                            .append("Major: ").append(undergrad.major).append(" | ")
                            .append("Enrollment Year: ").append(undergrad.enrollmentYear).append("\n");
                }
                break; // Optional: stop after first match
            }
        }

        if (result.length() == 0) {
            return "Student not found";
        } else {
            return result.toString();
        }
    }

    public String displayAllLoans() {
        if (loans.isEmpty()) {
            return "No loans found.";
        }

        StringBuilder result = new StringBuilder();
        for (Loan loan : loans) {
            result.append("Book ISBN: ").append(loan.book).append(" | ")
                    .append("Student ID: ").append(loan.student).append(" | ")
                    .append("Date of Borrow: ").append(loan.loanDate).append(" | ")
                    .append("Due Date: ").append(loan.dueDate).append("\n");
        }

        return result.toString();
    }

    public String displayAllStudents() {
        StringBuilder result = new StringBuilder();

        for (Student student : students) {
            if (student instanceof GraduateStudent grad) {
                result.append("ID: ").append("  ")
                        .append(grad.studentID).append(" |  ")
                        .append("First Name: ").append(" ").append(grad.firstName).append(" |  ")
                        .append("Last Name: ").append(" ").append(grad.lastName).append(" |  ")
                        .append("Major: ").append("  ").append(grad.major).append(" | ")
                        .append("Supervisor: ").append(" ").append(grad.supervisor).append(" |  ")
                        .append("Thesis Title: ").append(" ").append(grad.thesisTitle).append("\n");
            } else if (student instanceof UndergraduateStudent undergrad) {
                result.append("ID: ").append(" ").append(undergrad.studentID).append(" |  ")
                        .append("First Name: ").append(" ").append(undergrad.firstName).append(" |  ")
                        .append("Last Name: ").append("  ").append(undergrad.lastName).append(" |  ")
                        .append("Major: ").append(" ").append(undergrad.major).append(" | ")
                        .append("Enrollment year: ").append(" ").append(undergrad.enrollmentYear).append("\n");
            }
        }

        return result.length() == 0 ? "No Student found" : result.toString();
    }

    public String displayAllBooks() {
        if (books.isEmpty()) {
            return "No Books found.";
        }

        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append("Title: ").append(book.title).append(" | ")
                    .append("Author: ").append(book.author).append(" | ")
                    .append("ISBN: ").append(book.isbn).append(" | ")
                    .append("Publication Year: ").append(book.publicationYear).append(" | ")
                    .append("Borrowed: ").append(book.isBorrowed).append("\n");
        }

        return result.toString();
    }

    public String removeStudent(String deleteID) {
        if(filedatamodel.deleteStudents(deleteID, studentFileName)) {
            loanStudentsFromFile();
            return "The Student was Successfully deleted." ;
        } else {
            return "Failed to delete student" ;
        }
    }

    public String removeBook(String deleteISBN) {
        if(filedatamodel.deleteBooks(deleteISBN, bookFileName)){
            loanBooksFromFile();
            return "The Book was Successfully deleted." ;
        } else {
            return "Failed to delete Book." ;
        }
    }

    public String addBook(String title, String author, String isbn, String publicationYear , String theCategory) {
        Book newBook = new Book();
        newBook.setDetails(title, author, isbn, publicationYear);
        books.add(newBook);
        saveBooktoFile();
        saveBooktoCategory(theCategory , title) ;
        if(saveBooktoFile() && saveBooktoCategory(theCategory , title)){
            return "The Book has been added." ;
        } else {
            return "Failed to add book." ;
        }
    }
    private boolean saveBooktoCategory(String theCategory , String booktitle){
        return filedatamodel.saveBooktoCategory( theCategory , booktitle , categories , categoryFileName) ;
    }

    private boolean saveBooktoFile() {
        return filedatamodel.saveBooks(books, bookFileName);
    }

    private boolean saveLoantoFile() {
        return filedatamodel.saveLoans(loans, loanFileName);
    }

    private void storeStudentID(List<Student> students) {
        studentID = new String[students.size()];
        for (int i = 0; i < students.size(); i++) {
            studentID[i] = students.get(i).getStudentID();
        }
    }

    private void storeBookISBN(List<Book> books) {
        bookISBN = new String[books.size()];
        for (int i = 0; i < books.size(); i++) {
            bookISBN[i] = books.get(i).getBookISBN();
        }
    }

    public boolean isStudentEnrolled(String theID) {
        storeStudentID(students);
        for (int i = 0; i < studentID.length; i++) {
            if (studentID[i].equals(theID)) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBookAvailable(String theISBN) {
        storeBookISBN(books);
        for (int i = 0; i < bookISBN.length; i++) {
            if (bookISBN[i].equals(theISBN)) {
                counter++;
            }
        }
        if (counter > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String AddLoan(String studentID, String ketabisbn, String loanDate, String dueDate) {
        Book newBook = new Book();
        newBook.setDetails(ketabisbn, loanDate, dueDate, studentID);
        Loan newLoan = new Loan();
        newLoan.getStudent(studentID);
        newLoan.getBook(ketabisbn);
        newLoan.getLoanDate(loanDate);
        newLoan.getDuedate(dueDate);
        loans.add(newLoan);
        saveLoantoFile();
        newBook.borrowBook();
        if(saveBooktoFile()){
            return "The Book was Successfully borrowed. List of All Borrows:  " + displayAllLoans();
        }
        return "Failed to borrow the book" ;
    }
    public String returnBook(String returnISBN){
        filedatamodel.returnBook(returnISBN , loanFileName) ;
        loadLoansFromFile();
        if(filedatamodel.returnBook(returnISBN , loanFileName)){
            return "The book has been Successfully returned." ;
        }
        return "Failed to return  the book." ;
    }
    public String listOfBorrowedBooks(String id){
        return filedatamodel.listOfBorrowedBooks(id) ;
    }
    public String listOfstudentsbyMajor(String majorName){
        return filedatamodel.listOfstudentsbyMajor(majorName) ;
    }
    public String listOfbooksinCategory(String categoryName){
        return filedatamodel.listOfbooksinCategory(categoryName) ;
    } ;
}