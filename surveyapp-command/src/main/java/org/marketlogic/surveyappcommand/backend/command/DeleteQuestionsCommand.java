package org.marketlogic.surveyappcommand.backend.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteQuestionsCommand implements QuestionCommand {
    private List<String> ids;
}
