package member.model;

public class bibleUniStudents implements Member {
    private String name;
    private int studentCode;

    public void setter(String name, int studentCode){
        this.name = name;
        this.studentCode = studentCode;
    }
}
