package org.marketlogic.survey;

import org.marketlogic.survey.model.UserSurveyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSurveyAnswerRepository extends JpaRepository<UserSurveyAnswer, String> {
}
