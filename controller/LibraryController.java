package controller ;
import java.util.List;

import model.*;
import dataaccess.FileDataModel ;

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

    public void addGraduateStudent(String StudentID, String firstName, String lastName, String major, String supervisor, String thesisTitle) {
        GraduateStudent newGraduateStudent = new GraduateStudent(StudentID, firstName, lastName, major, supervisor, thesisTitle);
        students.add(newGraduateStudent);
        saveStudentstoFile();
        System.out.println("Graduate Student with the ID " + StudentID + " has been added.");
    }

    public void addUndergraduateStudent(String undergraduateID, String undergraduateFirstName,
                                        String undergraduateLastName, String undergraduateMajor, String undergraduateYear) {
        UndergraduateStudent newUndergraduateStudent = new UndergraduateStudent(undergraduateID, undergraduateFirstName, undergraduateLastName, undergraduateMajor, undergraduateYear);
        students.add(newUndergraduateStudent);
        saveStudentstoFile();
        System.out.println("Undergraduate Student with ID  " + undergraduateID + " has been added. ");
    }

    private void saveStudentstoFile() {
        filedatamodel.saveStudents(students, studentFileName);
    }

    private void loanStudentsFromFile() {
        this.students = filedatamodel.loadStudents();
        System.out.println("The Students' information has been loaded.");
    }

    private void loadLoansFromFile() {
        this.loans = filedatamodel.loadLoans();
        System.out.println("The Loans' information has been loaded.");
    }

    private void loadcategoriesFromFile(){
        this.categories = filedatamodel.loadCategories() ;
    }

    private void loanBooksFromFile() {
        this.books = filedatamodel.loadBooks();
        System.out.println("The Books' information has been successfully loaded.");
    }

    public void searchBookbyISBN(String isbnToSearch) {
        for (Book book : books) {
            if (book.getBookISBN().equalsIgnoreCase(isbnToSearch)) {
                System.out.println("Book Found: ");
                System.out.println("==================================================");
                System.out.println(book);
                System.out.println("==================================================");
            }
        }
    }

    public void searchBookbyName(String nametoSearch) {
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(nametoSearch)) {
                System.out.println("Book Found");
                System.out.println("=================================================");
                System.out.println(book);
                System.out.println("=================================================");
            }
        }
    }

    public void searchStudent(String id) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(id)) {
                System.out.println("Student found:");
                System.out.println("*************************************************");
                System.out.println(student);
                System.out.println("*************************************************");
                return;
            }
        }
        System.out.println("No student found with ID: " + id);
    }

    public void displayAllLoans() {
        if (loans.isEmpty()) {
            System.out.println("No Loans found.");
            return;
        }
        System.out.println("=================== List of All Loans ===================");
        for (Loan loan : loans) {
            System.out.println(loan);
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("================ List of All the Students ==============");
        for (Student student : students) {
            System.out.println(student); // calls overridden toString()
        }
        System.out.println("========================================================");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No Books found.");
            return;
        }
        System.out.println("================ List of All the Books ================");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("========================================================");
    }

    public void removeStudent(String deleteID) {
        filedatamodel.deleteStudents(deleteID, studentFileName);
        loanStudentsFromFile();
    }

    public void removeBook(String deleteISBN) {
        filedatamodel.deleteBooks(deleteISBN, bookFileName);
        loanBooksFromFile();
    }

    public void addBook(String title, String author, String isbn, String publicationYear) {
        Book newBook = new Book();
        newBook.setDetails(title, author, isbn, publicationYear);
        books.add(newBook);
        saveBooktoFile();
        saveBooktoCategory(title) ;
        System.out.println("The Book has been added.");
    }
    private void saveBooktoCategory(String booktitle){
        filedatamodel.saveBooktoCategory(booktitle , categories , categoryFileName) ;
    }

    private void saveBooktoFile() {
        filedatamodel.saveBooks(books, bookFileName);
    }

    private void saveLoantoFile() {
        filedatamodel.saveLoans(loans, loanFileName);
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

    public void AddLoan(String studentID, String ketabisbn, String loanDate, String dueDate) {
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
        System.out.println("All Loans:");
        displayAllLoans();
    }
    public void returnBook(String returnISBN){
        filedatamodel.returnBook(returnISBN , loanFileName) ;
        loadLoansFromFile();
    }
    public void listOfBorrowedBooks(String id){
        filedatamodel.listOfBorrowedBooks(id) ;
    }
    public void listOfstudentsbyMajor(String majorName){
        filedatamodel.listOfstudentsbyMajor(majorName) ;
    }
    public void listOfbooksinCategory(String categoryName){
        filedatamodel.listOfbooksinCategory(categoryName) ;
    } ;
}