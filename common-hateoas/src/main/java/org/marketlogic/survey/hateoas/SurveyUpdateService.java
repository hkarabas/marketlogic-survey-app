package org.marketlogic.survey.hateoas;

import org.marketlogic.survey.model.Survey;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SurveyUpdateService {

    public List<Survey> getAll();

    public CompletableFuture<Survey> findById(String surveyId);

}
