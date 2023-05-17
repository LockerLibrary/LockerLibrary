package member;

import member.model.Member;
import locker.model.Locker;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class manageMember<T> {
    private Member member;

    private boolean loggedIn;
    private int loginAttempts;
    private Map<String, String> userCredentials; // 사용자 계정 정보 저장

    private static List<Locker> lockers; // 전체 locker 목록
    private static HashMap<Integer, Locker> occupiedLockers; // 예약된 locker 목록


    // 로그인 기능
    public manageMember() {
        userCredentials = new HashMap<>();
        occupiedLockers = new HashMap<>();
    }

    public void setMember(Member object) {
        this.member = object;
        System.out.println(member.getter());
    }

    public Member getMember() {
        return member;
    }


    public T returnName(T bs) {
        // 로그인 과정
        return bs;
    }

    public T returnCode(T number) {
        return number;
    }

    public void signUp(String username, String password) {
        // 회원가입 기능 구현
        if (loggedIn) {
            System.out.println("로그인 상태에서는 회원가입이 불가능합니다.");
            return;
        }

        if (userCredentials.containsKey(username)) {
            System.out.println("이미 존재하는 사용자명입니다.");
            return;
        }

        userCredentials.put(username, password);
        System.out.println("회원가입이 완료되었습니다.");
    }

    public void login(String username, String password) {
        // 추가적인 로그인 조건을 확인
        if (loggedIn) {
            System.out.println("이미 로그인되어 있습니다. 로그아웃을 실행한 후에 다시 로그인 시도해주세요.");
            return;
        }

        // 실제 로그인 기능 구현
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            loggedIn = true;
            loginAttempts = 0;
            System.out.println("로그인 성공!");
        } else {
            loggedIn = false;
            loginAttempts++;
            System.out.println("로그인 실패!");
        }
    }

    public void logout() {
        loggedIn = false;
        System.out.println("로그아웃 되었습니다.");
    }

    public boolean login_check() {
        return loggedIn;
    }

    // 예약 기능
    // 외부에서 (예약 가능한) 전체 locker 목록을 입력!
    public void setLockers(List<Locker> lockers) {
        manageMember.lockers = lockers;
    }

    // 빈 사물함 목록 출력 : occupiedLockers의 요소를 제외하고, locker 목록 복사 후 리턴
    public List<Locker> check_unoccupiedLocker() {
        List<Locker> unoccupied = new ArrayList<>();

        if(occupiedLockers == null) { // 예약된 사물함이 없으면 그냥 전체 사물함 리턴
            return lockers;
        }
        
        for(Locker locker : lockers) {
            if(!occupiedLockers.containsValue(locker))
                unoccupied.add(locker);
        } 
        return unoccupied;
    }

    // 예약 : 사용중인 사물함 목록에 키=pk, 값=locker 저장
    // (pk에 학번을 넣어서 각자 사물함을 예약하도록 함)
    public void reserve_locker(int pk, Locker locker) {
        if(!loggedIn) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
        if(occupiedLockers.containsKey(pk)) {
            System.out.println("이미 다른 사물함을 예약중입니다.");
            return;
        }
        if(occupiedLockers.containsValue(locker)) {
            System.out.println("예약된 사물함입니다.");
            return;
        }
        
        occupiedLockers.put(pk, locker);
        System.out.printf("%s-%d 사물함이 예약되었습니다.\n", locker.getbuildingName(), locker.getLockNum());
    }

    // 예약취소 : 사용중인 사물함 목록에서 키=pk에 해당하는 요소 제거
    public void cancel_locker(int pk) {
        if(!loggedIn) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
        if(!occupiedLockers.containsKey(pk)) {
            System.out.println("아직 예약된 사물함이 없습니다.");
            return;
        }
        
        occupiedLockers.remove(pk);
        System.out.println("사물함이 예약취소 되었습니다.");
    }
    // 예약확인 : 사용중인 사물함 목록에서 키=pk에 해당하는 Locker객체 반환 + 정보 출력
    public Locker check_locker(int pk) {
        if(!loggedIn) {
            System.out.println("로그인이 필요합니다.");
            return null;
        }
        if(!occupiedLockers.containsKey(pk)) {
            System.out.println("예약된 사물함이 없습니다.");
            return null;
        }
        
        Locker myLocker = occupiedLockers.get(pk);

        System.out.printf("예약중인 사물함은 %s-%d입니다.\n", myLocker.getbuildingName(), myLocker.getLockNum());
        return myLocker;
    }
}


