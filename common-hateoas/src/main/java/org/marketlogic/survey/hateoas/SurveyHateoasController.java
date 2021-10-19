package org.marketlogic.survey.hateoas;

import org.marketlogic.survey.common.controller.BaseController;
import org.marketlogic.survey.common.model.ResourceWithUrl;
import org.marketlogic.survey.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users")
public class SurveyHateoasController extends BaseController {

    @Autowired
    private UserUpdateService userUpdateService;

    @Autowired
    private QuestionUpdateService questionUpdateService;

    @Autowired
    private AnswerUpdateService answerUpdateService;

    @Autowired
    private UserSurveyAnswerUpdateService userSurveyAnswerUpdateService;

    @Autowired
    private SurveyUpdateService surveyUpdateService;


    @GetMapping(value = "/{user-id}")
    public CompletableFuture<ResourceWithUrl> getUser(@PathVariable("user-id") String id) {
        return userUpdateService.findById(id).thenApply(this::toResourceUser);
    }

    public ResourceWithUrl toResourceUser(User user) {
        ResourceWithUrl<User> result = new ResourceWithUrl<>(user);
        if (user != null) {
            result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getUser(user.getId())).withSelfRel().getHref());
        }
        return result;
    }

    @GetMapping(value = "/{question-id}")
    public CompletableFuture<ResourceWithUrl> getQuestion(@PathVariable("question-id") String id) {
        return questionUpdateService.findById(id).thenApply(this::toResourceQuestion);
    }

    public ResourceWithUrl toResourceQuestion(Question question) {
        ResourceWithUrl<Question> result = new ResourceWithUrl<>(question);
        if (question != null) {
            result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getQuestion(question.getId())).withSelfRel().getHref());
        }
        return result;
    }

    @GetMapping(value = "/{answer-id}")
    public CompletableFuture<ResourceWithUrl> getAnswer(@PathVariable("answer-id") String id) {
        return answerUpdateService.findById(id).thenApply(this::toResourceAnswer);
    }

    public ResourceWithUrl toResourceAnswer(Answer answer) {
        ResourceWithUrl<Answer> result = new ResourceWithUrl<>(answer);
        if (answer != null) {
            result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getAnswer(answer.getId())).withSelfRel().getHref());
        }
        return result;
    }

    @GetMapping(value = "/{usersurveyanswer-id}")
    public CompletableFuture<ResourceWithUrl> getUserSurveyAnswer(@PathVariable("usersurveyanswer-id") String id) {
        return userSurveyAnswerUpdateService.findById(id).thenApply(this::toResourceUserSurveyAnswer);
    }

    public ResourceWithUrl toResourceUserSurveyAnswer(UserSurveyAnswer userSurveyAnswer) {
        ResourceWithUrl<UserSurveyAnswer> result = new ResourceWithUrl<>(userSurveyAnswer);
        if (userSurveyAnswer != null) {
            result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getUserSurveyAnswer(userSurveyAnswer.getId())).withSelfRel().getHref());
        }
        return result;
    }

    @GetMapping(value = "/{survey-id}")
    public CompletableFuture<ResourceWithUrl> getSurvey(@PathVariable("survey-id") String id) {
        return surveyUpdateService.findById(id).thenApply(this::toResourceSurvey);
    }

    public ResourceWithUrl toResourceSurvey(Survey survey) {
        ResourceWithUrl<Survey> result = new ResourceWithUrl<>(survey);
        if (survey != null) {
            result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getUserSurveyAnswer(survey.getId())).withSelfRel().getHref());
        }
        return result;
    }

}
