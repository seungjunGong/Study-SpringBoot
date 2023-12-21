package me.seungjun.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity // 엔티티 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id // id 필드를 기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder // 빌더 패턴으로 객체 생성(롬복에서 지원)
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
    /*
    빌더 패턴
    Article.builder()
        .title("abc")
        .content("def")
        .build();
     */

    /* 롬복의 NoArgsConstructor 애너테이션으로 대체
    protected Article(){ // 기본 생성자
    }
     */
    
    /* 롬복의 Getter 애너테이션으로 대체
    // 게터
    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
     */

    // 타임리프 관련 필드
    @CreatedDate // 엔티티 생성시 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티 수정시 수정 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
