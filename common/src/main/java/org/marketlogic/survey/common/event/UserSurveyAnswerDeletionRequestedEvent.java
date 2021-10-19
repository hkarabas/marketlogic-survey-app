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
@EventEntity(entity = "org.marketlogic.surveyappcommand.backend.domain.UserSurveyAnswerBulkDeleteAggregate")
public class UserSurveyAnswerDeletionRequestedEvent implements Event {
    private String userSurveyAnswerID;
}
