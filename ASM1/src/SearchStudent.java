import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SearchStudent {
    private Scanner sc;
    private StudentManagement sm;

    public SearchStudent(Scanner sc, StudentManagement sm) {
        this.sc = sc;
        this.sm = sm;
    }

    public void searchInClass(LinkedList<Student> students) {
        System.out.print("Enter search keyword: ");
        String searchKeyword = sc.nextLine();
        int stt = 1;
        List<Student> searchResults = new LinkedList<>();

        for (Student student : students) {
            String id = String.valueOf(student.getId());
            String name = student.getName();
            String mark = String.valueOf(student.getMark());
            String rank = student.getRank();

            if (id.contains(searchKeyword) || name.contains(searchKeyword) || mark.contains(searchKeyword) || rank.contains(searchKeyword)) {
                System.out.println(stt + ". ID: " + student.getId() + ", Name: " + student.getName() + ", Mark: " + student.getMark() + ", Rank: " + student.getRank());
                searchResults.add(student);
                stt++;
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No students found.");
        } else if (searchResults.size() == 1) {
            Student selectedStudent = searchResults.get(0);
            System.out.println("Found 1 student:");
            System.out.println(selectedStudent);

            sm.choice();
            System.out.print("Choose an action: ");
            int action = sc.nextInt();
            sc.nextLine();
            handleAction(action, selectedStudent, students);
        } else {
            System.out.println("Found multiple students:");
            int index = 1;
            for (Student student : searchResults) {
                System.out.println(index + ". " + student);
                index++;
            }

            System.out.print("Enter the number of the student to select: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice > 0 && choice <= searchResults.size()) {
                Student selectedStudent = searchResults.get(choice - 1);
                System.out.println("1. Edit");
                System.out.println("2. Delete");
                System.out.println("0. Back");
                System.out.print("Choose an action: ");
                int action = sc.nextInt();
                sc.nextLine(); // Consume newline
                handleAction(action, selectedStudent, students);
            } else {
                System.out.println("Invalid student number.");
            }
        }
    }

    private void handleAction(int action, Student selectedStudent, LinkedList<Student> students) {
        switch (action) {
            case 0:
                break;
            case 1:
                System.out.print("Enter new student name: ");
                String newName = sc.nextLine();
                System.out.print("Enter new student marks: ");
                double newMarks = sc.nextDouble();
                sc.nextLine(); // Consume newline

                selectedStudent.setName(newName);
                selectedStudent.setMark(newMarks);
                sm.saveClassrooms();
                System.out.println("Student information updated successfully.");
                break;
            case 2:
                students.remove(selectedStudent);
                for (int i = 0; i < students.size(); i++) {
                    students.get(i).setId(i + 1); // Reassign IDs
                }
                sm.saveClassrooms();
                System.out.println("Student deleted successfully.");
                break;
            default:
                System.out.println("Invalid action.");
                break;
        }
    }
}
