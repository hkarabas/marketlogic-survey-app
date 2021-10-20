package org.marketlogic.survey.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSurveyAnswer {
    @Id
    private String id;

    @Column
    private String surveyId;

    @Column
    private String answerId;

    @Column
    private String userId;

    public UserSurveyAnswer(UserSurveyAnswerDto userSurveyAnswerDto) {
        this.surveyId = userSurveyAnswerDto.getSurveyId();
        this.answerId = userSurveyAnswerDto.getAnswerId();
        this.userId = userSurveyAnswerDto.getUserId();
    }

}
