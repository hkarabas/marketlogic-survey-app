package org.marketlogic.surveyappcommand.backend.domain;


import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.marketlogic.survey.common.event.QuestionDeletionRequestedEvent;
import org.marketlogic.survey.common.event.SurveyDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteQuestionCommand;
import org.marketlogic.surveyappcommand.backend.command.DeleteSurveyCommand;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "questionCommandSideEventHandlers")
public class QuestionEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<QuestionAggregate>> deleteUser(EventHandlerContext<QuestionDeletionRequestedEvent> ctx) {
        String questionId = ctx.getEvent().getQuestionID();
        return ctx.update(QuestionAggregate.class, questionId, new DeleteQuestionCommand());
    }
}
