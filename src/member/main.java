package member;

//import member.model.Member;
//import member.model.bibleUniStudent;
//import member.decorator.*;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        manageMember mg = new manageMember();

//        Member member = new Member() {
//            private String name; // 이름
//            private int studentCode; // 학번
//
//            public String getName() {
//                return this.name;
//            }
//        };

//        Member m = new bibleUniStudent("iser", 1);
//        System.out.println(m.getName());
        /*bibleUniStudent bs = new bibleUniStudent("iser", 1);

        mg.setMember(bs);
        System.out.println(bs.name);

        System.out.println(mg.returnName(bs));
        System.out.println(mg.returnCode("hihi"));
        System.out.println(mg.returnCode(123123));
        System.out.println(mg.returnCode("dkanrjsk"));*/

        Member member = new bible("dlgusdn", "201804027", "man");
        System.out.println(member.getter());
    }
}

//abstract class Member {
//    public abstract List getter();
//    public abstract void setter();
//}

abstract class Member <T> {
    private T member1;
    private T member2;
    private T member3;

    public Member (T member1, T member2, T member3) {
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
    }
    public List getter() {
        List list = new ArrayList<>();
        list.add(member1);
        list.add(member2);
        list.add(member3);
        return list;
    }

}

class bible extends Member {
    private String name;
    private String studentCode;
    private String cex;

    public bible(String name, String studentCode, String cex) {
        super(name, studentCode, cex);
        this.name = name;
        this.studentCode = studentCode;
        this.cex = cex;
    }

    @Override
    public List getter() {
        List list = new ArrayList<>();
        list.add(name);
        list.add(studentCode);
        list.add(cex);
        return list;
    }
}
