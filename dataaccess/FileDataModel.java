package dataaccess ;
import java.io.File;
import java.io.BufferedReader ;
import java.io.IOException; 
import java.io.FileWriter;
import java.io.FileReader ;
import java.util.List;
import model.Student;
import java.util.ArrayList ;
import model.UndergraduateStudent ;
import  model.GraduateStudent ;
public class FileDataModel {  
    private String stufilepath ; 
    public FileDataModel(String stufilepath){  
        this.stufilepath = stufilepath ;   
        try{
            File myStudents = new File(stufilepath) ; 
            if (myStudents.createNewFile()){
                System.out.println("File created"); 
            } else {
                System.out.println("File already exists.");
            }
        } catch(IOException e){
            System.out.println("An error occured."); 
        }
    }
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(stufilepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 5) {
                    // undergraduate student: ID, firstName, lastName, major, enrollmentYear
                    UndergraduateStudent ug = new UndergraduateStudent(parts[1], parts[2], parts[0], parts[3], parts[4]);
                    students.add(ug);
                } else if(parts.length >= 6) {
                    // graduate student: ID, firstName, lastName, major, supervisor, thesisTitle (may contain commas)
                    String thesisTitle = String.join(",", java.util.Arrays.copyOfRange(parts, 5, parts.length));
                    GraduateStudent grad = new GraduateStudent(parts[0], parts[1], parts[2], parts[3], parts[4], thesisTitle);
                    students.add(grad);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    public void saveStudents(List<Student> students, String filePath){
//        this.stufilepath = filePath  ;
        try(FileWriter myWriter = new FileWriter(filePath) ){
             for (Student s : students) {
                myWriter.write(s.toString() + "\n");
            } 
//             myWriter.close();
             System.out.println("Student saved successfully");
        } catch (IOException  e){ 
            System.out.println("An error ocurred" + e.getMessage());
        }
       
    }
}