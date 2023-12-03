package me.seungjun.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication // 스프링 부트 사용에 필요한 기본 설정 애너테이션
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args); // 애플리케이션 실행.run(메인클래스, 커맨드라인 인수)
    }

}
