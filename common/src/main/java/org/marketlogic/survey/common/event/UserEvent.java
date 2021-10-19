package org.marketlogic.survey.common.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.marketlogic.surveyappcommand.backend.domain.UserAggregate")
public interface UserEvent extends Event {
}
