package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService { //Service 클래스는 비즈니스에 가까운 느낌 -> 그래서 클래스 이름 등 용어도 비즈니스스러운 용어를 사용함
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // 외부에서 memberRepository를 넣을 수 있도록 설정
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {// 같은 이름이 있는 중복 회원 X (조건)
        /* 조금 덜 깔끔한 코드
        Optional<Member> result = memberRepository.findByName(member.getName());
        // ctrl + alt + v 단축키로 memberRepository.findByName(member.getName()) 을 리턴
        //Member member1 = result.get(); -> 값을 꺼낼때 이렇게 해도 됨(직접 바로 꺼내는 방식)
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        // result에 값이 존재하면 throw new 모시깽이를 실행함
        // 이 코드는 Optional로 한번 감싸기 때문에 실행이 가능함
       */

        // 좀 더 최적화된 코드

        validataDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validataDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // memberId 반환
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
