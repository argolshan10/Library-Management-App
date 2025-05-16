package model;

public class UndergraduateStudent extends Student{ 
    public String enrollmentYear ; 
    public UndergraduateStudent(String undergraduateFirstName ,String  undergraduateLastName ,String  undergraduateID ,String  undergraduateMajor , String undergraduateYear){
        this.studentID = undergraduateID  ; 
        this.firstName = undergraduateFirstName ; 
        this.lastName = undergraduateLastName ; 
        this.major = undergraduateMajor ; 
        this.enrollmentYear = undergraduateYear ; 
    }
    @Override 
    public void getStudentDetails(){ 

    }
    @Override
    public String toString(){
        return super.toString() + "," + enrollmentYear ;
    }
}
