package com.borja.cms.application.article.service;

import com.borja.cms.application.article.RemoveArticleUseCase;
import com.borja.cms.application.article.dtos.RemovedArticleDTO;
import com.borja.cms.application.article.output.RemoveArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RemoveArticleService implements RemoveArticleUseCase {

    @Autowired
    RemoveArticleRepository removeArticleRepository;

    @Override
    public Output handle(Input input) {
        Optional<RemovedArticleDTO> result = removeArticleRepository.removeArticle(input.id(), input.removedBy());

        if (result.isPresent()) {
            return new Output(input.id(), input.removedBy());
        }

        return new Output(null, null);
    }

}
