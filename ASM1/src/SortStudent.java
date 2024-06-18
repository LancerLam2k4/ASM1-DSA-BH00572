import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SortStudent {
    public LinkedList<Student> stu ;
    Scanner sc = new Scanner(System.in);
    public SortStudent() {}
    public void process(){
        boolean sortmenu = false;
        while (!sortmenu) {
            System.out.println("1. Sort by Name Ascending");
            System.out.println("2. Sort by Name Descending");
            System.out.println("3. Sort by Mark Ascending");
            System.out.println("4. Sort by Mark Descending");
            System.out.println("5. Return to the original sort.");
            System.out.println("6. Reverse sort by ID");
            System.out.println("0. Back");
            System.out.print("Choose an action: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 0:
                    sortmenu = true;
                    break;
                case 1:
                    bubbleSortByName(stu, true);
                    System.out.println("Students sorted by name ascending.");
                    sortmenu = true;
                    break;
                case 2:
                    bubbleSortByName(stu, false);
                    System.out.println("Students sorted by name descending.");
                    sortmenu = true;
                    break;
                case 3:
                    selectionSortByMark(stu, true);
                    System.out.println("Students sorted by mark ascending.");
                    sortmenu = true;
                    break;
                case 4:
                    selectionSortByMark(stu, false);
                    System.out.println("Students sorted by mark descending.");
                    sortmenu = true;
                    break;
                case 5:
                    quickSortById(stu,true);
                    System.out.println("Return to the original sort.");
                    sortmenu = true;
                    break;
                case 6:
                    quickSortById(stu,false);
                    System.out.println("Reverse sort by ID");
                    sortmenu = true;
                    break;
                default:
                    System.out.println("Invalid action.");
                    break;
            }
        }
    }
    private void bubbleSortByName(LinkedList<Student> students, boolean ascending) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 -i; j++) {
                if ((ascending && students.get(i).getName().compareTo(students.get(j +1).getName()) > 0) ||
                        (!ascending && students.get(i).getName().compareTo(students.get(j+1).getName()) < 0)) {
                    Collections.swap(students, j, j+1);
                }
            }
        }
    }

    public void selectionSortByMark(List<Student> students, boolean ascending) {
        int n = students.size();

        for (int i = 0; i < n - 1; i++) {
            int minOrMaxIdx = i;

            for (int j = i + 1; j < n; j++) {
                if ((ascending && students.get(j).getMark() < students.get(minOrMaxIdx).getMark()) ||
                        (!ascending && students.get(j).getMark() > students.get(minOrMaxIdx).getMark())) {
                    minOrMaxIdx = j;
                }
            }

            // Swap the found minimum/maximum element with the first element
            if (minOrMaxIdx != i) {
                Collections.swap(students, i, minOrMaxIdx);
            }
        }
    }
    private void SortById(List<Student> students, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(students, low, high, ascending);
            SortById(students, low, pi - 1, ascending);
            SortById(students, pi + 1, high, ascending);
        }
    }
    private int partition(List<Student> students, int low, int high, boolean ascending) {
        Student pivot = students.get(high); // Chọn pivot là phần tử cuối cùng trong danh sách
        int i = low - 1; // Khởi tạo chỉ số của phần tử nhỏ hơn pivot

        for (int j = low; j < high; j++) {
            if ((ascending && students.get(j).getId() <= pivot.getId()) ||
                    (!ascending && students.get(j).getId()>= pivot.getId())) {
                i++;
                Collections.swap(students, i, j); // Đổi chỗ phần tử tại i và j
            }
        }

        Collections.swap(students, i + 1, high); // Đưa pivot vào đúng vị trí của nó trong danh sách đã sắp xếp
        return i + 1; // Trả về chỉ số của pivot
    }
    public void quickSortById(List<Student> students,boolean ascending) {
        SortById(students, 0, students.size() - 1, ascending);
    }
}
