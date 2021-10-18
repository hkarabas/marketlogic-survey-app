package org.marketlogic.survey.common.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.UserSurveyAnswerDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSurveyAnswerCreatedEvent implements UserSurveyAnswerEvent {
    private UserSurveyAnswerDto userSurveyAnswerDto;
}
