package org.marketlogic.surveyappcommand.backend.controller;


import org.marketlogic.survey.common.controller.BaseController;
import org.marketlogic.survey.common.model.ResourceWithUrl;
import org.marketlogic.survey.hateoas.SurveyHateoasController;
import org.marketlogic.survey.model.Survey;
import org.marketlogic.survey.model.SurveyDto;
import org.marketlogic.surveyappcommand.backend.SurveyViewServiceImpl;
import org.marketlogic.surveyappcommand.backend.domain.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/surveys")
public class SurveyController extends BaseController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyViewServiceImpl surveyViewServiceImpl;

    @PostMapping
    public CompletableFuture<ResourceWithUrl> saveSurvey(@RequestBody SurveyDto surveyDto, HttpServletRequest request) {

        return surveyService.save(surveyDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getSurvey(), e.getEntityId())));
    }

    @DeleteMapping(value = "/{survey-id}")
    public CompletableFuture<ResourceWithUrl> deleteOneSurvey(@PathVariable("survey-id") String id, HttpServletRequest request) {
        return surveyService.remove(id)
                .thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getSurvey(), e.getEntityId())));
    }

    @DeleteMapping
    public void deleteAllSurvey() throws Exception {
        List<Survey> surveysToDelete = surveyViewServiceImpl.getAll();
        if (surveysToDelete.size() > 0) {
            surveyService.deleteAll(surveysToDelete
                    .stream()
                    .map(Survey::getId)
                    .collect(Collectors.toList()));
        }
    }

    @PatchMapping(value = "/{survey-id}", headers = {"Content-type=application/json"})
    public CompletableFuture<ResourceWithUrl> updateSurvey(@PathVariable("survey-id") String id, @RequestBody SurveyDto surveyDto, HttpServletRequest request) {
        return surveyService.update(id, surveyDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getSurvey(), e.getEntityId()))
        );
    }

    protected ResourceWithUrl toResource(SurveyDto surveyDto, String id) {
        ResourceWithUrl<SurveyDto> result = new ResourceWithUrl<>(surveyDto);
        result.setId(id);
        result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getSurvey(id)).withSelfRel().getHref());
        return result;
    }
}
