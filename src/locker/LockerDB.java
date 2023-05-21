package locker;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import locker.model.Locker;

// 데이터 저장하기 위해 DB대용으로 만든 클래스 (locker 목록 저장)
public class LockerDB {

    // DB에 담긴 데이터 : locker 목록
    private static List<Locker> allLockers = new ArrayList<Locker>(); // 전체 locker 목록
    private static HashMap<Integer, Locker> occupiedLockers = new HashMap<Integer, Locker>(); // 예약된 locker 목록


    // DB 데이터 읽기 : locker 목록 반환
    public static List<Locker> getAllLockers() { // 전체 locker 목록
        return allLockers;
    }

    public static HashMap<Integer, Locker> getOccupiedLockers() { // 예약된 locker 목록
        return occupiedLockers;
    }


    // DB 데이터 검색 : 특정 locker 검색
    public Locker searchLocker(String lockerPk) { // 사물함 pk로 검색
        for (Locker locker : allLockers) {
            if (locker.getLockerPk().equals(lockerPk)) {
                return locker;
            }
        }
        return null; // 해당 사물함이 없음 or 전체 사물함이 없음
    }

    public Locker searchLocker(String buildingName, int lockerNum) { // 건물명 + 사물함 번호로 검색 (overroding)
        for (Locker locker : allLockers) {
            if (locker.getbuildingName().equals(buildingName) && locker.getLockerNum() == lockerNum) {
                return locker;
            }
        }
        return null;
    }


    // DB 데이터 추가 : locker객체 생성 -> 전체 locker 목록에 저장
    public void addLocker(String lockerPk, String buildingName, int lockerNum) { // !!Pk 수동입력 지양!!
        allLockers.add(new Locker(lockerPk, buildingName, lockerNum));
    }

    public void addLocker(String buildingName, int lockerNum) { // lockerPk 없이 생성 (overroding)
        String lockerPk = createPk(buildingName, lockerNum);
        allLockers.add(new Locker(lockerPk, buildingName, lockerNum));
    }


    // DB 데이터 삭제 : 전체 locker 목록에서, 특정 locker 삭제
    public void deleteLocker(String lockerPk) { // 사물함 pk로 삭제
        for (Locker locker : allLockers) {
            if (locker.getLockerPk().equals(lockerPk)) {
                allLockers.remove(locker);
                return;
            }
        }
    }

    public void deleteLocker(String buildingName, int lockerNum) { // 건물명 + 사물함 번호로 삭제 (overroding)
        for (Locker locker : allLockers) {
            if (locker.getbuildingName().equals(buildingName) && locker.getLockerNum() == lockerNum) {
                allLockers.remove(locker);
                return;
            }
        }
    }


    // <for test>
    // DB 더미데이터 입력 : 전체 locker 목록에 더미데이터 저장
    public void createLocker() {
        for (int i = 1; i <= 100; i++) {
            addLocker("갈멜관", i);
            addLocker("모리아관", i);
            addLocker("밀알관", i);
            addLocker("복음관", i);
            addLocker("일립관", i);
        }
    }


    /* 사물함 추가할때, lockerPk를 자동생성하기 위한 기능들 */
    // 건물코드 테이블
    public static String[][] buildingCodeTable = {
            { "갈멜관", "G" },
            { "모리아관", "Mo" },
            { "밀알관", "Mi" },
            { "복음관", "B" },
            { "일립관", "I" }
    };

    // 건물명, 사물함 번호 -> lockerPk 생성          / ex (모리아관, 13번) -> Mo-13
    private static String createPk(String buildingName, int lockerNum) {
        for (int i = 0; i < buildingCodeTable.length; i++) {
            if (buildingCodeTable[i][0].equals(buildingName)) {
                return buildingCodeTable[i][1] + "-" + lockerNum;
            }
        }
        return null;
    }


    // LockerDB 기능 테스트
    public static void main(String[] args) {
        LockerDB db = new LockerDB();

        db.createLocker(); // 더미 데이터 생성
        System.out.println();

        System.out.println(db.searchLocker("모리아관", 20).getLockerPk());

        db.addLocker("모리아관", 200);
        System.out.println(db.searchLocker("모리아관", 200).getLockerPk());
        db.deleteLocker("모리아관", 200);
        System.out.println(db.searchLocker("모리아관", 200));
    }
}