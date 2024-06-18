import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double mark;
    private String rank;

    public Student(int id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
        this.rank = SortRank(mark);
    }
    public String getRank(){
        return rank;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getMark(){
        return mark;
    }
    public void setMark(double mark){
        this.mark = mark;
        this.rank = SortRank(mark);
    }
    @Override
    public String toString() {
        return String.format("ID: %-10s || Name: %-20s || Mark: %-5.2f || Rank: %s", id, name, mark,rank);
    }

    public String SortRank(double mark){
        if(mark < 5 &&mark >= 0){
            rank = "Fail";
        }else if(mark >= 5 && mark < 6.5){
            rank = "Medium";
        }else if(mark >= 6.5 && mark < 7.5){
            rank = "Good";
        }else if(mark >= 7.5 && mark < 9){
            rank = "Very Good";
        } else if (mark>=9 && mark <=10) {
            rank = "Excellent";
        }else {
            System.out.println("Errol");
        }
        return rank;
    }

}
