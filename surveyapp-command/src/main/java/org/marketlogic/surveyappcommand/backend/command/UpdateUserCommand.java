package org.marketlogic.surveyappcommand.backend.command;

import io.eventuate.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.UserDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand implements UserCommand {
    private String id;
    private UserDto userDto;
}
