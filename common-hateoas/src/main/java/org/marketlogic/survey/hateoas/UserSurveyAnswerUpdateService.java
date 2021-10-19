package org.marketlogic.survey.hateoas;

import org.marketlogic.survey.model.UserSurveyAnswer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserSurveyAnswerUpdateService {
    public List<UserSurveyAnswer> getAll();

    public CompletableFuture<UserSurveyAnswer> findById(String userSurveyAnswerId);
}
