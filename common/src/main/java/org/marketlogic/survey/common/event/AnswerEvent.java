package org.marketlogic.survey.common.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "----")
public interface AnswerEvent extends Event {
}
