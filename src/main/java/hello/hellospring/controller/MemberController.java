package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    // spring이 처음 실행될 때 spring container(스프링 전용 통)가 생성되고
    // 제일 처음에 Controller 어노테이션을 보고 MemberController 객체가 생성 후 저장됨
    // 그 후 스프링이 이걸 관리함 -> 스프링 컨테이너에서 스프링 빈이 관리된다고도 함

    private final MemberService memberService;
    // 스프링 컨테이너에 있는 memberService의 객체를 spring이 알아서 연결해줌 -> 여러번 만들어 쓸 필요가 없는 객체인 경우에 사용

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }


}
