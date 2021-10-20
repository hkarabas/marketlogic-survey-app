package org.marketlogic.survey.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_survey_answer")
public class UserSurveyAnswer {
    @Id
    private String id;

    @Column(name="survey_id")
    private String surveyId;

    @Column(name="answer_id")
    private String answerId;

    @Column(name = "user_id")
    private String userId;

    public UserSurveyAnswer(UserSurveyAnswerDto userSurveyAnswerDto) {
        this.surveyId = userSurveyAnswerDto.getSurveyId();
        this.answerId = userSurveyAnswerDto.getAnswerId();
        this.userId = userSurveyAnswerDto.getUserId();
    }

}
