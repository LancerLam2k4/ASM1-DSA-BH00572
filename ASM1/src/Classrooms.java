import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Classrooms implements Serializable {
    private static final long serialVersionUID = 1L;

    private String className;
    private LinkedList<Student> students;
    private int maxStudents;

    public Classrooms(String className,int maxStudents) {
        this.className = className;
        this.students = new LinkedList<>();
        this.maxStudents = maxStudents;
    }
    public int generateNewStudentId() {
        int maxId = 0;
        for (Student student : students) {
            int studentId = student.getId(); // Lấy phần số từ ID (loại bỏ ký tự đầu tiên)
            if (studentId > maxId) {
                maxId = studentId;
            }
        }
        maxId += 1;
        return maxId ;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }
    public int getMaxStudents() {
        return maxStudents;
    }
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    public int getRemainingSlots() {
        return maxStudents - students.size();
    }
    @Override
    public String toString() {
        int index =1;
        StringBuilder sb = new StringBuilder();
        sb.append("Students in ").append(className).append(" :").append("\n");
        for (Student student : students) {
            sb.append(index).append(". ").append(student).append("\n");
            index++;
        }
        return sb.toString();
    }
}
