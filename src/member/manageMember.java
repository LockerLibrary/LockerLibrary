package member;

import member.model.Member;

import java.util.Map;
import java.util.HashMap;

public class manageMember<T> {
    private Member member;

    private boolean loggedIn = false;
    private int loginAttempts;
    private Map<String, String> userCredentials; // 사용자 계정 정보 저장

    public manageMember() {
        userCredentials = new HashMap<>();
    }

    public void setMember(Member object) {
        this.member = object;
        System.out.println(member.getter());
    }

    /*로그인 기능 구현*/
    // 회원가입
    public int signUp(String username, String password) {
        if (loggedIn) {
            System.out.println("로그인 상태에서는 회원가입이 불가능합니다.");
            return -1;
        }
        if (userCredentials.containsKey(username)) {
            System.out.println("이미 존재하는 사용자명입니다.");
            return -1;
        }

        userCredentials.put(username, password);
        System.out.println("회원가입이 완료되었습니다.");
        return 1;
    }

    // 로그인
    public int login(String username, String password) {
        // 추가적인 로그인 조건을 확인
        if (loggedIn) {
            System.out.println("이미 로그인되어 있습니다. 로그아웃을 실행한 후에 다시 로그인 시도해주세요.");
            return -1;
        }
        // 실제 로그인 기능 구현
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            loggedIn = true;
            loginAttempts = 0;
            System.out.println("로그인 성공!");
            return 1;
        } else {
            loggedIn = false;
            loginAttempts++;
            System.out.println("로그인 실패!");
            return -1;
        }
    }

    // 로그아웃
    public void logout() {
        loggedIn = false;
        System.out.println("로그아웃 되었습니다.");
    }

    // 로그인 상태 체크
    public boolean login_check() {
        return loggedIn;
    }
}


