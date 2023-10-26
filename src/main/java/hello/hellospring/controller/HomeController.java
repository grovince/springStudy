package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //제일 처음 localhost:8080으로 들어오게 되면 가장 먼저 호출되는 메서드
    // 만약 GetMapping으로 맵핑 되어 있는 것이 없다면 요청 들어온 파일이 정적 리소스에 있는지 찾아봄
    // 근데 이 코드에는 맵핑되어 있는 것이 존재하므로 home.html을 호출하고 끝남
    public String home(){
        return "home"; // home.html이 호출됨
    }

}
