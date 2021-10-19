package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.marketlogic.survey.common.event.QuestionDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteQuestionsCommand;
import org.marketlogic.surveyappcommand.backend.command.QuestionCommand;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<QuestionBulkDeleteAggregate, QuestionCommand> {
    public List<Event> process(DeleteQuestionsCommand cmd) {
        return cmd.getIds()
                .stream()
                .map(QuestionDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(QuestionDeletionRequestedEvent event) {
    }
}
