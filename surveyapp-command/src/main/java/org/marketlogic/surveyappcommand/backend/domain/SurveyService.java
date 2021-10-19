package org.marketlogic.surveyappcommand.backend.domain;


import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.SurveyDto;
import org.marketlogic.surveyappcommand.backend.command.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class SurveyService {

    private AggregateRepository<SurveyAggregate, SurveyCommand> aggregateRepository;

    private AggregateRepository<SurveyBulkDeleteAggregate, SurveyCommand> bulkDeleteAggregateRepository;


    public CompletableFuture<EntityWithIdAndVersion<SurveyAggregate>> save(SurveyDto Survey) {
        return aggregateRepository.save(new CreateSurveyCommand(Survey));
    }

    public CompletableFuture<EntityWithIdAndVersion<SurveyAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteSurveyCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<SurveyAggregate>> update(String id, SurveyDto newSurvey) {
        return aggregateRepository.update(id, new UpdateSurveyCommand(id, newSurvey));
    }

    public CompletableFuture<EntityWithIdAndVersion<SurveyBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return bulkDeleteAggregateRepository.save(new DeleteSurveysCommand(ids));
    }
}
