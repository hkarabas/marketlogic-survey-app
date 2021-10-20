package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.marketlogic.survey.common.event.AnswerCreatedEvent;
import org.marketlogic.survey.common.event.AnswerDeletedEvent;
import org.marketlogic.survey.common.event.AnswerUpdatedEvent;
import org.marketlogic.survey.model.AnswerDto;
import org.marketlogic.surveyappcommand.backend.command.AnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.CreateAnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.DeleteAnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.UpdateAnswerCommand;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class AnswerAggregate extends ReflectiveMutableCommandProcessingAggregate<AnswerAggregate, AnswerCommand> {

    private AnswerDto answerDto;
    private boolean deleted;

    public List<Event> process(CreateAnswerCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new AnswerCreatedEvent(cmd.getAnswerDto()));
    }

    public List<Event> process(UpdateAnswerCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new AnswerUpdatedEvent(cmd.getAnswerDto()));
    }

    public List<Event> process(DeleteAnswerCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new AnswerDeletedEvent());
    }


}
