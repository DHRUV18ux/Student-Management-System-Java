
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
  import java.io.File;
import java.io.IOException;// this for the jackson thing 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class Student {
   private  String name;
   private int rollno;
    private  List<Integer>marks;
     static int  rollNoCounter=1000;
     Student() {}
    Student(String name,List<Integer>marks ){
       this.rollno= ++rollNoCounter;
     this.name=name;
     this.marks=marks;
    }// we have taken the name and marks of a student now we take average of all marks 
    public String getName(){
        return name;
    }
    public void setName(String name){
      this.name=name;
    }
    public List<Integer>getMarks(){
        ArrayList<Integer>newList=new ArrayList<>(marks);
        return newList;
    }
    public void setMarks(List<Integer>marks){
      this.marks=marks;
    }
    public int getRollno(){
     return rollno;
    }
    @JsonIgnore
    public String getGrade(){
     double average=getAverage();
     if(average>=90){
      return "A+";
     }
     else if(average>=80){
      return "A";
     }
     else if(average>=70){
      return "B+";
     }
     else if(average>=60){
      return "B";
     }
     else if(average>=50){
      return "C";
     }
     else{
      return "F";
     }
    }
    @JsonIgnore
    public double getAverage(){
        int sum=0;
     for(int m:marks){
      sum+=m;
     }
      return (double)sum/marks.size();
    }
    @Override
    public String toString(){
    return "Roll No :" + rollno+", Name :" +name +", Marks :"+ getMarks()+", Average:" +getAverage()+",Grade:" +getGrade();
     }
     public int getGradeRank(){
      switch(getGrade()){
       case "A+": return 6;
       case "A"  : return 5;
       case "B+" : return 4;
       case "B"  : return 3;
       case "C"  : return 2;
       case "F"  : return 1;
       default :  return 0;
      }
     }
}
 class StudentNotFoundException extends Exception{
           StudentNotFoundException(String message){
           super(message);
           }
 }

 class InvalidMarksException extends Exception{
             InvalidMarksException(String message){
             super(message);
             }
 }
 class InvalidIndexException extends Exception{
   InvalidIndexException(String message){
     super(message);
   }
 }

 
  
public class miniproject1{

       static  Map<Integer,Student>students=new HashMap<>();
        static  Scanner sc= new Scanner(System.in);
        
        public static void saveToJson(){
          try{
          ObjectMapper mapper= new ObjectMapper();
          mapper.writeValue(new File("students.json"),students);
          System.out.println("Data saved to JSON file");
          }
          catch(IOException e){
            System.out.println("error saving json "+e.getMessage());
          }
        }

       public static void loadFromJson(){
        try{
         ObjectMapper mapper= new ObjectMapper();
         File file= new File("students.json");
         if(!file.exists()){
          System.out.println("No previous json data found , Starting fresh ");
          return;
         }
         students=mapper.readValue(file,new TypeReference<Map<Integer,Student>>(){});
         int maxRollno=1000;
         for(Integer rollno:students.keySet()){
          if(rollno>maxRollno){
            maxRollno=rollno;
          }
         }
         Student.rollNoCounter=maxRollno;
         System.out.println("Data loaded from JSON file");
        }
        catch(IOException e){
        System.out.println("Error loading JSON "+e.getMessage());
        }
       }

   public static void main(String args[]){
      loadFromJson();
      System.out.println("you are in main menu");
      while(true){
       System.out.println("Student Management System");
        System.out.println("1. Add student");
      System.out.println("2. View all students");
     System.out.println("3. Update student");
      System.out.println("4. Delete student");
      System.out.println("5. Search student by name");
      System.out.println("6. Sort Students ");
      System.out.println( "7. exit ");
      int choice=sc.nextInt();
      sc.nextLine();
       if(choice==1){
        try{
           addStudent();
        }
        catch(InvalidMarksException e){
        System.out.println(e.getMessage());
        }
       }
       else if(choice==2){
        viewStudent();
       }
       else if(choice==3){
        try{
       updateStudent();
       }
       catch(StudentNotFoundException e){
        System.out.println(e.getMessage());
       }
       catch(InvalidMarksException e){
        System.out.println(e.getMessage());
       }
       catch(InvalidIndexException e){
        System.out.println(e.getMessage());
       }
       }
       else if(choice==4){
        try{
           deleteStudent();
        }
        catch(StudentNotFoundException e){
          System.out.println(e.getMessage());
        }
       }
       else if(choice==5){
          searchStudentByPartialName();
       }
       else if(choice==6){
        sortStudents();
       }
       else if(choice==7){
        saveToJson();
        System.out.println("program terminates");
        break;
       }
       else{
        System.out.println("Invalid Search");
       }
      }
   }
     
