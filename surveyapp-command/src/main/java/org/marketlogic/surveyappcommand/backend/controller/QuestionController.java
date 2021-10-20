package org.marketlogic.surveyappcommand.backend.controller;


import org.marketlogic.survey.common.controller.BaseController;
import org.marketlogic.survey.common.model.ResourceWithUrl;
import org.marketlogic.survey.hateoas.SurveyHateoasController;
import org.marketlogic.survey.model.Question;
import org.marketlogic.survey.model.QuestionDto;
import org.marketlogic.surveyappcommand.backend.QuestionViewServiceImpl;
import org.marketlogic.surveyappcommand.backend.domain.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/questions")
public class QuestionController extends BaseController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionViewServiceImpl questionViewServiceImpl;

    @PostMapping
    public CompletableFuture<ResourceWithUrl> saveQuestion(@RequestBody QuestionDto questionDto, HttpServletRequest request) {

        return questionService.save(questionDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getQuestionDto(), e.getEntityId())));
    }

    @DeleteMapping(value = "/{question-id}")
    public CompletableFuture<ResourceWithUrl> deleteOneQuestion(@PathVariable("survey-id") String id, HttpServletRequest request) {
        return questionService.remove(id)
                .thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getQuestionDto(), e.getEntityId())));
    }

    @DeleteMapping
    public void deleteAllSurvey() throws Exception {
        List<Question> questionsToDelete = questionViewServiceImpl.getAll();
        if (questionsToDelete.size() > 0) {
            questionService.deleteAll(questionsToDelete
                    .stream()
                    .map(Question::getId)
                    .collect(Collectors.toList()));
        }
    }

    @PatchMapping(value = "/{question-id}", headers = {"Content-type=application/json"})
    public CompletableFuture<ResourceWithUrl> updateQuestion(@PathVariable("question-id") String id, @RequestBody QuestionDto questionDto, HttpServletRequest request) {
        return questionService.update(id, questionDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getQuestionDto(), e.getEntityId()))
        );
    }

    protected ResourceWithUrl toResource(QuestionDto questionDto, String id) {
        ResourceWithUrl<QuestionDto> result = new ResourceWithUrl<>(questionDto);
        result.setId(id);
        result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getQuestion(id)).withSelfRel().getHref());
        return result;
    }

}
