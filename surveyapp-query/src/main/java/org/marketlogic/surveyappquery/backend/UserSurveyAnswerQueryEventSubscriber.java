package org.marketlogic.surveyappquery.backend;


import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.common.event.UserSurveyAnswerCreatedEvent;
import org.marketlogic.survey.common.event.UserSurveyAnswerDeletedEvent;
import org.marketlogic.survey.common.event.UserSurveyAnswerUpdatedEvent;
import org.marketlogic.survey.model.UserSurveyAnswer;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EventSubscriber(id = "userSurveyAnswerQuerySideEventHandlers")
public class UserSurveyAnswerQueryEventSubscriber {

    private UserSurveyAnswerUpdateServiceImpl userSurveyAnswerServiceImpl;

    @EventHandlerMethod
    public void create(DispatchedEvent<UserSurveyAnswerCreatedEvent> de) {
        UserSurveyAnswer userSurveyAnswer = new UserSurveyAnswer(de.getEvent().getUserSurveyAnswerDto());
        userSurveyAnswer.setId(de.getEntityId());
        userSurveyAnswerServiceImpl.save(userSurveyAnswer);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<UserSurveyAnswerDeletedEvent> de) {
        userSurveyAnswerServiceImpl.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<UserSurveyAnswerUpdatedEvent> de) {
        UserSurveyAnswer userSurveyAnswer = new UserSurveyAnswer(de.getEvent().getUserSurveyAnswerDto());
        userSurveyAnswer.setId(de.getEntityId());
        userSurveyAnswerServiceImpl.save(userSurveyAnswer);
    }


}
