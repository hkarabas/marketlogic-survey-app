package org.marketlogic.survey.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto {

    private String id;

    private String questionId;

    private String answer_desc;


}
