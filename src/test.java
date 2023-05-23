package member;
import member.model.Member;
import member.manageMember;
import locker.LockerDB;
import locker.manageLocker;

/**
 * <p>
 * 테스트 클래스.
 * </p>
 * <p>
 * Member 클래스를 그대로 이용해도 상관없지만, Member를 상속받아 회원정보 필드(변수)의 이름만 재정의하여 유지보수를 쉽게 할 수
 * 있음.
 * </p>
 */

public class test {
    public static void main(String[] args) {

        LockerDB db = LockerDB.getInstance();
        db.addDummyLocker();

        Member wb = new library("김원빈", 200503001, "컴소");
        Member test = new library("tester", 11111111, "test");
        manageMember mmwb = new manageMember<>();
        manageMember mmtest = new manageMember<>();
        manageLocker mlwb = new manageLocker<>();
        manageLocker mltest = new manageLocker<>();
        
        mmwb.setMember(wb);
        mmtest.setMember(test);
        mlwb.setmanageMember(mmwb);
        mltest.setmanageMember(mmtest);
        
        
        
        /* 로그인 기능 */

        // 회원가입
        System.out.println("\n< 회원가입 >");
        wb.setUsername("wb");
        wb.setPassword("1234");
        mmwb.signUp(wb);                    // wb [김원빈, 200503001, 컴소]

        test.setUsername("test");
        test.setPassword("1234");
        mmtest.signUp(test);                // test [tester, 00000000, test]


        // 로그인
        System.out.println("\n< 로그인 >");
        mmwb.login("0000", "0000"); // 실패
        mmwb.login("wb", "1234");
        mmtest.login("test", "1234");


        // 로그아웃
        System.out.println("\n< 로그아웃 >");
        mmwb.logout();

        mmwb.login("wb", "1234"); // 다시 로그인



        /* 예약 기능 */

        // 예약
        System.out.println("\n< 예약 >");
        mlwb.reserve_locker(200503001, db.searchLocker("G-10"));
        mlwb.reserve_locker(200503001, db.searchLocker("G-20")); // 실패
        mltest.reserve_locker(11111111, db.searchLocker("G-10")); // 실패 (다른 사람이 예약)


        // 예약 확인
        System.out.println("\n< 예약 확인 >");
        mlwb.check_locker(200503001);


        // 예약 취소
        System.out.println("\n< 예약 취소 >");
        mlwb.cancel_locker(200503001);

        mlwb.check_locker(200503001); // 취소 여부 확인



        // //DB 생성
        // LockerDB db = LockerDB.getInstance();
        // db.addDummyLocker(); // 각 건물(G, Mo, Mi, B, I)별로 1~100번 사물함 생성

        // // member, manageMember, manageLocker 설정
        // manageMember mg1 = new manageMember();
        // manageMember mg2 = new manageMember();
        // manageMember mg3 = new manageMember();

        // manageLocker ml1 = new manageLocker();
        // manageLocker ml2 = new manageLocker();
        // manageLocker ml3 = new manageLocker();

        // Member member1 = new library("이현우", 201804027, "컴퓨터소프트웨어학과");
        // Member member2 = new library("이주찬", 202101031, "컴퓨터소프트웨어학과");
        // Member member3 = new library("변진모", 201904013, "컴퓨터소프트웨어학과");
        
        // mg1.setMember(member1); // 추상클래스 타입으로 매개변수 전달이 잘 되는지 확인. 출력: [이현우, 201804027, 컴퓨터소프트웨어학과]
        // mg2.setMember(member2);
        // mg3.setMember(member3);

        // ml1.setmanageMember(mg1);
        // ml2.setmanageMember(mg2);
        // ml3.setmanageMember(mg3);

        // // Member의 username, password 입력
        // member1.setUsername("gusdn123");
        // member1.setPassword("password1");
        // member2.setUsername("wncks456");
        // member2.setPassword("password2");
        // member3.setUsername("wlsah9980");
        // member3.setPassword("password3");


        // /* 로그인 기능 테스트 */ 
        // mg1.signUp(member1); // 회원가입
        // mg2.signUp(member2);

        // //mg1.login("user2", "wrongPassword");// 로그인 실패 (패스워드 불일치)
        // //mg1.login("user4", "password4");    // 로그인 실패 (존재하지 않는 사용자명)
        // mg1.login("gusdn123","password12" );  //로그인 실패
        // mg2.login("wncks456","password2");    // 로그인 성공
        // mg2.signUp(member3); // 회원가입 실패 // mg2 객체는 이미 로그인 중이므로 회원가입이 불가능하다.
        // mg1.login("gusdn123","password1" );  //로그인 성공
        // mg2.login("wncks456","password2");    // 이미 로그인
        // mg2.logout(); // 로그아웃
        // mg2.login("wncks456","password2");    // 로그인 성공
        // mg3.signUp(member3); // 회원가입 성공 mg3객체는 로그인 상태가 아니기에 회원가입 가능
        // mg2.logout(); // 로그아웃
        // mg1.logout(); // 로그아웃

        
        // /* 예약 기능 테스트 */
        // System.out.println("----------------------------");
        // System.out.println(ml1.check_unoccupiedLocker()); // 비어있는 locker 목록 확인 (사물함 객체 담김)
        
        // // 로그인 연동 테스트 
        // mg1.logout();
        // ml1.reserve_locker(201804027, db.searchLocker("G-1")); // 로그인 필요
        // ml1.cancel_locker(201804027);
        // ml1.check_locker(201804027);

        // // 예약
        // System.out.println("\n예약 ----------------");
        // mg1.login("gusdn123","password1" );  //로그인 성공
        // ml1.reserve_locker(201804027, db.searchLocker("G-10")); // l1 예약
        // ml1.reserve_locker(201804027, db.searchLocker("G-20")); // 예약 실패 (이미 예약한 사물함 존재)
        
        // mg2.login("wncks456","password2"); // 로그인 성공
        // ml2.reserve_locker(202101031, db.searchLocker("G-10")); // 예약 실패 (다른사람이 예약한 사물함)

        // // 예약 확인
        // System.out.println("\n예약 확인 ----------------");
        // ml2.check_locker(202101031); // 예약 확인 실패 (예약된 사물함이 없음)

        // ml2.reserve_locker(202101031, db.searchLocker("Mi-10")); // b2 예약
        // ml2.check_locker(202101031); // 예약 확인 성공

        // // 예약 취소
        // System.out.println("\n예약 취소 ----------------");
        // ml2.cancel_locker(202101031); // 예약 취소 성공
        // ml2.cancel_locker(202101031); // 예약 취소 실패 (예약된 사물함이 없음)
    }
}


// Member 클래스를 상속한 샘플 클래스
class library extends Member {
    public library(String name, int studentCode, String major) {
        super(name, studentCode, major);
    }
}