package backend.command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.UserSurveyAnswerDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserSurveyAnswerCommand implements  UserSurveyAnswerCommand{
    private UserSurveyAnswerDto userSurveyAnswerDto;
}
