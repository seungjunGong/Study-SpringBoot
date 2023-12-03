package me.seungjun.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
@AllArgsConstructor
@Getter
@Entity // 엔티티 지정 JPA가 관리, @Entity(name = "table_name")
        // 기본적으로 name을 지정하지 않으면 클래스 이름과 같은 이름으로 테이블과 매핑
public class Member {

        @Id // id를 기본키로 지정
        @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 1 증가
        @Column(name = "id", updatable = false)
        private Long id; // DB 테이블의 'id' 컬럼과 매칭
        
        @Column(name = "name", nullable = false) // name 속성 not null 설정
        private String name; // DB 테이블의 'name' 컬럼과 매칭
}
