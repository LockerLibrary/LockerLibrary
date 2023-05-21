package locker;

import member.model.Member;
import locker.model.Locker;
import locker.LockerDB;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class manageLocker {
    private Member member;

    private static List<Locker> allLockers = LockerDB.getAllLockers(); // 전체 locker 목록
    private static HashMap<Integer, Locker> occupiedLockers = LockerDB.getOccupiedLockers(); // 예약된 locker 목록

    public void setMember(Member object) {
        this.member = object;
        System.out.println(member.getter());
    }
    
    /* 예약 기능 구현 */
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
        // if (!loggedIn) {
        //     System.out.println("로그인이 필요합니다.");
        //     return;
        // }
        if (occupiedLockers.containsKey(pk)) {
            System.out.println("이미 다른 사물함을 예약중입니다.");
            return;
        }
        if (occupiedLockers.containsValue(locker)) {
            System.out.println("예약된 사물함입니다.");
            return;
        }

        occupiedLockers.put(pk, locker); // Hashmap에 [pk, locker] 저장
        System.out.printf("%s-%d 사물함이 예약되었습니다.\n", locker.getbuildingName(), locker.getLockerNum());
    }

    // 예약취소 : 예약된 사물함 목록에서 키=pk에 해당하는 요소 제거
    public void cancel_locker(int pk) {
        // if (!loggedIn) {
        //     System.out.println("로그인이 필요합니다.");
        //     return;
        // }
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

        // if (!loggedIn) {
        //     System.out.println("로그인이 필요합니다.");
        //     return null;
        // }
        if (myLocker == null) {
            System.out.println("예약된 사물함이 없습니다.");
            return null;
        }

        System.out.printf("%s-%d 사물함을 예약중입니다.\n", myLocker.getbuildingName(), myLocker.getLockerNum());
        return myLocker;
    }
}
