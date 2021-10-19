package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.marketlogic.survey.common.event.*;
import org.marketlogic.survey.model.AnswerDto;
import org.marketlogic.surveyappcommand.backend.command.AnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.CreateAnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.DeleteAnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.UpdateAnswerCommand;

import java.util.Collections;
import java.util.List;

public class AnswerAggregate extends ReflectiveMutableCommandProcessingAggregate<AnswerAggregate, AnswerCommand> {
    
    private AnswerDto survey;
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
