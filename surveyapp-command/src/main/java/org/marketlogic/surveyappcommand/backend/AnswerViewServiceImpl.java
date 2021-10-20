package org.marketlogic.surveyappcommand.backend;

import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.AnswerRepository;
import org.marketlogic.survey.hateoas.AnswerUpdateService;
import org.marketlogic.survey.model.Answer;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerViewServiceImpl implements AnswerUpdateService {

    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    @Override
    public CompletableFuture<Answer> findById(String answerId) {
        return answerRepository.findById(answerId).map(CompletableFuture::completedFuture).orElseGet(()-> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No todo found for given id")));
    }
}
