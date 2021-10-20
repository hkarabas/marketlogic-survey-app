package org.marketlogic.surveyappquery.backend;

import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.SurveyRepository;
import org.marketlogic.survey.hateoas.SurveyUpdateService;
import org.marketlogic.survey.model.Survey;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class SurveyUpdateServiceImpl implements SurveyUpdateService {

    private SurveyRepository repository;

    public void remove(String id) {
        repository.deleteById(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public Survey save(Survey survey) {
        return repository.save(survey);
    }

    @Override
    public List<Survey> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<Survey> findById(String surveyId) {
        Optional<Survey> optionalSurvey = repository.findById(surveyId);
        return optionalSurvey.map(CompletableFuture::completedFuture).orElseGet(() -> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No Survey found for given id")));
    }

}
