package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.marketlogic.survey.common.event.AnswerDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.AnswerCommand;
import org.marketlogic.surveyappcommand.backend.command.DeleteAnswersCommand;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<AnswerBulkDeleteAggregate, AnswerCommand> {

    public List<Event> process(DeleteAnswersCommand cmd) {
        return cmd.getIds()
                .stream()
                .map(AnswerDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(AnswerDeletionRequestedEvent event) {
    }


}
