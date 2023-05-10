package member.model;

public class bibleUniStudent implements Member {
    public String name; // 이름
    private int studentCode; // 학번

    public bibleUniStudent(String name, int studentCode) {
        super();
        this.name = name;
        this.studentCode = studentCode;
    }

//    public void bibleUniStudent(String name, int studentCode){
//        this.name = name;
//        this.studentCode = studentCode;
//    }

    public void setter(String name, int studentCode) {
        this.name = name;
        this.studentCode = studentCode;
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.studentCode;
    }
}

