package locker.model;

public class Locker {
    private int lockNum;
//    private int buildingNum;
    private String buildingName;

    public Locker (int lockNum, String buildingName) {
        this.lockNum = lockNum;
        this.buildingName = buildingName;
    }

    public int getLockNum() {
        return lockNum;
    }
    public String getbuildingName() {
        return buildingName;
    }
}