package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
        // @Bean 어노테이션이 메소드에 사용되면, 해당 메서드가 생성하는 객체가 Srping 컨테이너에
        // 빈으로 등록되어 관리됨 -> Spring이 객체의 생명 주기를 관리하고 의존성 주입을 쉽게 처리하기 위함
        // 이 메소드는 MemberService 객체를 생성하고 반환하는 메서드 -> 메서드의 반환 값은 Spring 컨테이너에 등록됨
        // 이 메서드를 호출하면 새로운 MemberService 인스턴스를 얻을 수 있음
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    // 스프리잉 시작될 때 위의 2개의 Bean을 등록하고
    // 스프링 빈에 등록되어 있는 memberRepository를 memberService와 연결해줌
    // -> MemberService 클래스의 private final MemberRepository memberRepository와 연결해줌
}
