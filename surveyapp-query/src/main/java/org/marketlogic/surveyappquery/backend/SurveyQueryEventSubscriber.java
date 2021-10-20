package org.marketlogic.surveyappquery.backend;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketlogic.survey.common.event.SurveyCreatedEvent;
import org.marketlogic.survey.common.event.SurveyDeletedEvent;
import org.marketlogic.survey.common.event.SurveyUpdatedEvent;
import org.marketlogic.survey.model.Survey;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EventSubscriber(id = "surveyQuerySideEventHandlers")
public class SurveyQueryEventSubscriber {

    private SurveyUpdateServiceImpl surveyUpdateServiceimpl;

    @EventHandlerMethod
    public void create(DispatchedEvent<SurveyCreatedEvent> de) {
        Survey survey = new Survey(de.getEvent().getSurveyDto());
        survey.setId(de.getEntityId());
        surveyUpdateServiceimpl.save(survey);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<SurveyDeletedEvent> de) {
        surveyUpdateServiceimpl.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<SurveyUpdatedEvent> de) {
        Survey survey = new Survey(de.getEvent().getSurveyDto());
        survey.setId(de.getEntityId());
        surveyUpdateServiceimpl.save(survey);
    }


}
