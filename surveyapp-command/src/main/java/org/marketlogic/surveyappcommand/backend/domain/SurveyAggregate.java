package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.marketlogic.survey.common.event.SurveyCreatedEvent;
import org.marketlogic.survey.common.event.SurveyDeletedEvent;
import org.marketlogic.survey.common.event.SurveyUpdatedEvent;
import org.marketlogic.survey.model.SurveyDto;
import org.marketlogic.surveyappcommand.backend.command.CreateSurveyCommand;
import org.marketlogic.surveyappcommand.backend.command.DeleteSurveyCommand;
import org.marketlogic.surveyappcommand.backend.command.SurveyCommand;
import org.marketlogic.surveyappcommand.backend.command.UpdateSurveyCommand;

import java.util.Collections;
import java.util.List;

public class SurveyAggregate extends ReflectiveMutableCommandProcessingAggregate<SurveyAggregate, SurveyCommand> {

    private SurveyDto survey;
    private boolean deleted;

    public List<Event> process(CreateSurveyCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new SurveyCreatedEvent(cmd.getSurveyDto()));
    }

    public List<Event> process(UpdateSurveyCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new SurveyUpdatedEvent(cmd.getSurveyDto()));
    }

    public List<Event> process(DeleteSurveyCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new SurveyDeletedEvent());
    }

}
