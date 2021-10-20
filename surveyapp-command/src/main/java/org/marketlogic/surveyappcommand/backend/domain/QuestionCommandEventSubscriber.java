package org.marketlogic.surveyappcommand.backend.domain;


import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.marketlogic.survey.common.event.QuestionDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteQuestionCommand;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "questionCommandSideEventHandlers")
public class QuestionCommandEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<QuestionAggregate>> deleteUser(EventHandlerContext<QuestionDeletionRequestedEvent> ctx) {
        String questionId = ctx.getEvent().getQuestionID();
        return ctx.update(QuestionAggregate.class, questionId, new DeleteQuestionCommand());
    }
}
