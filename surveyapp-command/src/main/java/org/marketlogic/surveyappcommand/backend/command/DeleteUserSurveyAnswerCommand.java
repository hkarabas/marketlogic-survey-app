package org.marketlogic.surveyappcommand.backend.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserSurveyAnswerCommand implements  UserSurveyAnswerCommand{
    private String id;
}
