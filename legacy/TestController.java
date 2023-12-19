package me.seungjun.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 라우터 역할을 하는 애너테이션
public class TestController {
    @Autowired // TestService 빈 주입
    TestService testService;
    @GetMapping("/test") // /test GET 요청이 오면 메서드 실행
    public List<Member> getAllMembers(){
        List<Member> members = testService.getAllMembers();
        return members;
    }
}
