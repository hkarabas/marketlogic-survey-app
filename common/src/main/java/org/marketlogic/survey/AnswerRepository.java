package org.marketlogic.survey;

import org.marketlogic.survey.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,String> {
}
