package org.marketlogic.survey.common.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.QuestionDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionUpdatedEvent implements  QuestionEvent {
    private QuestionDto questionDto;
}
