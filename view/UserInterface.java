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
            System.out.println("======= Alireza's Library Management Application =======");
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
            switch (choice){
            case 1:
                displayMemberMenu();
            break;
            case 2:
                // displayBookMenu();
                {}
            break;
            case 3:
                // displayLoanMenu();
                {}
            break;
            case 4:
                // displayReportMenu();
                {}
            break;
            case 5:
                System.out.println("Exiting...");
            break;
            default:
                System.out.println("Invalid option , Please try again: "); 
            }
        } while ( choice != 5);  
    }
    public void displayMemberMenu(){
        int choice; 
        do{
            System.out.println("Loading the Manage Members section...");
            System.out.println("*************************************************");
            System.out.println("* Manage Members                                *");
            System.out.println("*************************************************");
            System.out.println("* Please choose one of the following operations: *");
            System.out.println("*************************************************");
            System.out.println("* 1) Add undergraduate Student                  *");
            System.out.println("*************************************************");
            System.out.println("* 2) Add Graduate Student                       *");
            System.out.println("*************************************************");
            System.out.println("* 3) Search Student by ID                       *");
            System.out.println("*************************************************");
            System.out.println("* 4) Delete Student by ID                       *");
            System.out.println("*************************************************");
            System.out.println("* 5) See list of all students                   *");
            System.out.println("*************************************************");
            System.out.println("* 6) Return to Main Menu                        *");
            System.out.println("*************************************************");
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
                    System.out.println("Enrollment Year");
                    String undergraduateYear = scanner.nextLine() ; 
                    scanner.nextLine(); 
                    LibraryController.addUndergraduateStudent(undergraduateFirstName , undergraduateLastName , undergraduateID , undergraduateMajor , undergraduateYear);
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
                    scanner.nextLine(); 
                    LibraryController.addGraduateStudent(GraduateFirstName , GraduateLastName , GraduateID , GraduateMajor , GraduateSupervisor , GraduateThesis);
                    break ; 
                case 3 :
                     System.out.println("Enter StudentID to search: ");
                     String searchID = scanner.nextLine();
                     LibraryController.searchStudent(searchID);
                     break ;

                case 4 : 
//                     System.out.println("Enter Student ID to remove: ");
//                     String deleteID  = scanner.nextLine() ;
//                     LibraryController.removeStudent(deleteID) ;
//                     break ;
                {}
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
}