package org.marketlogic.surveyappcommand.backend;

import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.UserSurveyAnswerRepository;
import org.marketlogic.survey.hateoas.UserSurveyAnswerUpdateService;
import org.marketlogic.survey.model.UserSurveyAnswer;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSurveyAnswerServiceImpl implements UserSurveyAnswerUpdateService {
    private UserSurveyAnswerRepository userSurveyAnswerRepository;

    @Override
    public List<UserSurveyAnswer> getAll() {
        return userSurveyAnswerRepository.findAll();
    }

    @Override
    public CompletableFuture<UserSurveyAnswer> findById(String userSurveyAnswerId) {
        Optional<UserSurveyAnswer> optionalUserSurveyAnswer = userSurveyAnswerRepository.findById(userSurveyAnswerId);
        return optionalUserSurveyAnswer.map(CompletableFuture::completedFuture).orElseGet(()-> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No todo found for given id")));
    }
}
