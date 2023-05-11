package member;

import member.model.Member;

/**
 * <p>테스트 클래스.</p>
 * <p>Member 클래스를 그대로 이용해도 상관없지만, Member를 상속받아 회원정보 필드(변수)의 이름만 재정의하여 유지보수를 쉽게 할 수 있음.</p>
 */

public class test {
    public static void main(String[] args) {
        manageMember mg = new manageMember();

        Member member1 = new library("이현우", 201804027, "컴퓨터소프트웨어학과");

        System.out.println(member1.getter());   // 추상클래스 타입으로 선언한 하위클래스의 정보를 잘 전달받는지 확인. 예상 출력: [이현우, 201804027, 컴퓨터소프트웨어학과]
        mg.setMember(member1);                  // 추상클래스 타입으로 매개변수 전달이 잘 되는지 확인. 예상 출력: [이현우, 201804027, 컴퓨터소프트웨어학과]
    }
}

class library extends Member {
    public library(String name, int studentCode, String major) {
        super(name, studentCode, major);
    }
}


