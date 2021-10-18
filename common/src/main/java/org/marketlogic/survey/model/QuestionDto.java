package org.marketlogic.survey.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {

    private String id;

    private String surveyId;

    private String question_asked;
}
