package member;

import member.model.Member;
import locker.model.Locker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>테스트 클래스.</p>
 * <p>Member 클래스를 그대로 이용해도 상관없지만, Member를 상속받아 회원정보 필드(변수)의 이름만 재정의하여 유지보수를 쉽게 할 수 있음.</p>
 */

public class test {
    public static void main(String[] args) {
        manageMember mg = new manageMember();

        Member member1 = new library("이현우", 201804027, "컴퓨터소프트웨어학과");
        Member member2 = new library("이주찬", 202101031, "컴퓨터소프트웨어학과");

        System.out.println(member1.getter());   // 추상클래스 타입으로 선언한 하위클래스의 정보를 잘 전달받는지 확인. 예상 출력: [이현우, 201804027, 컴퓨터소프트웨어학과]
        mg.setMember(member1);                  // 추상클래스 타입으로 매개변수 전달이 잘 되는지 확인. 예상 출력: [이현우, 201804027, 컴퓨터소프트웨어학과]

        // 로그인 기능 테스트

        // mg.signUp("user1", "password1");
        // mg.signUp("user2", "password2");
        // mg.signUp("user3", "password3");

        // mg.login("user1", "password1"); // 로그인 성공
        // mg.login("user2", "wrongPassword"); // 로그인 실패
        // mg.login("user4", "password4"); // 존재하지 않는 사용자명, 로그인 실패

        // mg.signUp("user5", "password5");
        // // 로그아웃
        // mg.logout();
        // 회원가입 테스트
        mg.signUp("user5", "password5");



        // 예약 기능 테스트
        System.out.println("-------------------------");
        Locker l1 = new Locker(01, "갈멜관");
        Locker l2 = new Locker(02, "갈멜관");
        Locker l3 = new Locker(03, "갈멜관");

        List<Locker> lockers = Arrays.asList(l1, l2, l3); // 예약 가능한 사물함 목록 입력 (단점 일일히 입력해야 하는데 다른 방법을 찾아야 할 듯)


        mg.setLockers(lockers);

        System.out.println(mg.check_unoccupiedLocker()); // 전체 사물함 리스트가 입력됐는지 확인 (리스트에 사물함 객체가 모두 담김)

        // 로그인 x 시 -> 로그인이 필요합니다.
        mg.reserve_locker(201804027, l1);
        mg.check_locker(201804027);
        mg.cancel_locker(201804027);


        // 로그인 o
        mg.signUp("test", "testPw");
        mg.login("test", "testPw");

        // 예약 기능 test
        mg.reserve_locker(201804027, l1); // 예약 성공
        mg.reserve_locker(201804027, l2); // 예약 실패 (이미 예약한 사물함 존재)
        
        mg.setMember(member2); // 다른 멤버로 접속!
        mg.reserve_locker(202101031, l1); // 예약 실패 (다른사람이 예약한 사물함임)

        // 예약 확인 기능 test
        mg.check_locker(202101031); // 예약 확인 실패 (예약한 사물함이 없음)

        mg.reserve_locker(202101031, l3); // l3 예약
        mg.check_locker(202101031); // 예약 확인 성공

        // 예약 취소 기능 test
        mg.cancel_locker(202101031); // 예약 취소 성공
        mg.cancel_locker(202101031); // 예약 취소 실패 (예약한 사물함이 없음)




        
    }
}

class library extends Member {
    public library(String name, int studentCode, String major) {
        super(name, studentCode, major);
    }
}