     // add Student 
      public  static void addStudent() throws InvalidMarksException{
          System.out.println("Enter the name of the student");
          String name=sc.nextLine();
          List<Integer>marks=new ArrayList<>();
          System.out.println("Enter the marks of the student");
          for(int i=0;i<3;i++){
           int m=sc.nextInt();
           if(m>=0 && m<=100){
               marks.add(m);
           }
           else{
          throw new InvalidMarksException("Invalid marks. Please enter values between 0 and 100.");
          }
        }
          sc.nextLine();
          Student newStudent=new Student(name,marks);
          students.put(newStudent.getRollno(),newStudent);
          System.out.println("Student added succsessfully");
      }
      // View all students Details
      public  static void viewStudent(){
          if(students.isEmpty()){
          System.out.println("There is no students in the dataBase");
          return;
          }

       System.out.println("Details of all students are :");
       for(Student s:students.values()){ // we are using the .values() cux we want the objets in the map 
        System.out.println("NAME :"+s.getName());
        System.out.println("ROLLNO :" +s.getRollno());
        System.out.println("MARKS :"+ s.getMarks());
        System.out.println("Average of all marks :"+ s.getAverage());
        System.out.println("grade of Student :"+s.getGrade());
        System.out.println("-------------------------------------------");
       }

      }
    // update student 
    public static  void updateStudent() throws StudentNotFoundException,InvalidMarksException,InvalidIndexException
     {
     System.out.println("enter the Roll number of the student  to update");
       int rollNo=sc.nextInt();
       sc.nextLine();
      Student found=students.get(rollNo);
      if(found!=null){
        System.out.println("Student Found "+found.getName());
        int choice=0;
        while(choice!=5){
        System.out.println("what do you want to update?");
         System.out.println("Enter 1 to update name");
        System.out.println("Enter 2 to replace all marks");
        System.out.println("Enter 3 to update  one mark");
        System.out.println("Enter 4 to add ONE mark ");
        System.out.println("Enter 5 to GO back!!");
         choice=sc.nextInt();
         sc.nextLine();
         if(choice==1){
      System.out.println("enter the new name of the student ");
      String newname=sc.nextLine();
      found.setName(newname);
      System.out.println("newname updates successfully");
         }
         else if(choice==2){
        System.out.println("now enter the new updated marks ");
         int size=found.getMarks().size();
         List<Integer>newMarks=new ArrayList<>();
         for(int i=0;i<size;i++){
         System.out.println("enter the marks of subject "+(i+1));
         int m=sc.nextInt();
         if(m>=0 && m<=100){
               newMarks.add(m);
         }
         else{
           throw new InvalidMarksException("Invalid marks ,Marks should be from 0 to 100");
         }
         }
         sc.nextLine();
         found.setMarks(newMarks);
         System.out.println("Marks updated successfully");
         }
         else if(choice==3){
         List<Integer>temp=found.getMarks();
          int size=temp.size();
          System.out.println("These are the marks of the student ");
          for(int i=0;i<size;i++){
          System.out.println( (i+1) +"."+temp.get(i));
          }
          System.out.println("Enter the subject number whose marks you want to update");
          int index=sc.nextInt();
            sc.nextLine();
          if(index<1 || index>size){
          throw new InvalidIndexException("INVALID SUBJECT NUMBER !!!");
          }
          System.out.println("Enter the marks for this "+index+"subject");
          int m=sc.nextInt();
          if(m<0 || m>100){
          throw new InvalidMarksException("Invalid marks ,Marks should be from 0 to 100");
          }
          sc.nextLine();
          temp.set(index-1,m);
          found.setMarks(temp);
         System.out.println("Marks updated successfully");
         }
         else if(choice==4){
          System.out.println("now we are adding one subject marks ");
          List<Integer>temp=new ArrayList<>(found.getMarks());
          System.out.println("enter the  subject marks you want to add");
          int m=sc.nextInt();
          if(m<0 || m>100){
          throw new InvalidMarksException("Invalid marks ,Marks should be from 0 to 100");
          }
          sc.nextLine();
          temp.add(m);
          found.setMarks(temp);
          System.out.println("New subject marks added successfully");
         }
         else if(choice==5){
         System.out.println("Going back to main menu...");
         }
         else{
        System.out.println("Invalid choice, please try again.");
         }
        }
      }
      else{
         throw new StudentNotFoundException("The requested roll doest not exist in the database");
      }
    }
    // now method for deletion 
        public  static void deleteStudent() throws StudentNotFoundException{
        System.out.println("Enter the RollNo of  the student you want to delete");
        int rollNo=sc.nextInt();
        sc.nextLine(); 
          Student removed= students.remove(rollNo);
          if(removed!=null){
           System.out.println("Student with "+rollNo +"removed successfully");
          } 
          else{
          throw new StudentNotFoundException("The requested roll no which you want to delete doesnot exist in the data base");
          }
        }
        // partial search 
        public   static void searchStudentByPartialName(){
          System.out.println("Enter some partial thing that comes in the name of the student you want to search");
          String text=sc.nextLine();
          List<Student>temp=new ArrayList<>();
          boolean flag=false;
          for(Student s:students.values()){
           if(s.getName().toLowerCase().contains(text.toLowerCase())){
             flag=true;
             temp.add(s);
           }
          }
          if(flag==false){
          System.out.println("The partial text is not able to match with any name in database");
          }
          else{
            System.out.println("maching students are");
            for(int i=0;i<temp.size();i++){
             System.out.println((i+1) + "name :" +temp.get(i).getName());
            }
            System.out.println("Do you want details of the any maching student if want press the student  number ");
            int choice=sc.nextInt();
            sc.nextLine();
            if(choice<1 || choice>temp.size()){
            System.out.println("Invalid selection!");
                 return;
            }
             Student selected=temp.get(choice-1);
             System.out.println("Selcted student details are :");
             System.out.println("NAME :" + selected.getName());
             System.out.println("MARKS:"+ selected.getMarks());
             System.out.println("AVERAGE:"+ selected.getAverage());
             System.out.println("Grade:" +selected.getGrade());
             System.out.println("------------------------------");
          }
          }
          // sort students
           public static void sortStudents(){
            if(students.isEmpty()){
             System.out.println("No students available to sort");
             return;
            }
           System.out.println("How You want to sort the students");
           System.out.println("Enter 1 to Sort student by name");
           System.out.println("Enter 2 to Sort student by Roll No ");
           System.out.println("Entet 3 to sort students by there  average marks");
           System.out.println("Enter 4 to sort students by there  Grade ");
           System.out.println(" Enter 5 to exit");
           while(true){
            System.out.println("Enter the choice you want to choose");
           int choice=sc.nextInt();
           sc.nextLine();
           if(choice==1){
           students.values().stream().sorted((a,b)->a.getName().compareTo(b.getName())).forEach(System.out::println);
           }
           else if(choice==2){
            students.values().stream().sorted((a,b)->a.getRollno()-b.getRollno()).forEach(System.out::println);
           }
           else if(choice==3){
           students.values().stream().sorted((a,b)->Double.compare(b.getAverage(),a.getAverage())).forEach(System.out::println);
           }
           else if(choice==4){
           students.values().stream().sorted((a,b)->Integer.compare(b.getGradeRank(),a.getGradeRank())).forEach(System.out::println);
           }
           else if(choice==5){
           System.out.println("sorted done now exit ");
           break;
           }
           else{
            System.out.println("Invalid choice plx choose the valid choice ");
           }
           }
           }
        }

