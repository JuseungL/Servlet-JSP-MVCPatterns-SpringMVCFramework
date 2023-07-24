package spring.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    //싱글톤이기 때문에 이렇게 하면 안돼
    //MemberRepository memberRepository = new MemberRepository();
    MemberRepository memberRepository =MemberRepository.getInstance();

    @AfterEach //테스트 끝났을 때 초기화 -> 초기화 안해주면 테스트에서 문제가 생길 수 있다.
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("JuseungL", 24);
        //when
        Member savedMember = MemberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("JihyeokC", 25);
        Member member2 = new Member("JunseokJ",23);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        //Assertions.assertThat(result.size()).isEqualTo(2);
        //위에꺼 Assertions에 커서 놓고 option+Enter로 static import하면 아래와같이됨
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);

    }
}