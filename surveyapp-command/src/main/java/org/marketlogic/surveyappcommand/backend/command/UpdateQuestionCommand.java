package org.marketlogic.surveyappcommand.backend.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.QuestionDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionCommand implements QuestionCommand {
    private String id;
    private QuestionDto questionDto;
}
