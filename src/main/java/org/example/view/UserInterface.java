package org.example.view ;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color ;
import javafx.scene.text.* ;
import javafx.scene.control.Label;
import javafx.scene.image.* ;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import org.example.Main ;

import org.example.controller.LibraryController;
import org.example.model.Student;

import javax.swing.*;
import javafx.application.Platform;

public class UserInterface {
    private VBox root ;
    private VBox menuArea = new VBox(10);
    private VBox formArea = new VBox(10);
    private LibraryController LibraryController ; 
    public UserInterface(VBox root) {
        this.LibraryController = new LibraryController();
        this.root = root ;
        menuArea.setAlignment(Pos.CENTER);
        formArea.setAlignment(Pos.CENTER);

        root.getChildren().addAll(menuArea, formArea);

    }

    public void displayMainMenu(){
        root.getChildren().clear();
        menuArea.getChildren().clear(); // Reset menu content
        formArea.getChildren().clear(); // Reset form content
        Label libraryManager = new Label() ;
        libraryManager.setText("Alireza's Library Manager");
        libraryManager.getStyleClass().add("library-manager");
        Button btn2 = new Button("Manage Members") ;
        Button btn3 = new Button("Manage Books") ;
        Button btn4 = new Button("Borrow Section") ;
        Button btn5 = new Button("Get Report") ;
        Button btn6 = new Button("Exit") ;
        btn2.setOnAction(e -> {
            System.out.println("Manage Members button clicked.");
            displayMemberMenu(); // no try-catch needed unless you want logging
        });
        btn3.setOnAction(e1 -> {
            System.out.println("Manage Books button was clicked.");
            displayBookMenu();
        });
        btn4.setOnAction(e2 -> {
            System.out.println("Borrow Section was clicked.");
            displayLoanMenu();
        });
        btn5.setOnAction(e3 -> {
            displayReportMenu();
        });
        btn6.setOnAction(e4 -> {
            Platform.exit();
        });

        menuArea.getChildren().addAll(libraryManager , btn2, btn3, btn4, btn5, btn6);
        root.getChildren().addAll(menuArea, formArea);
    }
    public void displayMemberMenu(){
        menuArea.getChildren().clear();
        formArea.getChildren().clear();
        System.out.println("displayMemberMenu() was called.");
        Button btn7 = new Button("Add Undergraduate Student");
        Button btn8 = new Button("Add Graduate Student");
        Button btn9 = new Button("Search Student by ID");
        Button btn10 = new Button("Delete Student by ID");
        Button btn11 = new Button("Display All Students");
        Button btn12 = new Button("Return to the Main Menu") ;
        menuArea.getChildren().addAll(btn7, btn8, btn9, btn10, btn11, btn12);
        // add undergraduate student
        Label statusLabel = new Label();

        TextField input1 = new TextField();
        input1.setPromptText("Student ID");

        TextField input2 = new TextField();
        input2.setPromptText("First Name");

        TextField input3 = new TextField();
        input3.setPromptText("Last Name");

        TextField input4 = new TextField();
        input4.setPromptText("Major");

        TextField input5 = new TextField();
        input5.setPromptText("Enrollment Year");

        Button btnSubmit = new Button("Submit Student");

        btn7.setOnAction(e -> {
            formArea.getChildren().setAll(input1, input2, input3, input4, input5, btnSubmit, statusLabel);
            statusLabel.setText("");
        });

        btnSubmit.setOnAction(e -> {

            String message = LibraryController.addUndergraduateStudent(
                    input1.getText(),
                    input2.getText(),
                    input3.getText(),
                    input4.getText(),
                    input5.getText()
            );

            statusLabel.setText(message);

            input1.clear();
            input2.clear();
            input3.clear();
            input4.clear();
            input5.clear();

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> displayMemberMenu());
            pause.play();
        });


        // add graduate student
        Label statusLabel2 = new Label();

        TextField input6 = new TextField();
        input6.setPromptText("Student ID");

        TextField input7 = new TextField();
        input7.setPromptText("First Name");

        TextField input8 = new TextField();
        input8.setPromptText("Last Name");

        TextField input9 = new TextField();
        input9.setPromptText("Major:");

        TextField input10 = new TextField();
        input10.setPromptText("Supervisor");

        TextField input11 =  new TextField() ;
        input11.setPromptText("Thesis Title:");

        Button btnSubmit2 = new Button("Submit Student");

        btn8.setOnAction(e -> {
            formArea.getChildren().setAll(input6, input7, input8, input9, input10, input11, btnSubmit2, statusLabel2);
            statusLabel2.setText("");
        });

