
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
class Student{
   private  String name;
   private int rollno;
    private  List<Integer>marks;
    private static int  rollNoCounter=1000;
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
        ArrayList<Integer>newList=new ArrayList(marks);
        return newList;
    }
    public void setMarks(List<Integer>marks){
      this.marks=marks;
    }
    public int getRollno(){
     return rollno;
    }
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
    public double getAverage(){
        int sum=0;
     for(int m:marks){
      sum+=m;
     }
      return (double)sum/marks.size();
    }
}
  
public class miniproject1{

       static ArrayList<Student>students=new ArrayList<>();
        static  Scanner sc= new Scanner(System.in);

   public static void main(String args[]){
      System.out.println("you are in main menu");
      while(true){
       System.out.println("Student Management System");
        System.out.println("1. Add student");
      System.out.println("2. View all students");
     System.out.println("3. Update student");
      System.out.println("4. Delete student");
      System.out.println("5. Search student by name");
      System.out.println("6. Exit");
      int choice=sc.nextInt();
      sc.nextLine();
       if(choice==1){
       addStudent();
       }
       else if(choice==2){
        viewStudent();
       }
       else if(choice==3){
       updateStudent();
       }
       else if(choice==4){
        deleteStudent();
       }
       else if(choice==5){
          searchStudentByPartialName();
       }
       else if(choice==6){
        System.out.println("program terminates");
        break;
       }
       else{
        System.out.println("Invalid Search");
       }
      }
   }
     
     // add Student 
      public  static void addStudent(){
          System.out.println("Enter the name of the student");
          String name=sc.nextLine();
          List<Integer>marks=new ArrayList<>();
          System.out.println("Enter the marks of the student");
          for(int i=0;i<3;i++){
           int m=sc.nextInt();
           marks.add(m);
          }
          sc.nextLine();
          Student newStudent=new Student(name,marks);
          students.add(newStudent);
          System.out.println("Student added succsessfully");
      }
      // View all students Details
      public  static void viewStudent(){
          if(students.isEmpty()){
          System.out.println("There is no students in the dataBase");
          return;
          }

       System.out.println("Details of all students are :");
       for(Student s:students){
        System.out.println("NAME :"+s.getName());
        System.out.println("ROLLNO :" +s.getRollno());
        System.out.println("MARKS :"+ s.getMarks());
        System.out.println("Average of all marks :"+ s.getAverage());
        System.out.println("grade of Student :"+s.getGrade());
        System.out.println("-------------------------------------------");
       }

      }
    // update student 
    public static  void updateStudent(){
     System.out.println("enter the name of the student  to update");
      String toFound=sc.nextLine();
         Student found=null;
      for(Student s:students ){
       if(s.getName().equalsIgnoreCase(toFound)){
           found=s;
           break;
       }
      }
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
         newMarks.add(m);
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
          System.out.println("INVALID SUBJECT NUMBER !!!");
          return;
          }
          System.out.println("Enter the marks for this "+index+"subject");
          int m=sc.nextInt();
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
        System.out.println("student not found");
      }
    }
    // now method for deletion 
        public  static void deleteStudent(){
        System.out.println("Enter the name of  the student you want to delete");
        String name=sc.nextLine().trim();
       
        Student found=null;
        for(Student s: students){
         if(s.getName().equalsIgnoreCase(name)){
         found =s;
         break;
         }
        }
        if(found!=null){
        System.out.println("Student found "+name);
        students.remove(found);
        System.out.println("student removed or deleted successfully ");
        }
        else{
          System.out.println("student doesnot exist in the database ");
        }
        }
        // partial search 
        public   static void searchStudentByPartialName(){
          System.out.println("Enter some partial thing that comes in the name of the student you want to search");
          String text=sc.nextLine();
          List<Student>temp=new ArrayList<>();
          boolean flag=false;
          for(Student s:students){
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
             System.out.println((i+1)+"."+temp.get(i).getName());
            }
            System.out.println("Do you want details of the any maching student if want press the student name number ");
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
        }

