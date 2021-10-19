package org.marketlogic.surveyappcommand.backend.command;

import io.eventuate.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteSurveyCommand implements SurveyCommand {
    private  String id;
}
