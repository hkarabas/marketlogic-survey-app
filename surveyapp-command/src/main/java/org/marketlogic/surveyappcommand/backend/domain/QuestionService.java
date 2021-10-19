package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.QuestionDto;
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
public class QuestionService {

    private AggregateRepository<QuestionAggregate, QuestionCommand> aggregateRepository;

    private AggregateRepository<QuestionBulkDeleteAggregate, QuestionCommand> bulkDeleteAggregateRepository;


    public CompletableFuture<EntityWithIdAndVersion<QuestionAggregate>> save(QuestionDto question) {
        return aggregateRepository.save(new CreateQuestionCommand(question));
    }

    public CompletableFuture<EntityWithIdAndVersion<QuestionAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteQuestionCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<QuestionAggregate>> update(String id, QuestionDto newQuestion) {
        return aggregateRepository.update(id, new UpdateQuestionCommand(id, newQuestion));
    }

    public CompletableFuture<EntityWithIdAndVersion<QuestionBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return bulkDeleteAggregateRepository.save(new DeleteQuestionsCommand(ids));
    }
}
