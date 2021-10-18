package backend.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.SurveyDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSurveyCommand implements SurveyCommand {
    private SurveyDto surveyDto;
}
