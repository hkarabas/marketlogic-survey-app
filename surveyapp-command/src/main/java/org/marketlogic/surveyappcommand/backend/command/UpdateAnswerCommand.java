package org.marketlogic.surveyappcommand.backend.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.AnswerDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnswerCommand implements AnswerCommand {
    private String id;
    private AnswerDto answerDto;
}
