package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.marketlogic.survey.common.event.SurveyDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteSurveysCommand;
import org.marketlogic.surveyappcommand.backend.command.SurveyCommand;

import java.util.List;
import java.util.stream.Collectors;

public class SurveyBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<SurveyBulkDeleteAggregate, SurveyCommand> {
    public List<Event> process(DeleteSurveysCommand cmd) {
        return cmd.getIds()
                .stream()
                .map(SurveyDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(SurveyDeletionRequestedEvent event) {
    }
}
