package controller ; 
import java.util.List;
import model.Student;
import model.UndergraduateStudent;
import dataaccess.FileDataModel ; 
import java.util.ArrayList;
import model.GraduateStudent ;
public class LibraryController{
    private static final String studentFileName = "students.txt";
    private List<Student> students ; 
    private FileDataModel filedatamodel ;  
    public LibraryController(){
        this.students = new ArrayList<> () ; 
        this.filedatamodel = new FileDataModel("students.txt"); 
         loanStudentsFromFile() ;
    }
    public void addGraduateStudent(String StudentID ,String firstName , String lastName , String major , String supervisor, String thesisTitle){
        GraduateStudent newGraduateStudent = new GraduateStudent( StudentID , firstName ,  lastName ,  major ,  supervisor,  thesisTitle) ;
        students.add(newGraduateStudent) ; 
        saveStudentstoFile() ; 
        System.out.println("Graduate Student with the ID " + StudentID + " has been added."); 
    } 
    public void addUndergraduateStudent(String undergraduateFirstName ,String  undergraduateLastName ,String  undergraduateID ,String  undergraduateMajor , String undergraduateYear){
        UndergraduateStudent newUndergraduateStudent = new UndergraduateStudent(undergraduateFirstName, undergraduateLastName, undergraduateID, undergraduateMajor, undergraduateYear);
        students.add(newUndergraduateStudent) ; 
        saveStudentstoFile();
        System.out.println("Undergraduate Student with ID  " + undergraduateID + " has been added. ");
    }
    private void saveStudentstoFile(){
        filedatamodel.saveStudents(students ,studentFileName);
    }
    private void loanStudentsFromFile(){
        this.students = filedatamodel.loadStudents() ;
        System.out.println("Student's information has been loaded.");
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
    public void displayAllStudents(){
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
}