        btnSubmit2.setOnAction(e -> {

            String message2 = LibraryController.addGraduateStudent(
                    input6.getText(),
                    input7.getText(),
                    input8.getText(),
                    input9.getText(),
                    input10.getText() ,
                    input11.getText()
            );

            statusLabel2.setText(message2);

            input6.clear();
            input7.clear();
            input8.clear();
            input9.clear();
            input10.clear();
            input11.clear();

            PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
            pause2.setOnFinished(event -> displayMemberMenu());
            pause2.play();
        });

        // search student by ID
        Label statusLabel3 = new Label();

        TextField input12 = new TextField();
        input12.setPromptText("Student ID: ");


        Button btnSubmit3 = new Button("Search Student");

        btn9.setOnAction(e -> {
            formArea.getChildren().setAll(input12, btnSubmit3, statusLabel3);
            statusLabel3.setText("");
        });

        btnSubmit3.setOnAction(e -> {

            String message3 = LibraryController.searchStudent(
                    input12.getText()
            );

            statusLabel3.setText(message3);

            input12.clear();


//            PauseTransition pause3 = new PauseTransition(Duration.seconds(2));
//            pause3.setOnFinished(event -> displayMemberMenu());
//            pause3.play();
        });

        // delete student

        Label statusLabel4 = new Label();

        TextField input13 = new TextField();
        input13.setPromptText("Student ID: ");


        Button btnSubmit4 = new Button("Delete Student");

        btn10.setOnAction(e -> {
            formArea.getChildren().setAll(input13, btnSubmit4, statusLabel4);
            statusLabel4.setText("");
        });

        btnSubmit4.setOnAction(e -> {

            String message4 = LibraryController.removeStudent(
                    input13.getText()
            );

            statusLabel4.setText(message4);

            input13.clear();


            PauseTransition pause4 = new PauseTransition(Duration.seconds(2));
            pause4.setOnFinished(event -> displayMemberMenu());
            pause4.play();
        });

        // display all students

        Label statusLabel5 = new Label();

        btn11.setOnAction(e -> {
            String message5 = LibraryController.displayAllStudents() ;
            statusLabel5.setText(message5);
            statusLabel5.setWrapText(true);
            formArea.getChildren().setAll(statusLabel5);
//            PauseTransition pause5 = new PauseTransition(Duration.seconds(2)) ;
//            pause5.setOnFinished(event -> displayMemberMenu());
//            pause5.play();

        });


