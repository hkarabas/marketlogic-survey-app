package org.marketlogic.surveyappcommand.backend.domain;


import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.marketlogic.survey.common.event.AnswerDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteAnswerCommand;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "answerCommandSideEventHandlers")
public class AnswerCommandEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<AnswerAggregate>> deleteUser(EventHandlerContext<AnswerDeletionRequestedEvent> ctx) {
        String answerId = ctx.getEvent().getAnswerId();
        return ctx.update(AnswerAggregate.class, answerId, new DeleteAnswerCommand());
    }
}
