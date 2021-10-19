package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.marketlogic.survey.common.event.UserSurveyAnswerDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteUserSurveyAnswerCommand;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "userSurveyAnswerCommandSideEventHandlers")
public class UserSurveyAnswerEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<UserSurveyAnswerAggregate>> deleteUser(EventHandlerContext<UserSurveyAnswerDeletionRequestedEvent> ctx) {
        String userSurveyAnswerID = ctx.getEvent().getUserSurveyAnswerID();
        return ctx.update(UserSurveyAnswerAggregate.class, userSurveyAnswerID, new DeleteUserSurveyAnswerCommand());
    }
}
