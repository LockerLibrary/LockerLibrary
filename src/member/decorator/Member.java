package com.example.LockerLibrary.src.member.decorator;

import java.util.ArrayList;
import java.util.List;

abstract class Member {
    public abstract List getter();
}

class RoadMember<T> extends Member {
    private T member1;
    private T member2;
    private T member3;

    @Override
    public List getter() {
        List list = new ArrayList<>();
        list.add(member1);
        list.add(member2);
        list.add(member3);
        return list;
    }
}

abstract class MemberDecorator extends Member {
    private final Member decoratedMember;

    public MemberDecorator(Member decoratedMember) {
        this.decoratedMember = decoratedMember;
    }

    public List getter() {
        return decoratedMember.getter();
    }
}

class bible extends MemberDecorator {
    private String name;
    private int studentCode;
    private String cex;

    public bible(Member decoratedMember) {
        super(decoratedMember);
    }

    @Override
    public List getter() {
        List list = new ArrayList<>();
        list.add(name);
        list.add(studentCode);
        list.add(cex);
        return list;
    }

    public void setter(String name, int studentCode, String cex) {
        this.name = name;
        this.studentCode = studentCode;
        this.cex = cex;
    }
}
