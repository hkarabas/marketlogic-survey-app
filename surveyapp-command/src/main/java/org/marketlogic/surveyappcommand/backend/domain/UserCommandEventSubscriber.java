package org.marketlogic.surveyappcommand.backend.domain;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.marketlogic.survey.common.event.UserDeletionRequestedEvent;
import org.marketlogic.surveyappcommand.backend.command.DeleteUserCommand;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "userCommandSideEventHandlers")
public class UserCommandEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> deleteUser(EventHandlerContext<UserDeletionRequestedEvent> ctx) {
        String userId = ctx.getEvent().getUserId();
        return ctx.update(UserAggregate.class, userId, new DeleteUserCommand());
    }


}
