package me.seungjun.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 스프링 데이터 JPA 인터페이스: JpaRepository 상속
public interface MemberRepository extends JpaRepository<Member, Long> {
}
