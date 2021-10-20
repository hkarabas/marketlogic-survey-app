package org.marketlogic.surveyappquery.backend;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.common.event.UserCreatedEvent;
import org.marketlogic.survey.common.event.UserDeletedEvent;
import org.marketlogic.survey.common.event.UserUpdatedEvent;
import org.marketlogic.survey.model.User;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EventSubscriber(id = "userQuerySideEventHandlers")
public class UserQueryEventSubscriber {

    private UserUpdateServiceImpl userUpdateServiceImpl;

    @EventHandlerMethod
    public void create(DispatchedEvent<UserCreatedEvent> de) {
        User user = new User(de.getEvent().getUserDto());
        user.setId(de.getEntityId());
        userUpdateServiceImpl.save(user);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<UserDeletedEvent> de) {
        userUpdateServiceImpl.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<UserUpdatedEvent> de) {
        User user = new User(de.getEvent().getUserDto());
        user.setId(de.getEntityId());
        userUpdateServiceImpl.save(user);
    }

}
