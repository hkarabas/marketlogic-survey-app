package org.marketlogic.surveyappcommand.backend.controller;

import org.marketlogic.survey.common.controller.BaseController;
import org.marketlogic.survey.common.model.ResourceWithUrl;
import org.marketlogic.survey.hateoas.SurveyHateoasController;
import org.marketlogic.survey.model.UserSurveyAnswer;
import org.marketlogic.survey.model.UserSurveyAnswerDto;
import org.marketlogic.surveyappcommand.backend.UserSurveyAnswerServiceImpl;
import org.marketlogic.surveyappcommand.backend.domain.UserSurveyAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/user-survey-answers")
public class UserSurveyAnswerController extends BaseController {

    @Autowired
    private UserSurveyAnswerService userSurveyAnswerService;

    @Autowired
    private UserSurveyAnswerServiceImpl userSurveyAnswerServiceImpl;

    @PostMapping
    public CompletableFuture<ResourceWithUrl> saveUserSurveyAnswer(@RequestBody UserSurveyAnswerDto userSurveyAnswerDto, HttpServletRequest request) {

        return userSurveyAnswerService.save(userSurveyAnswerDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getUserSurveyAnswerDto(), e.getEntityId())));
    }

    @DeleteMapping(value = "/{usersurveyanswer-id}")
    public CompletableFuture<ResourceWithUrl> deleteOneUserSurveyAnswerDto(@PathVariable("usersurveyanswer-id") String id, HttpServletRequest request) {
        return userSurveyAnswerService.remove(id)
                .thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getUserSurveyAnswerDto(), e.getEntityId())));
    }

    @DeleteMapping
    public void deleteAllUserSurveyAnswer() throws Exception {
        List<UserSurveyAnswer> userSurveyAnswerServiceImplToDelete = userSurveyAnswerServiceImpl.getAll();
        if (userSurveyAnswerServiceImplToDelete.size() > 0) {
            userSurveyAnswerService.deleteAll(userSurveyAnswerServiceImplToDelete
                    .stream()
                    .map(UserSurveyAnswer::getId)
                    .collect(Collectors.toList()));
        }
    }

    @PatchMapping(value = "/{usersurveyanswer-id}", headers = {"Content-type=application/json"})
    public CompletableFuture<ResourceWithUrl> updateUserSurveyAnswer(@PathVariable("usersurveyanswer-id") String id, @RequestBody UserSurveyAnswerDto userSurveyAnswerDto, HttpServletRequest request) {
        return userSurveyAnswerService.update(id, userSurveyAnswerDto)
                .thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getUserSurveyAnswerDto(), e.getEntityId())));
    }

    protected ResourceWithUrl toResource(UserSurveyAnswerDto userSurveyAnswerDto, String id) {
        ResourceWithUrl<UserSurveyAnswerDto> result = new ResourceWithUrl<>(userSurveyAnswerDto);
        result.setId(id);
        result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getUserSurveyAnswer(id)).withSelfRel().getHref());
        return result;
    }

}
