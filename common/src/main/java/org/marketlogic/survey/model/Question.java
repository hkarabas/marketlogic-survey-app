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
public class Question {

    @Id
    private String id;

    @Column
    private String surveyId;

    @Column
    private String question_asked;

    public Question(QuestionDto questionDto) {
        this.surveyId = questionDto.getSurveyId();
        this.question_asked = questionDto.getQuestion_asked();
    }
}
