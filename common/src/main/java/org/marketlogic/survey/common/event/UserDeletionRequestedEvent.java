package org.marketlogic.survey.common.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EventEntity(entity = "net.chrisrichardson.eventstore.examples.todolist.todoservice.backend.domain.TodoBulkDeleteAggregate")
public class UserDeletionRequestedEvent implements Event {
    private String userId;
}
