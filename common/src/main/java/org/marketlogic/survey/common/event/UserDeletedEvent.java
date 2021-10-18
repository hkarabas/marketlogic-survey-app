package org.marketlogic.survey.common.event;

import io.eventuate.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.model.UserDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDeletedEvent implements UserEvent {
    private UserDto userDto;
}
