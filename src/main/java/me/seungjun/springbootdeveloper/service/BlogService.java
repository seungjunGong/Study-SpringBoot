package me.seungjun.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.seungjun.springbootdeveloper.domain.Article;
import me.seungjun.springbootdeveloper.dto.AddArticleRequest;
import me.seungjun.springbootdeveloper.dto.UpdateArticleRequest;
import me.seungjun.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final 혹은 @NotNull이 붙은 생성자를 만들어줌.
@Service // 빈 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity()); // save는 JpaRepository 에서 지원하는 저장 메서드이다. 데이터베이스에 엔티티를 저장함.
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }
    
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id)); // 엔티티가 없는 경우 예외 발생
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 메서드
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
