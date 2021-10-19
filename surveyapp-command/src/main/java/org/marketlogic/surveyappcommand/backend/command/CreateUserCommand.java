package org.marketlogic.surveyappcommand.backend.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.UserDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCommand implements UserCommand {
    private UserDto userDto;
}
