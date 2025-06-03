package org.example.dataaccess ;
import java.io.*; 
import java.util.*;
import java.lang.Boolean ;


import javafx.scene.layout.VBox;
import org.example.model.Loan ;
import org.example.model.Book;
import org.example.model.Category ;
import org.example.model.Student;
import java.util.Scanner ;
import org.example.model.UndergraduateStudent ;
import  org.example.model.GraduateStudent ;
import org.example.Main ;
public class FileDataModel {
    private String stufilepath ;
    private String bookfilepath ;
    private String LoanFilepath;
    private String categoryFilepath ;
    public FileDataModel(String stufilepath , String bookfilepath , String LoanFilepath , String categoryFilepath){
        this.stufilepath = stufilepath ;
        this.bookfilepath = bookfilepath ;
        this.LoanFilepath = LoanFilepath ;
        this.categoryFilepath = categoryFilepath ;
        try{
            File myCategories = new File(categoryFilepath) ;
            if (myCategories.createNewFile()){
                System.out.println("Category File Created");
            } else {
                System.out.println("Category File already exits.");
            }
        } catch (IOException e){
            System.out.println("An error occurred while trying to create the category file.");
        }
        try{
            File myLoans = new File(LoanFilepath) ;
            if (myLoans.createNewFile()){
                System.out.println("Loan File Created") ;
            } else {
                System.out.println("Loan File already exists") ;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the loan file") ;
        }
        try{
            File myStudents = new File(stufilepath) ; 
            if (myStudents.createNewFile()){
                System.out.println("Student File created");
            } else {
                System.out.println("Student File already exists.");
            }
        } catch(IOException e){
            System.out.println("An error occurred trying to create the Student File.");
        }
        try{
            File myBooks = new File(bookfilepath) ;
            if (myBooks.createNewFile()){
                System.out.println("Book File Created");
            } else {
                System.out.println("Book File already exists.");
            }
        } catch (IOException e){
            System.out.println("An Error occurred trying to create the Book File.");
        }
    }
    public List<Loan> loadLoans(){
        List<Loan> loans = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(LoanFilepath))) {
            String line ;
            while((line = br.readLine()) != null){
                String [] parts = line.split(",");
                if (parts.length == 4){
                    Loan l = new Loan();
                    l.getStudent(parts[0]) ;
                    l.getBook(parts[1]) ;
                    l.getLoanDate(parts[2]);
                    l.getDuedate(parts[3]);
                    loans.add(l) ;
                }
            }
        } catch (IOException e ){
            System.out.println("An error occurred trying to load loans.");
        }
        return loans ;
    }
    public  List<Book> loadBooks(){
        List<Book> books = new ArrayList<>() ;
        try (BufferedReader br = new BufferedReader(new FileReader(bookfilepath))){
            String  line ;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",") ;
                if(parts.length == 5) {
                    Book b = new Book() ;
                    b.setDetails(parts[0] , parts [1] ,parts[2] , parts[3]);
                    b.isBorrowed = Boolean.parseBoolean(parts[4]) ;
                    books.add(b) ;
                }
            }
        } catch (IOException e){
            System.out.println("Error occurred trying to load Books.");
        }
        return books ;
    }
    public List<Category> loadCategories() {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(categoryFilepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Category cat = new Category();
                    cat.getBookName(parts[0].trim());  // Correct setter
                    cat.setName(parts[1].trim());
                    cat.setDescription(parts[2].trim());
                    categories.add(cat);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred trying to Load Categories");
            e.printStackTrace();  // Optional: for better debugging
        }
        return categories;
    }
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(stufilepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 5) {
                    UndergraduateStudent ug = new UndergraduateStudent(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4]
                    );

                    students.add(ug);
                } else if(parts.length >= 6) {
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
    public boolean saveLoans(List<Loan> loans , String filepath){
        try(FileWriter myWriter = new FileWriter(filepath)){
            for (Loan loan : loans){
                myWriter.write(loan.toString() + "\n") ;
            }
            return true ;
        } catch (IOException e) {
            return false ;
        }
    }
    public  boolean saveBooks(List<Book> books , String filepath){
        try (FileWriter myWriter = new FileWriter(filepath)){
            for (Book ketab : books){
                myWriter.write(ketab.toString() + "\n");
            }
            return true ;
        } catch (IOException e){
            return false ;
        }
    }

    public boolean saveBooktoCategory( String theCategory, String booktitle , List<Category> categories , String filepath){
        Category categ = new Category() ;
        categ.getBookName(booktitle);

        if (theCategory.equals("1")){
            categ.setName("Computer Science");
            categ.setDescription("This category is about computer science , algorithms and programming.");
            categories.add(categ);
            try(FileWriter myWriter = new FileWriter(filepath)){
                for (Category c : categories){
                    myWriter.write(c.toString() + "\n") ;
                }
                return true ;
            } catch (IOException e) {
                return false ;
            }
        } else if (theCategory.equals("2")){
            categ.setName(("Electrical Engineering"));
            categ.setDescription("This category is about electrical engineering");
            categories.add(categ);
            try(FileWriter myWriter = new FileWriter(filepath)){
                for (Category c : categories){
                    myWriter.write(c.toString() + "\n") ;
                }
                return true ;
            } catch (IOException e) {
                return false ;
            }
        } else if (theCategory.equals("3")){
            categ.setName("History");
            categ.setDescription("This category is about History");
            categories.add(categ);
            try(FileWriter myWriter = new FileWriter(filepath)){
                for (Category c : categories){
                    myWriter.write(c.toString() + "\n") ;
                }
                return true ;
            } catch (IOException e) {
                return false ;
            }
        } else {
            categ.setName("4");
            categ.setDescription("This category is about novels.");
            categories.add(categ);
            try(FileWriter myWriter = new FileWriter(filepath)){
                for (Category c : categories){
                    myWriter.write(c.toString() + "\n") ;
                }
                return true ;
            } catch (IOException e) {
                return false ;
            }
        }
    }
    public String saveStudents(List<Student> students, String filePath ){
        try(FileWriter myWriter = new FileWriter(filePath) ){
             for (Student s : students) {
                myWriter.write(s.toString() + "\n");
            }

            return "Student has been Successfully added.";

        } catch (IOException  e){ 
            return "An error occurred" ;
        }
       
    }
    public boolean deleteBooks(String deleteISBN , String filepath){
        List<Book> books = loadBooks() ;
        boolean removed = false ;
        Iterator<Book> iterator = books.iterator() ;
        while(iterator.hasNext()){
            Book bo = iterator.next();
            if (bo.getBookISBN().trim().equalsIgnoreCase(deleteISBN.trim())){
                iterator.remove();
                removed = true ;
                break ;
            }
        }
        if (removed){
            saveBooks(books , filepath);
            return true ;
        } else {
           return false ;
        }
    }
    public boolean returnBook(String returnISBN , String filepath){
        List<Loan> loans = loadLoans() ;
        boolean returned  = false ;
        Iterator<Loan> iterator = loans.iterator() ;
        while(iterator.hasNext()) {
            Loan lo = iterator.next();
            if(lo.getLoanISBN().trim().equalsIgnoreCase(returnISBN.trim())){
                iterator.remove();
                returned = true ;
                break ;
            }
        }
        if(returned){
            saveLoans(loans , filepath);
            return true ;
        } else{
            return false ;
        }
    }
    public boolean deleteStudents(String deleteID , String filePath){

        List<Student> students = loadStudents();

        
        boolean removed = false;

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getStudentID().trim().equalsIgnoreCase(deleteID.trim())) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            saveStudents(students, filePath);
            return true ;

        } else {
            return false ;
        }
    }

    public String listOfBorrowedBooks(String id){
        StringBuilder result = new StringBuilder();
        List<Loan> loans = loadLoans();

        for (Loan l : loans) {
            if (id.equalsIgnoreCase(l.student)) {
                result.append("Book ISBN: ").append(l.book).append(" | ")
                        .append("Student ID: ").append(l.student).append(" | ")
                        .append("Date of Borrow: ").append(l.loanDate).append(" | ")
                        .append("Due Date: ").append(l.dueDate).append("\n");
            }
        }

        if (result.length() == 0) {
            return "No such student was found.";
        } else {
            return result.toString();
        }
    }
    public String listOfstudentsbyMajor(String majorName){
        StringBuilder result = new StringBuilder();
        List<Student> students = loadStudents();

        for (Student s : students) {
            if (majorName.equalsIgnoreCase(s.major)) {
                result.append("First Name: ").append(s.firstName).append(" | ")
                        .append("Last Name: ").append(s.lastName).append("\n");
            }
        }

        if (result.length() == 0) {
            return "No such major was found.";
        } else {
            return result.toString();
        }
    }
    public String listOfbooksinCategory(String categoryName) {
        List<Category> categories = loadCategories();
        StringBuilder result = new StringBuilder();

        String searchName = categoryName.trim().toLowerCase();
        System.out.println("Searching for category: '" + categoryName.trim().toLowerCase() + "'");
        for (Category c : categories) {
            System.out.println("Loaded category: '" + c.name.trim().toLowerCase() + "'");
        }

        for (Category c : categories) {
            if (c.name != null && c.name.trim().toLowerCase().equals(searchName)) {
                result.append("Book Name: ").append(c.bookName).append(" | ")
                        .append("Category Name: ").append(c.name).append(" | ")
                        .append("Category Description: ").append(c.description).append("\n");
            }
        }

        if (result.length() == 0) {
            return "No such category was found.";
        }
        return result.toString();
    }


}
