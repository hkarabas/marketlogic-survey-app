package org.marketlogic.survey.common.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.marketlogic.surveyappcommand.backend.domain.QuestionAggregate")
public interface QuestionEvent extends Event {
}
