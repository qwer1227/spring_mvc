package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트의 순서가 보장되지 않기 때문에 객체를 초기화 시켜주는게 필요하다
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given 이런게 주어졌을때
        Member member = new Member("hello", 20);
        //when 이런걸 실행했을때
        Member savedMember = memberRepository.save(member);
        //then 결과가 이래야만해
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("hello", 20);
        Member member2 = new Member("hello", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> members = memberRepository.findAll();
        //then
        Assertions.assertThat(members.size()).isEqualTo(2);
        Assertions.assertThat(members).contains(member1,member2);
    }
}
