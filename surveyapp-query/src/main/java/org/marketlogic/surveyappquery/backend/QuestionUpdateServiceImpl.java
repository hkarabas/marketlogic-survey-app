package org.marketlogic.surveyappquery.backend;

import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.QuestionRepository;
import org.marketlogic.survey.hateoas.QuestionUpdateService;
import org.marketlogic.survey.model.Question;
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
public class QuestionUpdateServiceImpl implements QuestionUpdateService {

    private QuestionRepository repository;

    @Override
    public List<Question> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<Question> findById(String questionId) {
        Optional<Question> optionalQuestion = repository.findById(questionId);
        return optionalQuestion.map(CompletableFuture::completedFuture).orElseGet(() -> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No Question found for given id")));
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public Question save(Question question) {
        return repository.save(question);
    }

}
