package org.marketlogic.surveyappcommand.backend;


import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.UserRepository;
import org.marketlogic.survey.hateoas.UserUpdateService;
import org.marketlogic.survey.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserViewServiceImpl implements UserUpdateService {

    private UserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<User> findById(String userId) {
        Optional<User> userOptional = repository.findById(userId);
        return userOptional.map(CompletableFuture::completedFuture).orElseGet(() -> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No todo found for given id")));
    }

}
