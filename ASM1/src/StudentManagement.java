import java.io.ObjectInputStream;
import java.util.*;
import java.io.*;

public class StudentManagement {
    private LinkedList<Classrooms> classrooms;
    SortStudent sortStudent = new SortStudent();
    private static final String FILENAME = "students.txt";
    Scanner sc = new Scanner(System.in);
    private SearchStudent searchStudent;

    public StudentManagement() {
        classrooms = new LinkedList<>();
        loadClassrooms();
        searchStudent = new SearchStudent(sc, this);
    }

    //Print-Start//////////////
    public void printClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("There are no classes available.");
            return;
        }
        System.out.println("Classrooms:");
        int index = 1;
        for (Classrooms classroom : classrooms) {
            System.out.println(index + ". " + classroom.getClassName());
            index++;
        }
        System.out.print("Enter the number of the class to select (or type '0' to cancel): ");
    }

    public void printStudentinClassrooms(int classNumber) {
        Classrooms classroom = classrooms.get(classNumber - 1);
        List<Student> students = classroom.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students in this class.");
            return;
        }
        System.out.println("Students in " + classroom.getClassName() + ":");
        int index = 1;
        for (Student student : students) {
            System.out.println(index + ". " + student);
            index++;
        }
    }

//Print-End//////////////////////////

    //ClassManagement-Start///////////////////////////////////////////////////////////////////////////////////
    //{Tạo lớp->
    public void createClassroom(String className, int maxStudents) {
        Classrooms classroom = new Classrooms(className, maxStudents);
        classrooms.add(classroom);
        saveClassrooms();
    }
    //<-Tạo Lớp}

    //{Xóa Lớp->
    public void deleteClassroom() {
        printClassrooms();
        int classNumber = sc.nextInt();
        sc.nextLine();
        if (classNumber > 0 && classNumber <= classrooms.size()) {
            classrooms.remove(classNumber - 1);
            saveClassrooms();
            System.out.println("Classroom deleted.");
        } else if (classNumber < 0 || classNumber >= classrooms.size()) {
            System.out.println("Invalid class number.");
        }
    }
    //<-Xóa Lớp}


    //{Sửa Lớp->
    public void editClassroomName() {
        printClassrooms();
        int classNumber = sc.nextInt();
        sc.nextLine(); // consume newline character
        if (classNumber > 0 && classNumber <= classrooms.size()) {
            System.out.print("Enter the new name for the class: ");
            String newClassName = sc.nextLine();
            Classrooms selectedClassroom = classrooms.get(classNumber - 1);
            selectedClassroom.setClassName(newClassName);
            saveClassrooms();
            System.out.println("Class name updated.");
        } else if (classNumber != 0) {
            System.out.println("Invalid class number.");
        }
    }

    public void editMaxStudents() {
        printClassrooms();
        int classroomIndex = sc.nextInt() - 1;
        sc.nextLine(); // consume newline
        if (classroomIndex >= 0 && classroomIndex < classrooms.size()) {
            Classrooms classroom = classrooms.get(classroomIndex);
            System.out.print("Enter new maximum number of students: ");
            int newMaxStudents = sc.nextInt();
            sc.nextLine(); // consume newline

            if (newMaxStudents >= classroom.getStudents().size()) {
                classroom.setMaxStudents(newMaxStudents);
                saveClassrooms();
                System.out.println("Maximum number of students updated.");
            } else {
                System.out.println("New maximum number of students cannot be less than the current number of students.");
            }
        } else {
            System.out.println("Invalid classroom choice.");
        }
    }
    //<-Sửa Lớp}
