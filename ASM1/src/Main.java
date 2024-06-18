import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();
        boolean backtomenu = false;
        while (true) {
            System.out.println("\n1. Class Management.");
            System.out.println("2. Student Management.");
            System.out.println("3. OverViews");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    while (!backtomenu) {
                        System.out.println("You want  ");
                        System.out.println("1. Create Class.");
                        System.out.println("2. Edit Name Class.");
                        System.out.println("3. Edit the number of students in a class.");
                        System.out.println("4. Delete Class.");
                        System.out.println("0. To Back");
                        int choice1 = sc.nextInt();
                        sc.nextLine();
                        switch (choice1) {
                            case 0:
                                backtomenu = true;
                                break;
                            case 1:
                                System.out.print("Enter classroom name: ");
                                String className = sc.nextLine();
                                System.out.print("Enter number of students: ");
                                int numStudents = sc.nextInt();
                                sc.nextLine();
                                sm.createClassroom(className, numStudents);
                                break;
                            case 2:
                                sm.editClassroomName();
                                break;
                            case 3:
                                sm.editMaxStudents();
                                break;
                            case 4:
                                sm.deleteClassroom();
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                    backtomenu = false;
                    break;
                case 2:
                    while (!backtomenu) {
                        System.out.println("You want  ");
                        System.out.println("1. Add students");
                        System.out.println("2. Edit Student.");
                        System.out.println("3. Delete Student.");
                        System.out.println("0. To Back");
                        int choice1 = sc.nextInt();
                        sc.nextLine();
                        switch (choice1) {
                            case 0:
                                backtomenu = true;
                                break;
                            case 1:
                                sm.addStudentToClassroom();
                                break;
                            case 2:
                                sm.editStudentInClassroom();
                                break;
                            case 3:
                                sm.deleteStudentFromClassroom();
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                    backtomenu = false;
                    break;
                case 3:
                    while (!sm.displayClassrooms()) {
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}