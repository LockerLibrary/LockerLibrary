package member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import locker.LockerList;
import locker.model.Locker;
import member.model.Member;

/**
 * <p>테스트 클래스.</p>
 * <p>Member 클래스를 그대로 이용해도 상관없지만, Member를 상속받아 회원정보 필드(변수)의 이름만 재정의하여 유지보수를 쉽게 할 수 있음.</p>
 */

public class test {
    public static void main(String[] args) {

        // 전체 locker 등록 -> 전역객체 allLockers에
        Locker g1 = new Locker(10, "갈멜관");
        Locker g2 = new Locker(20, "갈멜관");
        Locker g3 = new Locker(30, "갈멜관");
        Locker b1 = new Locker(10, "복음관");
        Locker b2 = new Locker(20, "복음관");
        List<Locker> allLockers = Arrays.asList(g1, g2, g3, b1, b2); // 예약 가능한 사물함 목록 입력 (단점 일일히 입력한다, 입력해주는 다른 클래스가 필요한가???)
        
        LockerList.setAllLockers(allLockers);


        // member 등록
        manageMember mg1 = new manageMember();
        manageMember mg2 = new manageMember();
        manageMember mg3 = new manageMember();

        Member member1 = new library("이현우", 201804027, "컴퓨터소프트웨어학과");
        Member member2 = new library("이주찬", 202101031, "컴퓨터소프트웨어학과");
        Member member3 = new library("변진모", 201904013, "컴퓨터소프트웨어학과");

        System.out.println(member1.getter());   // 추상클래스 타입으로 선언한 하위클래스의 정보를 잘 전달받는지 확인. 예상 출력: [이현우, 201804027, 컴퓨터소프트웨어학과]
        mg1.setMember(member1);                  // 추상클래스 타입으로 매개변수 전달이 잘 되는지 확인. 예상 출력: [이현우, 201804027, 컴퓨터소프트웨어학과]
        mg2.setMember(member2);
        mg3.setMember(member3);

        member1.setUsername("gusdn123");
        member1.setPassword("password1");
        member2.setUsername("wncks456");
        member2.setPassword("password2");
        member3.setUsername("wlsah9980");
        member3.setPassword("password3");


        // 로그인 기능 테스트
        mg1.signUp(member1); // 회원가입
        mg2.signUp(member2);

        //mg1.login("user2", "wrongPassword");// 로그인 실패 (패스워드 불일치)
        //mg1.login("user4", "password4");    // 로그인 실패 (존재하지 않는 사용자명)
        mg1.login("gusdn123","password12" );  //로그인 실패
        mg2.login("wncks456","password2");    // 로그인 성공
        mg2.signUp(member3); // 회원가입 실패 // mg2 객체는 이미 로그인 중이므로 회원가입이 불가능하다.
        mg1.login("gusdn123","password1" );  //로그인 성공
        mg2.login("wncks456","password2");    // 이미 로그인
        mg2.logout(); // 로그아웃
        mg2.login("wncks456","password2");    // 로그인 성공
        mg3.signUp(member3); // 회원가입 성공 mg3객체는 로그인 상태가 아니기에 회원가입 가능
        mg2.logout(); // 로그아웃
        mg1.logout(); // 로그아웃

        System.out.println("----------------------------");

        
        // 예약 기능 테스트
        System.out.println(mg1.check_unoccupiedLocker()); // 비어있는 locker 목록 확인 (사물함 객체 담김)

        mg1.reserve_locker(201804027, g1); // 로그인 x -> 로그인이 필요합니다.
        mg1.check_locker(201804027);
        mg1.cancel_locker(201804027);

        mg1.login("gusdn123","password1" );  //로그인 성공

        // 예약 test
        System.out.println("\n예약 test ----------------");
        mg1.reserve_locker(201804027, g1); // l1 예약
        mg1.reserve_locker(201804027, g2); // 예약 실패 (이미 예약한 사물함 존재)
        
        // 다른멤버로 접속
        mg2.login("wncks456","password2");    // 로그인 성공
        
        mg2.reserve_locker(202101031, g1); // 예약 실패 (다른사람이 예약한 사물함)

        // 예약 확인 test
        System.out.println("\n예약 확인 test ----------------");
        mg2.check_locker(202101031); // 예약 확인 실패 (예약된 사물함이 없음)

        mg2.reserve_locker(202101031, b2); // b2 예약
        mg2.check_locker(202101031); // 예약 확인 성공

        // 예약 취소 test
        System.out.println("\n예약 취소 test ----------------");
        mg2.cancel_locker(202101031); // 예약 취소 성공
        mg2.cancel_locker(202101031); // 예약 취소 실패 (예약된 사물함이 없음)  
    }
}

class library extends Member {
    public library(String name, int studentCode, String major) {
        super(name, studentCode, major);
    }
}

class db {
    public void createLocker() {
        List list = new ArrayList();

        for (int i = 1; i <= 100; i++){
            list.add(new Locker(i, "갈멜관"));
            list.add(new Locker(i+100, "일립관"));
        }
        List<Locker> allLockers = list; // 라커 더미데이터 정의
    }
}