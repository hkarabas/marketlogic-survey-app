package org.marketlogic.surveyappquery.controller;

import org.marketlogic.survey.common.controller.BaseController;
import org.marketlogic.survey.common.model.ResourceWithUrl;
import org.marketlogic.survey.hateoas.SurveyHateoasController;
import org.marketlogic.survey.model.Survey;
import org.marketlogic.surveyappquery.backend.SurveyUpdateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/surveys")
public class SurveyQueryController extends BaseController {

    @Autowired
    private SurveyUpdateServiceImpl surveyUpdateService;


    @GetMapping
    public HttpEntity<Collection<ResourceWithUrl>> listAll() {
        List<ResourceWithUrl> resourceWithUrls = surveyUpdateService.getAll().stream().map(this::toResource).collect(Collectors.toList());
        return new ResponseEntity<>(resourceWithUrls, OK);
    }

    public ResourceWithUrl toResource(Survey survey) {
        ResourceWithUrl<Survey> result = new ResourceWithUrl<>(survey);
        if (survey != null) {
            result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getSurvey(survey.getId())).withSelfRel().getHref());
        }
        return result;
    }


}
