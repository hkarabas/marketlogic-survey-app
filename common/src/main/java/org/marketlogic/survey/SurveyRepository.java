package org.marketlogic.survey;

import org.marketlogic.survey.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SurveyRepository extends JpaRepository<Survey, String> {
}
