package org.marketlogic.surveyappquery.backend;


import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.UserRepository;
import org.marketlogic.survey.hateoas.UserUpdateService;
import org.marketlogic.survey.model.User;
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
public class UserUpdateServiceImpl implements UserUpdateService {

    private UserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<User> findById(String userid) {
        Optional<User> optionalUser = repository.findById(userid);
        return optionalUser.map(CompletableFuture::completedFuture).orElseGet(() -> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No User found for given id")));
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public User save(User user) {
        return repository.save(user);
    }


}
