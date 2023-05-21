// 로그인 상태인지 확인 어떻게??
//      1. manageLocker안에 manageMember 넣기
//      2. 로그인 정보 Member로 빼기 (loggedIn)
//
// 예약, 삭제, 조회 할떄 pk는 어떻게?
//      1. 학번             (단순)
//      2. Member 정보      (but 얻기 어려움, getter쓰려면 함수명이 이상함 getMember1, getMember2..)
//      3. userCredentials (유일한가?) 


package locker;

import member.manageMember;
import member.model.Member;
import locker.model.Locker;
import locker.LockerDB;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// 사물함 예약 기능 : 남은 사물함 목록, 예약, 취소, 예약확인
public class manageLocker<T> {
    // Member 관련
    // private Member member;

    // public void setMember(Member object) {
    //     this.member = object;
    //     System.out.println(member.getter());
    // }
    
    private manageMember<T> mg = null; // loggedIn 정보 필요

    // DB (사물함 목록 -> DB의 사물함 목록과 연결)
    private static LockerDB db = LockerDB.getInstance();
    private static List<Locker> allLockers = db.getAllLockers(); // 전체 locker 목록
    private static HashMap<Integer, Locker> occupiedLockers = db.getOccupiedLockers(); // 예약된 locker 목록


    // manageMember 연결 // loggedIn 필요
    public void setmanageMember(manageMember<T> manageMember) {
        mg = manageMember;
    }

    
    // 빈 사물함 목록 출력
    public List<Locker> check_unoccupiedLocker() {
        List<Locker> unoccupied = new ArrayList<>();

        if (occupiedLockers.size() == 0) { // 예약된 locker가 없으면, 전체 사물함 리턴
            return allLockers;
        }

        for (Locker locker : allLockers) { // 전체 locker를 순회하며, (예약된 locker제외) unoccupied 목록에 추가
            if (!occupiedLockers.containsValue(locker))
                unoccupied.add(locker);
        }
        return unoccupied;
    }

    // 예약 : 예약된 사물함 목록에 [키=pk, 값=locker] 저장 (pk에 학번 넣는게 바람직)
    public void reserve_locker(int pk, Locker locker) {
        if (!mg.login_check()) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
        if (occupiedLockers.containsKey(pk)) {
            System.out.println("이미 다른 사물함을 예약중입니다.");
            return;
        }
        if (occupiedLockers.containsValue(locker)) {
            System.out.println("예약된 사물함입니다.");
            return;
        }

        occupiedLockers.put(pk, locker); // Hashmap에 [pk, locker] 저장
        System.out.printf("%s 사물함이 예약되었습니다.\n", locker.getLockerPk(), locker.getLockerNum());
    }

    // 예약취소 : 예약된 사물함 목록에서 키=pk에 해당하는 요소 제거
    public void cancel_locker(int pk) {
        if (!mg.login_check()) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
        if (!occupiedLockers.containsKey(pk)) {
            System.out.println("예약된 사물함이 없습니다.");
            return;
        }

        occupiedLockers.remove(pk);
        System.out.println("사물함이 예약취소 되었습니다.");
    }

    // 예약확인 : 사용중인 사물함 목록에서 키=pk에 해당하는 Locker객체 반환 +정보 출력 (void로 출력만 해도??)
    public Locker check_locker(int pk) {
        Locker myLocker = occupiedLockers.get(pk);

        if (!mg.login_check()) {
            System.out.println("로그인이 필요합니다.");
            return null;
        }
        if (myLocker == null) {
            System.out.println("예약된 사물함이 없습니다.");
            return null;
        }

        System.out.printf("%s 사물함을 예약중입니다.\n", myLocker.getLockerPk(), myLocker.getLockerNum());
        return myLocker;
    }
}
