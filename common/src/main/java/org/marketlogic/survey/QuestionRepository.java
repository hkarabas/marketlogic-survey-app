package org.marketlogic.survey;

import org.marketlogic.survey.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, String> {
}
