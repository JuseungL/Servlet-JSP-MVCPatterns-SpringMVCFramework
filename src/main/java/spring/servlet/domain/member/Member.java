package spring.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    //id는 저장될 때 발급되도록
    private Long id;
    private String username;
    private int age;

    private Member() {
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }


}
