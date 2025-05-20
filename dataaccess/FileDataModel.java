package dataaccess ;
import java.io.*; 
import java.util.*;
import java.lang.Boolean ;
import model.Loan ;
import model.Book;
import model.Category ;
import model.Student;
import java.util.Scanner ;
import model.UndergraduateStudent ;
import  model.GraduateStudent ;
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
                    cat.getBookName(parts[0]);
                    cat.setName(parts[1]);
                    cat.setDescription(parts[2]);
                    categories.add(cat);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred trying to Load Categories");
        }
        return categories ;
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
    public void saveLoans(List<Loan> loans , String filepath){
        try(FileWriter myWriter = new FileWriter(filepath)){
            for (Loan loan : loans){
                myWriter.write(loan.toString() + "\n") ;
            }
            System.out.println("Loans saved Successfully");
        } catch (IOException e) {
            System.out.println("An error occurred trying to save loans: " + e.getMessage());
        }
    }
    public  void saveBooks(List<Book> books , String filepath){
        try (FileWriter myWriter = new FileWriter(filepath)){
            for (Book ketab : books){
                myWriter.write(ketab.toString() + "\n");
            }
            System.out.println("Books saved successfully");
        } catch (IOException e){
            System.out.println("An error occurred");
        }
    }

    public void saveBooktoCategory(String booktitle , List<Category> categories , String filepath){
        Category categ = new Category() ;
        categ.getBookName(booktitle);
        System.out.println("Which one of the following categories does the book belong to?");
        System.out.println("1) Computer Science");
        System.out.println("2) Electrical Engineering");
        System.out.println("3) History");
        System.out.println("4) Novel");
        Scanner sc = new Scanner(System.in) ;
        int thechoice = sc.nextInt() ;
        switch(thechoice){
            case 1 :
                categ.setName("Computer Science");
                categ.setDescription("This category is about computer science , algorithms and programming.");
                categories.add(categ);
                try(FileWriter myWriter = new FileWriter(filepath)){
                    for (Category c : categories){
                        myWriter.write(c.toString() + "\n") ;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
                break ;
            case 2  :
                categ.setName(("Electrical Engineering"));
                categ.setDescription("This category is about electrical engineering");
                categories.add(categ);
                try(FileWriter myWriter = new FileWriter(filepath)){
                    for (Category c : categories){
                        myWriter.write(c.toString() + "\n") ;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
                break ;
            case 3 :
                categ.setName("History");
                categ.setDescription("This category is about History");
                categories.add(categ);
                try(FileWriter myWriter = new FileWriter(filepath)){
                    for (Category c : categories){
                        myWriter.write(c.toString() + "\n") ;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
                break ;
            case 4:
                categ.setName("Novel");
                categ.setDescription("This category is about novels.");
                categories.add(categ);
                try(FileWriter myWriter = new FileWriter(filepath)){
                    for (Category c : categories){
                        myWriter.write(c.toString() + "\n") ;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
                break ;
            default:
                System.out.println("Invalid option , Please try again: ");
        }
    }
    public void saveStudents(List<Student> students, String filePath){
        try(FileWriter myWriter = new FileWriter(filePath) ){
             for (Student s : students) {
                myWriter.write(s.toString() + "\n");
            }
             System.out.println("Student saved successfully");
        } catch (IOException  e){ 
            System.out.println("An error occurred" + e.getMessage());
        }
       
    }
    public void deleteBooks(String deleteISBN , String filepath){
        System.out.println("Attempting to remove book with ISBN" + deleteISBN);
        List<Book> books = loadBooks() ;
        for(Book b : books){
            System.out.println("Loaded ID: [" + b.getBookISBN() + "]");
        }
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
            System.out.println("Book with ISBN" + deleteISBN + " was deleted from the Library successfully");
        } else {
            System.out.println("Book with ISBN" + deleteISBN + " was not found");
        }
    }
    public void returnBook(String returnISBN , String filepath){
        List<Loan> loans = loadLoans() ;
        for (Loan l : loans){
            System.out.println("Loaded ID: [" + l.getLoanISBN() + "]");
        }
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
            System.out.println("The book has been returned Successfully.");
        } else{
            System.out.println("No such book with this ISBN was found.");
        }
    }
    public void deleteStudents(String deleteID , String filePath){
        System.out.println("Attempting to remove student with ID: " + deleteID);
        List<Student> students = loadStudents();
        for (Student s : students) {
            System.out.println("Loaded ID: [" + s.getStudentID() + "]");
        }
        
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
            System.out.println("Student with ID " + deleteID + " deleted successfully.");
        } else {
            System.out.println("Student with ID " + deleteID + " not found.");
        }
    }
    public void listOfBorrowedBooks(String id){
        List<Loan> loans = loadLoans() ;
        for (Loan l : loans){
            if(id.equals(l.student)){
                System.out.println(l);
            }
        }
    }
    public void listOfstudentsbyMajor(String majorName){
        List<Student> students = loadStudents() ;
        for (Student s : students) {
            if(majorName.equals(s.major)){
                System.out.println(s.firstName + s.lastName);
            }
        }
    }
    public void listOfbooksinCategory(String categoryName) {
        List<Category> categories = loadCategories();
        boolean found = false;

        for (Category c : categories) {
            if (categoryName.equals(c.name)) {
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found in category: " + categoryName);
        }
    }

}  