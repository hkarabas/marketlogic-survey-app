package org.marketlogic.survey.common.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.AnswerDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerUpdatedEvent implements AnswerEvent {
    private AnswerDto answerDto;
}
