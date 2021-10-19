package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.marketlogic.survey.common.event.UserDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteUsersCommand;
import org.marketlogic.surveyappcommand.backend.command.UserCommand;

import java.util.List;
import java.util.stream.Collectors;

public class UserBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<UserBulkDeleteAggregate, UserCommand> {
    public List<Event> process(DeleteUsersCommand cmd) {
        return cmd.getIds()
                .stream()
                .map(UserDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(UserDeletionRequestedEvent event) {

    }
}
