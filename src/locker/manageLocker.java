package locker;

import member.manageMember;
import locker.model.Locker;
import locker.LockerDB;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// 사물함 예약 기능 : 남은 사물함 목록, 예약, 취소, 예약확인
public class manageLocker<T> {
    
    // manageMember (loggedIn 정보 얻기 위해)
    private manageMember<T> mg = null;

    // DB (사물함 목록 -> DB의 사물함 목록과 연결)
    private static LockerDB db = LockerDB.getInstance();
    private static List<Locker> allLockers = db.getAllLockers(); // 전체 locker 목록
    private static HashMap<Integer, Locker> occupiedLockers = db.getOccupiedLockers(); // 예약된 locker 목록


    // manageMember 등록
    public void setmanageMember(manageMember<T> manageMember) {
        mg = manageMember;
    }

    
    /* 빈 사물함 목록 출력 */ 
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


    /* 예약 : 예약된 locker 목록에 [키pk, 값locker] 저장 */  // pk에 학번 넣는게 바람직
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


    /* 예약 취소 : 예약된 locker 목록에서 키pk에 해당하는 요소 제거 */ 
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

    
    /* 예약 확인 : 예약된 locker 목록에서 키pk에 해당하는 Locker객체 반환 */ 
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
