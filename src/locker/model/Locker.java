package locker.model;

public class Locker {
    private String lockerPk; // = "건물코드-사물함번호" -> 자동 생성되도록 함
    private String buildingName;
    private int lockerNum;

    public Locker (String lockerPk, String buildingName, int lockerNum) {
        this.lockerPk = lockerPk;
        this.buildingName = buildingName;
        this.lockerNum = lockerNum;
    }

    // getter
    public String getLockerPk() {
        return lockerPk;
    }
    public int getLockerNum() {
        return lockerNum;
    }
    public String getbuildingName() {
        return buildingName;
    }
}