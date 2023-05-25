package com.borja.cms.steps.article;

import com.borja.cms.config.CucumberStepDefinitionInfra;
import com.borja.cms.domain.article.model.Status;
import com.borja.cms.infrastructure.article.output.mongo.entity.Article;
import com.borja.cms.infrastructure.article.output.repository.RemoveArticleAdapter;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import org.springframework.data.domain.Example;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@CucumberContextConfiguration
public class RemoveArticleUseCaseStepDefinition extends CucumberStepDefinitionInfra {

    @Autowired
    private RemoveArticleAdapter removeArticleAdapter;

    @Given("the article with id {long} with owner {string}")
    public void theExistentArticle(Long articleId, String articleOwner) {
        articleMongoRepository.save(buildArticle(articleId, articleOwner));
    }

    @When("the article with id {long} is removed by the owner {string}")
    public void theArticleWithIdIsRemovedByTheOwner(Long articleId, String articleOwner) {
        removeArticleAdapter.removeArticle(articleId, articleOwner);
    }

    @Then("the status for the article with id {long} has changed to {string}")
    public void theArticleStatusChangedTo(Long articleId, String status) {
        Article article = new Article();
        article.setId(articleId);
        Optional<Article> articleOpt = articleMongoRepository.findOne(Example.of(article));

        assertTrue(articleOpt.isPresent());
        assertEquals(status, articleOpt.get().getStatus());
        assertNotEquals(articleOpt.get().getCreatedAt(), articleOpt.get().getUpdatedAt());
    }

    private Article buildArticle(Long articleId, String articleOwner) {
        Date now = Date.from(Instant.now());

        Article article = new Article();
        article.setId(articleId);
        article.setOwner(articleOwner);
        article.setStatus(Status.CREATED.name());
        article.setCreatedAt(now);
        article.setUpdatedAt(now);

        return article;
    }
}
