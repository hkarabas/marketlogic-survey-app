package org.marketlogic.survey.common.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.SurveyDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SurveyCreatedEvent implements SurveyEvent {
    private SurveyDto surveyDto;

}
