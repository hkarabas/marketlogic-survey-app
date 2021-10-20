package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.marketlogic.survey.common.event.UserSurveyAnswerCreatedEvent;
import org.marketlogic.survey.common.event.UserSurveyAnswerUpdatedEvent;
import org.marketlogic.survey.model.UserSurveyAnswerDto;
import org.marketlogic.surveyappcommand.backend.command.CreateUserSurveyAnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.DeleteUserSurveyAnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.UpdateUserSurveyAnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.UserSurveyAnswerCommand;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserSurveyAnswerAggregate extends ReflectiveMutableCommandProcessingAggregate<UserSurveyAnswerAggregate, UserSurveyAnswerCommand> {
    private UserSurveyAnswerDto userSurveyAnswerDto;
    private boolean deleted;

    public List<Event> process(CreateUserSurveyAnswerCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new UserSurveyAnswerCreatedEvent(cmd.getUserSurveyAnswerDto()));
    }

    public List<Event> process(UpdateUserSurveyAnswerCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new UserSurveyAnswerUpdatedEvent(cmd.getUserSurveyAnswerDto()));
    }

    public List<Event> process(DeleteUserSurveyAnswerCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new UserSurveyAnswerUpdatedEvent());
    }

}
