package me.seungjun.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.seungjun.springbootdeveloper.domain.Article;
import me.seungjun.springbootdeveloper.dto.AddArticleRequest;
import me.seungjun.springbootdeveloper.dto.ArticleResponse;
import me.seungjun.springbootdeveloper.dto.UpdateArticleRequest;
import me.seungjun.springbootdeveloper.service.BlogService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 해당 메서드로 매핑
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){ // @RequestBody 응답값과 객체 매핑
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED) // status 201
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream() // 스트림 사용 추가 공부 필요
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    // url 경로에서 id 값을 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) { // @PathVariable url 에서 값을 가져오는 에너테이션
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    // url 경로에서 id 값을 추출
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) { // @PathVariable url 에서 값을 가져오는 에너테이션
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