//ClassManagement-End///////////////////////////////////////////////////////////////////////////


    //StudentManagement-Start///////////////////////////////////////////////////////////////////////
    //{Hàm thêm student vào lớp->
    public void addStudentToClassroom() {
        printClassrooms();
        int classNumber = sc.nextInt();
        sc.nextLine();
        if (classNumber > 0 && classNumber <= classrooms.size()) {
            Classrooms classroom = classrooms.get(classNumber - 1);
            if (classroom.getRemainingSlots() > 0) {
                System.out.print("Enter student name: ");
                String name = sc.nextLine();
                System.out.print("Enter student marks: ");
                double marks = sc.nextDouble();
                sc.nextLine();
                int studentId = classroom.getStudents().size() + 1;
                Student student = new Student(studentId, name, marks);
                classroom.addStudent(student);
                saveClassrooms();
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Classroom is full. Cannot add more students.");
            }
        }
    }
    //<-Hàm thêm student vào lớp}

    //{Edit Student->
    public void editStudentInClassroom() {
        printClassrooms();
        int classNumber = sc.nextInt();
        sc.nextLine();
        if (classNumber > 0 && classNumber <= classrooms.size()) {
            Classrooms classroom = classrooms.get(classNumber - 1);
            List<Student> students = classroom.getStudents();
            printStudentinClassrooms(classNumber);
            System.out.println("Enter the number of the class to select (or type '0' to cancel): \"");
            int studentId = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (studentId != 0) {
                Student studentToUpdate = null;
                for (Student student : students) {
                    if (student.getId() == studentId) {
                        studentToUpdate = student;
                        break;
                    }
                }
                if (studentToUpdate != null) {
                    System.out.print("Enter new student name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new student marks: ");
                    double newMarks = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    studentToUpdate.setName(newName);
                    studentToUpdate.setMark(newMarks);
                    saveClassrooms();
                    System.out.println("Student information updated successfully.");
                } else {
                    System.out.println("Student not found.");
                }
            } else {
                System.out.println("Edit cancelled.");
            }

        } else {
            System.out.println("Invalid class number.");
        }
    }


    public void deleteStudentFromClassroom() {
        printClassrooms();
        int classNumber = sc.nextInt();
        sc.nextLine();
        Classrooms classroom = classrooms.get(classNumber - 1);
        List<Student> students = classroom.getStudents();
        if (classNumber > 0 && classNumber <= classrooms.size()) {
            printStudentinClassrooms(classNumber);
            int studentNumber = sc.nextInt();
            sc.nextLine();
            if (studentNumber > 0 && studentNumber <= students.size()) {
                students.remove(studentNumber - 1);
                for (int i = 0; i < students.size(); i++) {
                    students.get(i).setId(i + 1);
                }
                saveClassrooms();
                System.out.println("Student deleted.");
            } else if (studentNumber < 0 || studentNumber >= students.size()) {
                System.out.println("Invalid student number.");
            }
        } else if (classNumber != 0) {
            System.out.println("Invalid class number.");
        }
    }


    //{Hàm show thông tin->
    public void choice(){
        System.out.println("1. Edit");
        System.out.println("2. Delete");
        System.out.println("0. Back");
        System.out.print("Choose an action: ");
    }
    public boolean displayClassrooms() {
        printClassrooms();
        int classNumber = sc.nextInt();
        sc.nextLine();
        if (classNumber == 0) {
            return true;
        }
        if (classNumber > 0 && classNumber <= classrooms.size()) {
            Classrooms classroom = classrooms.get(classNumber - 1);
            LinkedList<Student> students = classroom.getStudents();
            sortStudent.stu = students;
            while (true) {
                printStudentinClassrooms(classNumber);
                System.out.print("Enter the number of the student to select (or :\n-1. Search in this class.\n-2. Sort\n0. Back): ");
                int stu = sc.nextInt();
                sc.nextLine();
                if (stu == 0) {
                    return false;
                }
                if (stu > 0 && stu <= students.size()) {
                    Student selectedStudent = students.get(stu - 1);
                    choice();
                    int action = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    switch (action) {
                        case 0:
                            break;
                        case 1:
                            System.out.print("Enter new student name: ");
                            String newName = sc.nextLine();
                            System.out.print("Enter new student marks: ");
                            double newMarks = sc.nextDouble();
                            sc.nextLine();
                            selectedStudent.setName(newName);
                            selectedStudent.setMark(newMarks);
                            saveClassrooms();
                            System.out.println("Student information updated successfully.");
                            break;
                        case 2:
                            students.remove(selectedStudent);
                            for (int i = 0; i < students.size(); i++) {
                                students.get(i).setId(i + 1); // Reassign IDs
                            }
                            saveClassrooms();
                            System.out.println("Student deleted successfully.");
                            break;
                        default:
                            System.out.println("Invalid action.");
                            break;
                    }
                } else if (stu == -1) {
                    searchStudent.searchInClass(students);
                } else if (stu == -2) {
                    sortStudent.process();
                } else {
                    System.out.println("Invalid student number.");
                }
            }
        } else if (classNumber != 0) {
            System.out.println("Invalid class number.");
        }
        return false;
    }

    //<-Hàm show thông tin}
//StudentManagement-End/////////////////////////////////////////////////////////////////////////////
    //{Hàm load->
    private void loadClassrooms() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            classrooms = (LinkedList<Classrooms>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading classrooms: " + e.getMessage());
        }
    }

    public void saveClassrooms() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(classrooms);
        } catch (IOException e) {
            System.err.println("Error saving classrooms: " + e.getMessage());
        }
    }
    //<-Hàm Load}
}
//Sort-Search-End//////////////////////////////////////////////////////////////////////////////////////////////////////