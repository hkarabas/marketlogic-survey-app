package org.marketlogic.survey.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSurveyAnswerDto {

    private String surveyId;

    private String answerId;

    private String userId;
}
