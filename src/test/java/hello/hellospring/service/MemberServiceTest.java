package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 같은 memberRepository를 사용하기 위해서
    }

    @AfterEach //Test 메서드들이 끝날 때마다 실행되는 callBack 메소드
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    // Test 코드는 한글로 이름을 설정해도 됨
    // Test 코드는 빌드될 때 실제로 포함되지 않음
    void 회원가입_Join() {
        // given-when-then 패턴
        //given : 무언가가 주어졌는데
        Member member = new Member();
        member.setName("spring");

        //when : 이걸 실행했을 때
        Long saveId = memberService.join(member); //join을 Test하는 코드

        //then : 결과가 이렇게 나와야 됨 이라는 뜻
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test //test는 예외처리도 중요함
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //lambda 함수
        // () -> memberService.join(member2) 이 해당 로직이 실행됐을 때 IllegalStateException 이 예외가 터져야 함

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try{
            memberService.join(member2);
            fail(); //예외가 그냥 넘어갈 경우
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}