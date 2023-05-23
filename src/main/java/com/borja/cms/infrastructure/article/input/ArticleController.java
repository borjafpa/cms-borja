package com.borja.cms.infrastructure.article.input;

import com.borja.cms.application.article.CreateArticleUseCase;
import com.borja.cms.application.article.RemoveArticleUseCase;
import com.borja.cms.infrastructure.article.input.dtos.CreateArticleRequest;
import com.borja.cms.infrastructure.article.input.dtos.CreateArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = ArticleController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ArticleController {

    public static final String BASE_URL = "/article/v1/";

    @Autowired
    CreateArticleUseCase createArticleUseCase;

    @Autowired
    RemoveArticleUseCase removeArticleUseCase;

    @PostMapping
    public ResponseEntity<CreateArticleResponse> createArticle(@RequestBody CreateArticleRequest request, Authentication authentication) {
        CreateArticleUseCase.Output output = createArticleUseCase.handle(new CreateArticleUseCase.Input(request.title(), request.content(), authentication.getName()));

        return ResponseEntity.ok()
                .body(new CreateArticleResponse(output.id(), output.title(), output.content(), output.status(), output.createdAt()));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity removeArticle( @PathVariable("articleId") final Long articleId, Authentication authentication) {

        RemoveArticleUseCase.Output output = removeArticleUseCase.handle(new RemoveArticleUseCase.Input(articleId, authentication.getName()));

        if (output.id() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }


}
