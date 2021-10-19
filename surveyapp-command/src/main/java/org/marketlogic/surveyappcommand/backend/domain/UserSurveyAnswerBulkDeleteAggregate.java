package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.marketlogic.survey.common.event.UserSurveyAnswerDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteUserSurveyAnswersCommand;
import org.marketlogic.surveyappcommand.backend.command.UserSurveyAnswerCommand;

import java.util.List;
import java.util.stream.Collectors;

public class UserSurveyAnswerBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<UserSurveyAnswerBulkDeleteAggregate, UserSurveyAnswerCommand> {

    public List<Event> process(DeleteUserSurveyAnswersCommand cmd) {
        return cmd.getIds()
                .stream()
                .map(UserSurveyAnswerDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(UserSurveyAnswerDeletionRequestedEvent event) {
    }

}
