package com.borja.cms.application.article.service;

import com.borja.cms.application.article.CreateArticleUseCase;
import com.borja.cms.application.article.dtos.CreateArticleDTO;
import com.borja.cms.application.article.dtos.CreatedArticleDTO;
import com.borja.cms.application.article.output.CreateArticleRepository;
import com.borja.cms.application.auth.SignInUseCase;
import com.borja.cms.domain.article.model.Status;
import com.borja.cms.infrastructure.security.service.UserDetailsImpl;
import com.borja.cms.infrastructure.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreateArticleService implements CreateArticleUseCase {

    @Autowired
    CreateArticleRepository createArticleRepository;

    @Override
    public Output handle(Input input) {
        CreatedArticleDTO createdArticleDTO = createArticleRepository.save(toCreateArticleDTO(input));

        return new Output(createdArticleDTO.id(),
                createdArticleDTO.title(), createdArticleDTO.content(),
                Status.valueOf(createdArticleDTO.status()) , createdArticleDTO.createdAt());
    }

    private CreateArticleDTO toCreateArticleDTO(Input input) {
        return new CreateArticleDTO(input.title(), input.content(), Status.CREATED.name(), input.owner());
    }

}
