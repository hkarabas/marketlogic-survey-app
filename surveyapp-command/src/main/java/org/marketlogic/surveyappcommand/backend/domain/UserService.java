package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.UserDto;
import org.marketlogic.surveyappcommand.backend.command.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService {

    private AggregateRepository<UserAggregate, UserCommand> aggregateRepository;

    private AggregateRepository<UserBulkDeleteAggregate, UserCommand> bulkDeleteAggregateRepository;


    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> save(UserDto user) {
        return aggregateRepository.save(new CreateUserCommand(user));
    }

    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteUserCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> update(String id, UserDto newUser) {
        return aggregateRepository.update(id, new UpdateUserCommand(id, newUser));
    }

    public CompletableFuture<EntityWithIdAndVersion<UserBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return bulkDeleteAggregateRepository.save(new DeleteUsersCommand(ids));
    }
}
