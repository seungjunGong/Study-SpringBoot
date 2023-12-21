package me.seungjun.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.seungjun.springbootdeveloper.domain.Article;
import me.seungjun.springbootdeveloper.dto.ArticleListViewResponse;
import me.seungjun.springbootdeveloper.dto.ArticleViewResponse;
import me.seungjun.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); // 글 리스트 저장

        return "articleList"; // articleList.html 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
    
    @GetMapping("/new-article")
    // 쿼리 파라미터에서 id 값을 받아온다.
    // id 값이 있는 경우 : 수정, id 값이 없는 경우 : 생성
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null) { // 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else{ // 수정
          Article article = blogService.findById(id);
          model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
