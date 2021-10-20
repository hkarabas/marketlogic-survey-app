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
public class Answer {

    @Id
    private String id;

    @Column
    private String questionId;

    @Column
    private String answer_desc;

    public Answer(AnswerDto answerDto) {
        this.questionId = answerDto.getQuestionId();
        this.answer_desc = answerDto.getAnswer_desc();
    }

}
