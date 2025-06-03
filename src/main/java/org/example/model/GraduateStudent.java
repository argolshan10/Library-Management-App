package org.example.model;

public class GraduateStudent extends Student { 
    public String supervisor ; 
    public String thesisTitle ;
    public GraduateStudent(String StudentID ,String  firstName , String  lastName , String  major , String  supervisor,String   thesisTitle) {
      this.studentID = StudentID ; 
      this.firstName = firstName ; 
      this.major = major ;  
      this.supervisor = supervisor ; 
      this.thesisTitle = thesisTitle ;   
      this.lastName = lastName ; 
    }
    @Override 
    public void getStudentDetails(){ 

    }
    @Override
    public String toString(){
        return super.toString() + "," + supervisor + "," + thesisTitle ;
    }
}
  