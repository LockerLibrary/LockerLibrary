package member;

import member.model.*;

import java.lang.reflect.Type;

public class manageMember <T> {
    private Member member;

    public void setMember(Member object) {
        this.member = object;
    }

    public T returnName(T bs) {
        // 로그인 과정
        return bs;
    }

    public T returnCode(T number){
        return number;
    }

//    public List<Locker> 남은 사물함 조회() {
//        return new List<Locker>;
//    }
//
//    public void 예약(Locker locker, int pk) {}
//
//    public void 예약취소(int pk) {}
//
//    public void 예약조회(int pk) {}
}
