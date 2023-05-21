package locker;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import locker.model.Locker;

// DB 대용으로 만든 클래스 (locker 목록 저장 / 데이터 검색, 삽입, 삭제)
public class LockerDB {

    // 싱글톤 구현
    private static LockerDB db;

    private LockerDB() {
    };

    public static LockerDB getInstance() {
        if (db == null) {
            db = new LockerDB();
        }
        return db;
    }


    // DB에 담긴 데이터 : locker 목록
    private static List<Locker> allLockers = new ArrayList<Locker>(); // 전체 locker 목록
    private static HashMap<Integer, Locker> occupiedLockers = new HashMap<Integer, Locker>(); // 예약된 locker 목록


    // DB 데이터 읽기 : locker 목록 반환
    public List<Locker> getAllLockers() { // 전체 locker 목록
        return allLockers;
    }
    public HashMap<Integer, Locker> getOccupiedLockers() { // 예약된 locker 목록
        return occupiedLockers;
    }

    // DB 데이터 검색 : 특정 locker 검색
    public Locker searchLocker(String lockerPk) { // 사물함 pk로 검색
        // * 사물함 리스트가 빈 경우
        if (allLockers.size() == 0) {
            return null;
        }
        // DB 검색 로직
        for (Locker locker : allLockers) { 
            if (locker.getLockerPk().equals(lockerPk)) {
                return locker;
            }
        }
        return null; // * 해당 사물함이 없는 경우
    }

    public Locker searchLocker(String buildingName, int lockerNum) { // 건물명 + 사물함 번호로 검색 (overroding)
        // * 사물함 리스트가 빔
        if (allLockers.size() == 0) {
            return null;
        }
        // DB 검색 로직
        for (Locker locker : allLockers) {
            if (locker.getbuildingName().equals(buildingName) && locker.getLockerNum() == lockerNum) {
                return locker;
            }
        }
        return null; // * 해당 사물함이 없음
    }

    // DB 데이터 추가 : locker객체 생성 -> 전체 locker 목록에 저장
    public void addLocker(String lockerPk, String buildingName, int lockerNum) { // !!Pk 수동입력 지양!!
        // * 동일한 사물함이 존재
        for (Locker locker : allLockers) {
            if (locker.getLockerPk().equals(lockerPk)) {
                return;
            }
        }
        // 데이터 추가 로직
        allLockers.add(new Locker(lockerPk, buildingName, lockerNum));
    }

    public void addLocker(String buildingName, int lockerNum) { // lockerPk 없이 생성 (overroding)
        String lockerPk = createPk(buildingName, lockerNum);
        // * 동일한 사물함이 존재
        for (Locker locker : allLockers) {
            if (locker.getLockerPk().equals(lockerPk)) {
                return;
            }
        }
        // 데이터 추가 로직
        allLockers.add(new Locker(lockerPk, buildingName, lockerNum));
    }

    // DB 데이터 삭제 : 전체 locker 목록에서, 특정 locker 삭제
    public void deleteLocker(String lockerPk) { // 사물함 pk로 삭제
        // * 사물함 리스트가 빔
        if (allLockers.size() == 0) {
            return;
        }
        // 데이터 삭제 로직
        for (Locker locker : allLockers) {
            if (locker.getLockerPk().equals(lockerPk)) {
                allLockers.remove(locker);
                return;
            }
        }
        return; // * 삭제할 사물함이 존재하지 않음
    }

    public void deleteLocker(String buildingName, int lockerNum) { // 건물명 + 사물함 번호로 삭제 (overroding)
        // * 사물함 리스트가 빔
        if (allLockers.size() == 0) {
            return;
        }
        // 데이터 삭제 로직
        for (Locker locker : allLockers) {
            if (locker.getbuildingName().equals(buildingName) && locker.getLockerNum() == lockerNum) {
                allLockers.remove(locker);
                return;
            }
        }
        return; // * 삭제할 사물함이 존재하지 않음
    }

    // <for test>
    // DB 더미데이터 입력 : 전체 locker 목록에 더미데이터 저장
    public void createDummyLocker() {
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

    // 건물명, 사물함 번호 -> lockerPk 생성 / ex (모리아관, 13번) -> Mo-13
    private static String createPk(String buildingName, int lockerNum) {
        for (int i = 0; i < buildingCodeTable.length; i++) {
            if (buildingCodeTable[i][0].equals(buildingName)) {
                return buildingCodeTable[i][1] + "-" + lockerNum;
            }
        }
        return null;
    }

    // // LockerDB 기능 테스트
    //
    // public static void main(String[] args) {
    // LockerDB db = new LockerDB();

    // db.createLocker(); // 더미 데이터 생성

    // // 검색
    // System.out.println(db.searchLocker("Mo-20").getLockerPk()); // 사물함 검색1 (pk)
    // System.out.println(db.searchLocker("모리아관", 20).getLockerPk()); // 사물함 검색2
    // (건물명, 사물함 번호)

    // // 추가
    // db.addLocker("Mo-200", "모리아관", 200); // 사물함(Mo-300) 추가1 (pk, 건물명, 사물함)
    // db.addLocker("모리아관", 300); // 사물함(Mo-200) 추가2 (건물명, 사물함)

    // System.out.println(db.searchLocker("Mo-200").getLockerPk()); // 사물함(Mo-200)
    // 추가여부 확인
    // System.out.println(db.searchLocker("Mo-300").getLockerPk()); // 사물함(Mo-300)
    // 추가여부 확인

    // // 삭제
    // db.deleteLocker("Mo-200"); // 사물함(Mo-200) 삭제1 (pk)
    // db.deleteLocker("모리아관", 300); // 사물함(Mo-300) 삭제2 (건물명, 사물함)

    // System.out.println(db.searchLocker("Mo-200")); // 사물함(Mo-200) 삭제여부 확인
    // System.out.println(db.searchLocker("Mo-300")); // 사물함(Mo-300) 삭제여부 확인
    // }
}