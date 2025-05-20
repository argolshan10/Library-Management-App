package view ; 
import java.util.Scanner;

import controller.LibraryController; 
public class UserInterface { 
    private Scanner scanner; 
    private LibraryController LibraryController ; 
    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.LibraryController = new LibraryController(); 
    } 
    public void displayMainMenu(){
        int choice ; 
        do {
            System.out.println("======= ALIREZA'S LIBRARY MANAGEMENT APPLICATION =======");
            System.out.println("| Please choose one of the following options:          |");
            System.out.println("========================================================");
            System.out.println("| 1) Manage Members                                    |");
            System.out.println("========================================================");
            System.out.println("| 2) Manage Books                                      |");
            System.out.println("========================================================");
            System.out.println("| 3) Borrow Section                                    |");
            System.out.println("========================================================");
            System.out.println("| 4) Get Report                                        |");
            System.out.println("========================================================");
            System.out.println("| 5) Exit                                              |");
            System.out.println("========================================================");
            choice = scanner.nextInt() ; 
            scanner.nextLine() ; 
            switch (choice){
            case 1:
                displayMemberMenu();
            break;
            case 2:
                 displayBookMenu();
            break;
            case 3:
                 displayLoanMenu();
                 break ;
            case 4:
                 displayReportMenu();
                 break ;
            case 5:
                System.out.println("Exiting...");
                break  ;
            default:
                System.out.println("Invalid option , Please try again: "); 
            }
        } while ( choice != 5);  
    }
    public void displayMemberMenu(){
        int choice; 
        do{
            System.out.println("Loading the Manage Members section...");
            System.out.println("============== Manage Members ======================");
            System.out.println("| Please choose one of the following operations:   |");
            System.out.println("====================================================");
            System.out.println("| 1) Add undergraduate Student                     |");
            System.out.println("====================================================");
            System.out.println("| 2) Add Graduate Student                          |");
            System.out.println("====================================================");
            System.out.println("| 3) Search Student by ID                          |");
            System.out.println("====================================================");
            System.out.println("| 4) Delete Student by ID                          |");
            System.out.println("====================================================");
            System.out.println("| 5) See list of all students                      |");
            System.out.println("====================================================");
            System.out.println("| 6) Return to the Main Menu                       |");
            System.out.println("====================================================");
            choice  = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1 :
                    System.out.println("Student ID: ");
                    String undergraduateID = scanner.nextLine(); 
                    System.out.println("First Name:"); 
                    String undergraduateFirstName = scanner.nextLine();
                    System.out.println("Last Name:"); 
                    String undergraduateLastName = scanner.nextLine();
                    System.out.println("Major:");
                    String undergraduateMajor = scanner.nextLine() ;
                    System.out.println("Enrollment Year: ");
                    String undergraduateYear = scanner.nextLine() ; 
                    // scanner.nextLine(); 
                    LibraryController.addUndergraduateStudent(undergraduateID, undergraduateFirstName, undergraduateLastName, undergraduateMajor, undergraduateYear);
                    break ; 
                case 2:
                    System.out.println("Student ID: ");
                    String GraduateID = scanner.nextLine(); 
                    System.out.println("First Name:"); 
                    String GraduateFirstName = scanner.nextLine();
                    System.out.println("Last Name:"); 
                    String GraduateLastName = scanner.nextLine();
                    System.out.println("Major:");
                    String GraduateMajor = scanner.nextLine() ;
                    System.out.println("Thesis Title: ");
                    String GraduateThesis = scanner.nextLine() ; 
                    System.out.println("Supervisor: ");
                    String GraduateSupervisor = scanner.nextLine(); 
                    // scanner.nextLine(); 
                    LibraryController.addGraduateStudent(GraduateID, GraduateFirstName, GraduateLastName, GraduateMajor, GraduateSupervisor, GraduateThesis);
                    break ; 
                case 3 :
                     System.out.println("Enter StudentID to search: ");
                     String searchID = scanner.nextLine();
                     LibraryController.searchStudent(searchID);
                     break ;

                case 4 : 
                    System.out.println("Enter Student ID to remove: ");
                    String deleteID  = scanner.nextLine() ;
                    System.out.println("DEBUG: Attempting to remove student with ID: " + deleteID);                   
                    LibraryController.removeStudent(deleteID) ;
                    break ;
                case 5 : 
                     LibraryController.displayAllStudents() ;
                     break ;
                case 6 : 
                    System.out.println("Redirecting to the Main Menu..."); 
                    return ; 
                default : 
                    System.out.println("Invalid Option , please try again");  
                           

            }  

        } while( true) ;   
    }
    public void displayBookMenu(){
        int choice;
        do {
            System.out.println("Loading the Manage Books section...") ;
            System.out.println("============== Manage Books ========================");
            System.out.println("| Please choose one of the following operations:   |");
            System.out.println("====================================================");
            System.out.println("| 1) Add Book                                      |");
            System.out.println("====================================================");
            System.out.println("| 2) Delete Book by ISBN                           |");
            System.out.println("====================================================");
            System.out.println("| 3) Search Book                                   |");
            System.out.println("====================================================");
            System.out.println("| 4) See List of all Books                         |");
            System.out.println("====================================================");
            System.out.println("| 5) Return to the Main Menu                       |");
            System.out.println("====================================================");
            choice = scanner.nextInt() ;
            scanner.nextLine();
            switch(choice){
                case 1 :
                    System.out.println("Title: ");
                    String title = scanner.nextLine();
                    System.out.println("Author ");
                    String author = scanner.nextLine();
                    System.out.println("ISBN: ");
                    String ISBN = scanner.nextLine();
                    System.out.println("Publication Year: ");
                    String publicationYear = scanner.nextLine() ;
                    LibraryController.addBook(title , author , ISBN , publicationYear) ;
                    break ;
                case 2 :
                    System.out.println("Enter Book ISBN to delete: ");
                    String isbntoDelete = scanner.nextLine() ;
                    LibraryController.removeBook(isbntoDelete) ;
                    break ;
                case 3 :
                    displaySearchMenu();
                    break ;
                case 4:
                    LibraryController.displayAllBooks() ;
                    break ;
                case 5 :
                    System.out.println("Redirecting to the Main Menu");
                    return ;
                default:
                    System.out.println("Invalid option , Please try again: ");
            }
        } while (true) ;
    }
    public void displaySearchMenu(){
        int choice ;
        do {
            System.out.println("Choose one of the following options: ");
            System.out.println("1) Search Book by ISBN");
            System.out.println("2) Search Book by name");
            System.out.println("3) Return to the Manage Books section");
            choice = scanner.nextInt() ;
            scanner.nextLine() ;
            switch(choice){
                case 1 :
                    System.out.println("Enter Book ISBN to search: ");
                    String isbnToSearch  = scanner.nextLine();
                    LibraryController.searchBookbyISBN(isbnToSearch) ;
                    break ;
                case 2 :
                    System.out.println("Enter Book Name to search: ");
                    String bookNametoSearch = scanner.nextLine() ;
                    LibraryController.searchBookbyName(bookNametoSearch) ;
                    break ;
                case 3  :
                    System.out.println("Redirecting to the Manage Books section... ");
                    return ;
                default:
                    System.out.println("Invaid option , Please try again: ");
            }
        } while (true) ;
    }
    public void displayLoanMenu(){
        int choice ;
        do{
            System.out.println("================== Borrow section ====================");
            System.out.println("| Please pick one of the following options:          |");
            System.out.println("======================================================");
            System.out.println("| 1) Borrow Book                                     |");
            System.out.println("======================================================");
            System.out.println("| 2) Return a Borrowed Book                          |");
            System.out.println("======================================================");
            System.out.println("| 3) Return to the Main Menu                         |");
            System.out.println("======================================================");
            choice =  scanner.nextInt() ;
            scanner.nextLine() ;
            switch(choice) {
                case 1:
                    System.out.println("Enter book ISBN to borrow: ");
                    String isbnToBorrow = scanner.nextLine();
                    LibraryController.isBookAvailable(isbnToBorrow);
                    if (!LibraryController.isBookAvailable(isbnToBorrow)) {
                        System.out.println("There is no such book with ISBN " + isbnToBorrow);
                        System.out.println("Please click 1 to go back to the Main Menu");
                        int option = scanner.nextInt();
                        if (option == 1) {
                            System.out.println("Redirecting to the Main Menu...");
                            return;
                        }
                    }
                    System.out.println("Enter the ID of the student who's borrowing: ");
                    String idToBorrow = scanner.nextLine();
                    LibraryController.isStudentEnrolled(idToBorrow);
                    if (!LibraryController.isStudentEnrolled(idToBorrow)) {
                        System.out.println("There is no such student with ID " + idToBorrow);
                        System.out.println("Please click 1 to go back to the Main Menu");
                        int option = scanner.nextInt();
                        if (option == 1) {
                            System.out.println("Redirecting to the Main Menu...");
                            return;
                        }
                    }
                    System.out.println("Enter the Borrow date: ");
                    String loanDate = scanner.nextLine();
                    System.out.println("Enter the Due date: ");
                    String dueDate = scanner.nextLine();
                    if (LibraryController.isBookAvailable(isbnToBorrow) && LibraryController.isStudentEnrolled(idToBorrow)) {
                        LibraryController.AddLoan(idToBorrow, isbnToBorrow, loanDate, dueDate);
                    }
                    break;
                case 2:
                    System.out.println("Enter Book ISBN:");
                    String returnISBN = scanner.nextLine() ;
                    LibraryController.returnBook(returnISBN) ;
                    break ;
                case 3 :
                    System.out.println("Redirecting to the Main Menu...");
                    return ;
                default:
                    System.out.println("Invalid option, Please try again : ");
            }
        } while (true) ;
    }
    public void displayReportMenu(){
        int choice ;
        do{
            System.out.println("=================== Get Report =================");
            System.out.println("Please choose one of the following options");
            System.out.println("1) See List of books in a certain category.");
            System.out.println("2) See List of books borrowed by a Student");
            System.out.println("3) See List of Students studying a certain Major");
            System.out.println("4) Return to the Main Menu");
            System.out.println("=================================================");
            choice = scanner.nextInt() ;
            scanner.nextLine() ;
            switch (choice){
                case 1 :
                    System.out.println("Enter Category Name :");
                    String categoryName = scanner.nextLine() ;
                    LibraryController.listOfbooksinCategory(categoryName) ;
                    break ;
                case 2  :
                    System.out.println("Enter Student ID");
                    String reportID = scanner.nextLine() ;
                    LibraryController.listOfBorrowedBooks(reportID) ;
                    break ;
                case 3 :
                    System.out.println("Enter the Major Name");
                    String majorName = scanner.nextLine() ;
                    LibraryController.listOfstudentsbyMajor(majorName) ;
                    break ;
                case 4 :
                    System.out.println("Redirecting to the Main Menu...");
                    return ;
                default:
                    System.out.println("Invalid option , Please try again: ");
            }
        } while(true) ;
    }
}