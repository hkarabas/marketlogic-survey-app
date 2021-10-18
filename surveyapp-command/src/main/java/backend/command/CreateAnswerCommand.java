package backend.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.AnswerDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnswerCommand implements AnswerCommand {
    private AnswerDto answerDto;
}
