package com.example.LockerLibrary.src.locker;

import locker.model.Locker;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/*
    전체 사물함 목록 & 예약한 사물함 목록에 싱글톤 적용
    서버가 있다면, 서버에 저장하고 불러와서 사용??
*/ 
public class LockerList {
    private static List<Locker> allLockers = null;                  // 전체 locker 목록
    private static HashMap<Integer, Locker> occupiedLockers = null; // 예약된 locker 목록

    private LockerList() {};

    // 전체 locker 목록 등록
    public static void setAllLockers(List<Locker> arrlist) {
        allLockers = arrlist;
    }

    // locker 목록 반환 (싱글톤 객체)
    public static List<Locker> getAllLockers() {
        if(allLockers == null) {
            return allLockers = new ArrayList<Locker>();
        }
        return allLockers;
    }
    public static HashMap<Integer, Locker> getOccupiedLockers() {
        if(occupiedLockers == null) {
            return occupiedLockers = new HashMap<Integer, Locker>();
        }
        return occupiedLockers;
    }
}
