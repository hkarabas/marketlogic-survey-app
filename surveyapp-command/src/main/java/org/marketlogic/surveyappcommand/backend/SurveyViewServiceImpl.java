package org.marketlogic.surveyappcommand.backend;

import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.SurveyRepository;
import org.marketlogic.survey.hateoas.SurveyUpdateService;
import org.marketlogic.survey.hateoas.UserUpdateService;
import org.marketlogic.survey.model.Survey;
import org.marketlogic.survey.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyViewServiceImpl implements SurveyUpdateService {

    private SurveyRepository repository;

    @Override
    public List<Survey> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<Survey> findById(String surveyId) {
        Optional<Survey> optionalSurvey = repository.findById(surveyId);
        return optionalSurvey.map(CompletableFuture::completedFuture).orElseGet(() -> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No todo found for given id")));
    }
}
