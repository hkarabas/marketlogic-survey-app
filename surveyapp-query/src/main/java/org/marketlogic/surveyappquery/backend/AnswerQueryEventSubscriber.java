package org.marketlogic.surveyappquery.backend;


import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.common.event.AnswerCreatedEvent;
import org.marketlogic.survey.common.event.AnswerDeletedEvent;
import org.marketlogic.survey.common.event.AnswerUpdatedEvent;
import org.marketlogic.survey.model.Answer;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EventSubscriber(id = "answerQuerySideEventHandlers")
public class AnswerQueryEventSubscriber {

    private AnswerUpdateServiceImpl answerUpdateServiceImpl;

    @EventHandlerMethod
    public void create(DispatchedEvent<AnswerCreatedEvent> de) {
        Answer answer = new Answer(de.getEvent().getAnswerDto());
        answer.setId(de.getEntityId());
        answerUpdateServiceImpl.save(answer);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<AnswerDeletedEvent> de) {
        answerUpdateServiceImpl.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<AnswerUpdatedEvent> de) {
        Answer answer = new Answer(de.getEvent().getAnswerDto());
        answer.setId(de.getEntityId());
        answerUpdateServiceImpl.save(answer);
    }


}
