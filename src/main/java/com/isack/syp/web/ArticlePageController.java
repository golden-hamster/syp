package com.isack.syp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/articles")
@Controller
public class ArticlePageController {

    @GetMapping
    public String articlesPage() {
        return "board";
    }

    @GetMapping("/{articleId}")
    public String articleDetailPage() {return "detail";}
}
