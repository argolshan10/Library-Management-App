package org.example.model;

public class UndergraduateStudent extends Student{ 
    public String enrollmentYear ; 
    public UndergraduateStudent(String studentID, String firstName, String lastName, String major, String enrollmentYear) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.enrollmentYear = enrollmentYear;
    }
    @Override 
    public void getStudentDetails(){ 

    }
    @Override
    public String toString(){
        return super.toString() + "," + enrollmentYear ;
    }
}
 