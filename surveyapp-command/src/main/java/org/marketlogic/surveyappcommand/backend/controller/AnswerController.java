package org.marketlogic.surveyappcommand.backend.controller;

import org.marketlogic.survey.common.controller.BaseController;
import org.marketlogic.survey.common.model.ResourceWithUrl;
import org.marketlogic.survey.hateoas.SurveyHateoasController;
import org.marketlogic.survey.model.Answer;
import org.marketlogic.survey.model.AnswerDto;
import org.marketlogic.surveyappcommand.backend.AnswerViewServiceImpl;
import org.marketlogic.surveyappcommand.backend.domain.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/answers")
public class AnswerController extends BaseController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerViewServiceImpl answerViewServiceImpl;

    @PostMapping
    public CompletableFuture<ResourceWithUrl> saveAnswer(@RequestBody AnswerDto AnswerDto, HttpServletRequest request) {

        return answerService.save(AnswerDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getAnswerDto(), e.getEntityId())));
    }

    @DeleteMapping(value = "/{answer-id}")
    public CompletableFuture<ResourceWithUrl> deleteOneAnswer(@PathVariable("answer-id") String id, HttpServletRequest request) {
        return answerService.remove(id)
                .thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getAnswerDto(), e.getEntityId())));
    }

    @DeleteMapping
    public void deleteAllSurvey() throws Exception {
        List<Answer> AnswersToDelete = answerViewServiceImpl.getAll();
        if (AnswersToDelete.size() > 0) {
            answerService.deleteAll(AnswersToDelete
                    .stream()
                    .map(Answer::getId)
                    .collect(Collectors.toList()));
        }
    }

    @PatchMapping(value = "/{answer-id}", headers = {"Content-type=application/json"})
    public CompletableFuture<ResourceWithUrl> updateAnswer(@PathVariable("answer-id") String id, @RequestBody AnswerDto answerDto, HttpServletRequest request) {
        return answerService.update(id, answerDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getAnswerDto(), e.getEntityId()))
        );
    }

    protected ResourceWithUrl toResource(AnswerDto answerDto, String id) {
        ResourceWithUrl<AnswerDto> result = new ResourceWithUrl<>(answerDto);
        result.setId(id);
        result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getAnswer(id)).withSelfRel().getHref());
        return result;
    }


}
