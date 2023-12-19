package me.seungjun.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.seungjun.springbootdeveloper.domain.Article;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest { // DTO 데이터 교환 객체

    private String title;
    private String content;

    public Article toEntity() { // 데이터 객체 생성 엔티티 생성
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
