package org.marketlogic.surveyappcommand.backend.domain;


import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.AnswerDto;
import org.marketlogic.surveyappcommand.backend.command.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class AnswerService {
    private AggregateRepository<AnswerAggregate, AnswerCommand> aggregateRepository;

    private AggregateRepository<AnswerBulkDeleteAggregate, AnswerCommand> bulkDeleteAggregateRepository;


    public CompletableFuture<EntityWithIdAndVersion<AnswerAggregate>> save(AnswerDto question) {
        return aggregateRepository.save(new CreateAnswerCommand(question));
    }

    public CompletableFuture<EntityWithIdAndVersion<AnswerAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteAnswerCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<AnswerAggregate>> update(String id, AnswerDto newAnswer) {
        return aggregateRepository.update(id, new UpdateAnswerCommand(id, newAnswer));
    }

    public CompletableFuture<EntityWithIdAndVersion<AnswerBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return bulkDeleteAggregateRepository.save(new DeleteAnswersCommand(ids));
    }
}
