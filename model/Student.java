package model;

abstract public class Student {
    public String studentID ;
    public String firstName ; 
    public String lastName ; 
    public String major ; 
    abstract void getStudentDetails() ;
    public String getStudentID() {
        return studentID;
    }
    @Override
    public String toString() {
        return studentID + "," + firstName + "," + lastName + "," + major;
    }
}
 