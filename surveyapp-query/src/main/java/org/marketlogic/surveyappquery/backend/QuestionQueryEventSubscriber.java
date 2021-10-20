package org.marketlogic.surveyappquery.backend;


import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.common.event.QuestionCreatedEvent;
import org.marketlogic.survey.common.event.QuestionDeletedEvent;
import org.marketlogic.survey.common.event.QuestionUpdatedEvent;
import org.marketlogic.survey.model.Question;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EventSubscriber(id = "questionQuerySideEventHandlers")
public class QuestionQueryEventSubscriber {

    private QuestionUpdateServiceImpl questionUpdateServiceImpl;

    @EventHandlerMethod
    public void create(DispatchedEvent<QuestionCreatedEvent> de) {
        Question question = new Question(de.getEvent().getQuestionDto());
        question.setId(de.getEntityId());
        questionUpdateServiceImpl.save(question);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<QuestionDeletedEvent> de) {
        questionUpdateServiceImpl.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<QuestionUpdatedEvent> de) {
        Question question = new Question(de.getEvent().getQuestionDto());
        question.setId(de.getEntityId());
        questionUpdateServiceImpl.save(question);
    }


}
