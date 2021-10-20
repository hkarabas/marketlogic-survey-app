package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.marketlogic.survey.common.event.UserCreatedEvent;
import org.marketlogic.survey.common.event.UserDeletedEvent;
import org.marketlogic.survey.common.event.UserUpdatedEvent;
import org.marketlogic.survey.model.UserDto;
import org.marketlogic.surveyappcommand.backend.command.CreateUserCommand;
import org.marketlogic.surveyappcommand.backend.command.DeleteUserCommand;
import org.marketlogic.surveyappcommand.backend.command.UpdateUserCommand;
import org.marketlogic.surveyappcommand.backend.command.UserCommand;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserAggregate extends ReflectiveMutableCommandProcessingAggregate<UserAggregate, UserCommand> {
    private UserDto user;
    private boolean deleted;

    public List<Event> process(CreateUserCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new UserCreatedEvent(cmd.getUserDto()));
    }

    public List<Event> process(UpdateUserCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new UserUpdatedEvent(cmd.getUserDto()));
    }

    public List<Event> process(DeleteUserCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new UserDeletedEvent());
    }

}
