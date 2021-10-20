package org.marketlogic.surveyappquery.backend;

import io.eventuate.CompletableFutureUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.AnswerRepository;
import org.marketlogic.survey.hateoas.AnswerUpdateService;
import org.marketlogic.survey.model.Answer;
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
public class AnswerUpdateServiceImpl implements AnswerUpdateService {

    private AnswerRepository repository;

    @Override
    public List<Answer> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<Answer> findById(String answerId) {
        Optional<Answer> optionalAnswer = repository.findById(answerId);
        return optionalAnswer.map(CompletableFuture::completedFuture).orElseGet(() -> CompletableFutureUtil.failedFuture(new EntityNotFoundException("No Answer found for given id")));
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public Answer save(Answer answer) {
        return repository.save(answer);
    }
}