        // return to the main menu
        btn12.setOnAction(e -> {
            formArea.getChildren().clear();
            displayMainMenu();
        });

    }
    public void displayBookMenu(){
        menuArea.getChildren().clear();
        formArea.getChildren().clear();
        Button btnAddBook = new Button("Add Book") ;
        Button btnDeleteBook = new Button("Delete Book") ;
        Button btnSearchBookbyISBN = new Button("Search Book by ISBN") ;
        Button btnSearchBookbyTitle = new Button("Search Book by Title") ;
        Button btnAllBooks = new Button("See List of All Books") ;
        Button btnReturnMenu = new Button("Return to Main Menu") ;
        menuArea.getChildren().addAll(btnAddBook  , btnDeleteBook  , btnSearchBookbyISBN  , btnSearchBookbyTitle  , btnAllBooks  , btnReturnMenu) ;

        // add book
        Label addbookLabel = new Label() ;
        TextField getTitle = new TextField() ;
        getTitle.setPromptText("Title:\t");

        TextField getAuthor = new TextField() ;
        getAuthor.setPromptText("Author:\t");

        TextField getISBN = new TextField() ;
        getISBN.setPromptText("ISBN:\t");

        TextField getPublicationYear = new TextField() ;
        getPublicationYear.setPromptText("Publication Year\t");

        TextField chooseCategory = new TextField();
        chooseCategory.setPromptText("Which one of these Categories does the book belong to? 1) Computer Science 2) Electrical Engineering 3) History 4) Novel  ");

        Button btnSubmitBook = new Button("Submit Book:") ;


        btnAddBook.setOnAction(e -> {
           formArea.getChildren().setAll(getTitle , getAuthor , getISBN , getPublicationYear , chooseCategory , btnSubmitBook , addbookLabel);
            addbookLabel.setText("");
        });

        btnSubmitBook.setOnAction(e1 ->{
            String message = LibraryController.addBook(
                    getTitle.getText() ,
                    getAuthor.getText() ,
                    getISBN.getText() ,
                    getPublicationYear.getText() ,
                    chooseCategory.getText()
            );

            addbookLabel.setText(message);

            getTitle.clear();
            getAuthor.clear();
            getISBN.clear();
            getPublicationYear.clear();


            PauseTransition pause = new PauseTransition(Duration.seconds(2)) ;
            pause.setOnFinished(event -> displayBookMenu());
            pause.play();
        });

        // delete book
        Label deletebookLabel = new Label() ;
        TextField ISBNtoDelete = new TextField() ;
        ISBNtoDelete.setPromptText("ISBN:\t");

        Button btnSubmitDelete = new Button("Delete Book") ;


        btnDeleteBook.setOnAction(e -> {
            formArea.getChildren().setAll(ISBNtoDelete , btnSubmitDelete , deletebookLabel) ;
            deletebookLabel.setText("");
        });

        btnSubmitDelete.setOnAction(e1 ->{
            String message2 = LibraryController.removeBook(
                    ISBNtoDelete.getText()
            );

            deletebookLabel.setText(message2);

            ISBNtoDelete.clear();

            PauseTransition pause2 = new PauseTransition(Duration.seconds(2)) ;
            pause2.setOnFinished(event -> displayBookMenu());
            pause2.play();
        });

        // search book by ISBN

        Label searchbookISBNLabel = new Label() ;
        TextField ISBNtoSearch = new TextField() ;
        ISBNtoSearch.setPromptText("ISBN:\t");

        Button btnSubmitSearchISBN = new Button("Search Book") ;

        btnSearchBookbyISBN.setOnAction(e -> {
            formArea.getChildren().setAll(ISBNtoSearch , btnSubmitSearchISBN , searchbookISBNLabel) ;
            searchbookISBNLabel.setText("");
        });

        btnSubmitSearchISBN.setOnAction(e1 ->{
            String message3 = LibraryController.searchBookbyISBN(
                    ISBNtoSearch.getText()
            );

            searchbookISBNLabel.setText(message3);

            ISBNtoSearch.clear();


//            PauseTransition pause3 = new PauseTransition(Duration.seconds(2)) ;
//            pause3.setOnFinished(event -> displayMemberMenu());
//            pause3.play();
        });

        // search book by name
        Label searchbookTITLELabel = new Label() ;
        TextField TITLEtoSearch = new TextField() ;
        TITLEtoSearch.setPromptText("Title:\t");

        Button btnSubmitSearchTITLE = new Button("Search Book") ;

        btnSearchBookbyTitle.setOnAction(e -> {
            formArea.getChildren().setAll(TITLEtoSearch , btnSubmitSearchTITLE , searchbookTITLELabel) ;
            searchbookTITLELabel.setText("");
        });

        btnSubmitSearchTITLE.setOnAction(e1 ->{
            String message4 = LibraryController.searchBookbyName(
                    TITLEtoSearch.getText()
            );

            searchbookTITLELabel.setText(message4);
            TITLEtoSearch.clear();


//            PauseTransition pause4 = new PauseTransition(Duration.seconds(2)) ;
//            pause4.setOnFinished(event -> displayMemberMenu());
//            pause4.play();
        });

        // display list of all Books
        Label displayAllBooksLabel = new Label() ;


        btnAllBooks.setOnAction(e -> {
            String message5 = LibraryController.displayAllBooks() ;
            displayAllBooksLabel.setText(message5);
            formArea.getChildren().setAll(displayAllBooksLabel) ;

        });

        // return to the main menu
        btnReturnMenu.setOnAction(e -> {
            formArea.getChildren().clear();
            displayMainMenu();
        });

    }
    public void displayLoanMenu(){
        menuArea.getChildren().clear();
        formArea.getChildren().clear();
        Button borrowBook = new Button("Borrow a Book") ;
        Button returnBook = new Button("Return a Borrowed Book") ;
        Button returnToMainMenu = new Button("Return to the Main Menu") ;
        menuArea.getChildren().addAll(borrowBook , returnBook , returnToMainMenu) ;

        // borrow a book
        Label borrowABook = new Label() ;
        TextField bookISBN = new TextField() ;
        bookISBN.setPromptText("Book ISBN:\t");
        TextField studentID = new TextField() ;
        studentID.setPromptText("Student ID:\t");
        TextField loanDate = new TextField();
        loanDate.setPromptText("Date of Borrow:\t");
        TextField dueDate = new TextField()  ;
        dueDate.setPromptText("Due Date:\t");
        Button btnSubmitBorrow = new Button("Borrow this Book") ;


        borrowBook.setOnAction(e -> {
            formArea.getChildren().setAll(bookISBN , studentID , loanDate , dueDate , btnSubmitBorrow , borrowABook) ;
            borrowABook.setText("");
        });

        btnSubmitBorrow.setOnAction(e -> {
            String message = LibraryController.AddLoan(
                    studentID.getText() ,
                    bookISBN.getText()  ,
                    loanDate.getText() ,
                    dueDate.getText()
            );

            borrowABook.setText(message);

            studentID.clear();
            bookISBN.clear();
            loanDate.clear();
            dueDate.clear();

            PauseTransition pause = new PauseTransition(Duration.seconds(2)) ;
            pause.setOnFinished(event -> displayLoanMenu());
            pause.play();
        });

        // return a borrowed book
        Label deleteBorrow = new Label() ;
        TextField isbnToReturn = new TextField() ;
        isbnToReturn.setPromptText("Book ISBN:\t");
        Button btnSubmitReturn = new Button("Return this book") ;


        returnBook.setOnAction(e -> {
           formArea.getChildren().setAll(isbnToReturn , btnSubmitReturn , deleteBorrow) ;
            deleteBorrow.setText("");
        });

        btnSubmitReturn.setOnAction(e -> {
            String message2 = LibraryController.returnBook(
                    isbnToReturn.getText()
            );

            deleteBorrow.setText(message2);

            isbnToReturn.clear();

            PauseTransition pause2 = new PauseTransition(Duration.seconds(2)) ;
            pause2.setOnFinished(event -> displayLoanMenu());
            pause2.play();
        });

        // return to the main menu
        returnToMainMenu.setOnAction(e -> {
            formArea.getChildren().clear();
            displayMainMenu();
        });

    }
    public void displayReportMenu(){
        menuArea.getChildren().clear();
        formArea.getChildren().clear();
        Button booksInCertainCategory = new Button("See List of Books in a Certain Category") ;
        Button booksBorrowedByaStudent = new Button("See List of Books borrowed by a certain student") ;
        Button studentsStudyingAMajor = new Button("See List of Students studying a certain major") ;
        Button returnToMainMenu = new Button("Return to the main Menu") ;
        menuArea.getChildren().addAll(booksInCertainCategory , booksBorrowedByaStudent , studentsStudyingAMajor , returnToMainMenu) ;

        // list of books in a certain category
        Label booksInCategory = new Label() ;
        TextField getCategory = new TextField() ;
        getCategory.setPromptText("Enter Category:\t");
        Button btnSubmitCategory = new Button("Search for books in this Category") ;


        booksInCertainCategory.setOnAction(e -> {
            formArea.getChildren().setAll(getCategory , btnSubmitCategory , booksInCategory) ;
            booksInCategory.setText("");
        });

        btnSubmitCategory.setOnAction(e -> {
            String message = LibraryController.listOfbooksinCategory(
                    getCategory.getText()
            );

            booksInCategory.setText(message);

            getCategory.clear();


//            PauseTransition pause = new PauseTransition(Duration.seconds(2)) ;
//            pause.setOnFinished(event -> displayMemberMenu());
//            pause.play();

        });

        // list of books borrowed by a certain student
        Label booksBorrowed = new Label() ;
        TextField getStudentID = new TextField() ;
        getStudentID.setPromptText("Enter Student ID:");
        Button btnSubmitBorrowedBooks = new Button("Search for Books borrowed by this student") ;


        booksBorrowedByaStudent.setOnAction(e -> {
            formArea.getChildren().setAll(getStudentID , btnSubmitBorrowedBooks , booksBorrowed) ;
            booksBorrowed.setText("");
        });

        btnSubmitBorrowedBooks.setOnAction(e -> {
            String message2 = LibraryController.listOfBorrowedBooks(
                    getStudentID.getText()
            );

            booksBorrowed.setText(message2);
            getStudentID.clear();


//            PauseTransition pause2 = new PauseTransition(Duration.seconds(2)) ;
//            pause2.setOnFinished(event -> displayMemberMenu());
//            pause2.play();

        });

        // list of students studying a certain major
        Label studentsMajor = new Label() ;
        TextField getMajorName = new TextField() ;
        getMajorName.setPromptText("Enter Major Name:");
        Button btnSubmitMajor = new Button("Search for Students who are currently studying this major") ;



        studentsStudyingAMajor.setOnAction(e -> {
            formArea.getChildren().setAll(getMajorName , btnSubmitMajor , studentsMajor) ;
            studentsMajor.setText("");
        });

        btnSubmitMajor.setOnAction(e -> {
            String message3 = LibraryController.listOfstudentsbyMajor(
                    getMajorName.getText()
            );

            studentsMajor.setText(message3);

            getMajorName.clear();


//            PauseTransition pause2 = new PauseTransition(Duration.seconds(2)) ;
//            pause2.setOnFinished(event -> displayMemberMenu());
//            pause2.play();

        });

        // return to the main menu
        returnToMainMenu.setOnAction(e -> {
            formArea.getChildren().clear();
            displayMainMenu();
        });

    }
}