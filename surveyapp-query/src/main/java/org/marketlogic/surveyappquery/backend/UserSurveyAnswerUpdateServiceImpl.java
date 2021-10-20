package org.marketlogic.surveyappquery.backend;


import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.UserSurveyAnswerRepository;
import org.marketlogic.survey.hateoas.UserSurveyAnswerUpdateService;
import org.marketlogic.survey.model.UserSurveyAnswer;
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
public class UserSurveyAnswerUpdateServiceImpl implements UserSurveyAnswerUpdateService {

    private UserSurveyAnswerRepository repository;


    @Override
    public List<UserSurveyAnswer> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<UserSurveyAnswer> findById(String userSurveyAnswerId) {
        Optional<UserSurveyAnswer> optionalUserSurveyAnswer = repository.findById(userSurveyAnswerId);
        return optionalUserSurveyAnswer.map(CompletableFuture::completedFuture).orElseGet(() ->
                CompletableFutureUtil.failedFuture(new EntityNotFoundException("No User Survey Answer found for given id")));
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public UserSurveyAnswer save(UserSurveyAnswer userSurveyAnswer) {
        return repository.save(userSurveyAnswer);
    }
}
