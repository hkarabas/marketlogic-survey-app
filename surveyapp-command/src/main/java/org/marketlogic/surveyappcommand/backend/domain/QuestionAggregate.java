package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.marketlogic.survey.common.event.QuestionCreatedEvent;
import org.marketlogic.survey.common.event.QuestionDeletedEvent;
import org.marketlogic.survey.common.event.QuestionUpdatedEvent;
import org.marketlogic.survey.model.QuestionDto;
import org.marketlogic.surveyappcommand.backend.command.CreateQuestionCommand;
import org.marketlogic.surveyappcommand.backend.command.DeleteQuestionCommand;
import org.marketlogic.surveyappcommand.backend.command.QuestionCommand;
import org.marketlogic.surveyappcommand.backend.command.UpdateQuestionCommand;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class QuestionAggregate extends ReflectiveMutableCommandProcessingAggregate<QuestionAggregate, QuestionCommand> {

    private QuestionDto questionDto;
    private boolean deleted;

    public List<Event> process(CreateQuestionCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new QuestionCreatedEvent(cmd.getQuestionDto()));
    }

    public List<Event> process(UpdateQuestionCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new QuestionUpdatedEvent(cmd.getQuestionDto()));
    }

    public List<Event> process(DeleteQuestionCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new QuestionDeletedEvent());
    }

}
