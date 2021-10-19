package org.marketlogic.survey.hateoas;

import org.marketlogic.survey.model.Question;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface QuestionUpdateService {

    public List<Question> getAll();

    public CompletableFuture<Question> findById(String questionId);
}
