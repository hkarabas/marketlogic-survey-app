package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.UserSurveyAnswerDto;
import org.marketlogic.surveyappcommand.backend.command.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserSurveyAnswerService {
    private AggregateRepository<UserSurveyAnswerAggregate, UserSurveyAnswerCommand> aggregateRepository;

    private AggregateRepository<UserSurveyAnswerBulkDeleteAggregate, UserSurveyAnswerCommand> bulkDeleteAggregateRepository;


    public CompletableFuture<EntityWithIdAndVersion<UserSurveyAnswerAggregate>> save(UserSurveyAnswerDto Survey) {
        return aggregateRepository.save(new CreateUserSurveyAnswerCommand(Survey));
    }

    public CompletableFuture<EntityWithIdAndVersion<UserSurveyAnswerAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteUserSurveyAnswerCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<UserSurveyAnswerAggregate>> update(String id, UserSurveyAnswerDto newSurvey) {
        return aggregateRepository.update(id, new UpdateUserSurveyAnswerCommand(id, newSurvey));
    }

    public CompletableFuture<EntityWithIdAndVersion<UserSurveyAnswerBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return bulkDeleteAggregateRepository.save(new DeleteUserSurveyAnswersCommand(ids));
    }
}
