package org.marketlogic.surveyappcommand.backend.domain;


import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.marketlogic.survey.common.event.SurveyDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteSurveyCommand;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "surveyCommandSideEventHandlers")
public class SurveyEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<SurveyAggregate>> deleteUser(EventHandlerContext<SurveyDeletionRequestedEvent> ctx) {
        String surveyId = ctx.getEvent().getSurveyID();
        return ctx.update(SurveyAggregate.class, surveyId, new DeleteSurveyCommand());
    }

}
