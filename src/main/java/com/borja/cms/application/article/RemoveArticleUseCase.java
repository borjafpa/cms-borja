package com.borja.cms.application.article;


public interface RemoveArticleUseCase {
    Output handle(Input input);

    record Input(Long id, String removedBy) {}

    record Output(Long id, String removedBy) {}

}
