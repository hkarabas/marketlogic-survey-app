package org.marketlogic.survey.hateoas;

import org.marketlogic.survey.model.Answer;
import org.marketlogic.survey.model.Question;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AnswerUpdateService {

    public List<Answer> getAll();

    public CompletableFuture<Answer> findById(String answerId);
}
