package org.marketlogic.surveyappcommand.backend;

import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.QuestionRepository;
import org.marketlogic.survey.hateoas.QuestionUpdateService;
import org.marketlogic.survey.model.Question;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionViewServiceImpl implements QuestionUpdateService {
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public CompletableFuture<Question> findById(String questionId) {
        return questionRepository.findById(questionId).map(CompletableFuture::completedFuture).orElseGet(() -> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No todo found for given id")));
    }
}
