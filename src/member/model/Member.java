package com.example.LockerLibrary.src.member.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>회원정보 관리 추상클래스</h3>
 * <p>필드에 필수 변수 3개를 제공하며, 제약 없이 변수를 넣을 수 있다.</p>
 * <p>해당 클래스를 상속받아 변수의 이름을 재정의함으로써, 유지보수를 쉽게 할 수 있다는 장점이 있다.</p>
 * <p>사용시 유의점, Member 클래스를 그대로 사용할 경우 변수 순서를 사용자가 관리하여야 한다. (유지보수가 어렵다.)
 * 그 때문에, 클래스를 상속받아서 변수를 재정의하는 것이 좋다.</p>
 * <p>ex) 변수 이름: name, gender, phoneNumber</p>
 * @param <T>
 */

public abstract class Member<T> {
    private T member1;
    private T member2;
    private T member3;

    public Member(T member1, T member2, T member3) {
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
    }

    public List getter() {
        List list = new ArrayList<>();
        list.add(member1);
        list.add(member2);
        list.add(member3);
        return list;
    }

}
