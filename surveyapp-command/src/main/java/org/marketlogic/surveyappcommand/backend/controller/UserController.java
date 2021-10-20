package org.marketlogic.surveyappcommand.backend.controller;


import org.marketlogic.survey.common.controller.BaseController;
import org.marketlogic.survey.common.model.ResourceWithUrl;
import org.marketlogic.survey.hateoas.SurveyHateoasController;
import org.marketlogic.survey.model.User;
import org.marketlogic.survey.model.UserDto;
import org.marketlogic.surveyappcommand.backend.UserViewServiceImpl;
import org.marketlogic.surveyappcommand.backend.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    UserViewServiceImpl userViewServiceImpl;

    @PostMapping
    public CompletableFuture<ResourceWithUrl> saveSurvey(@RequestBody UserDto userDto, HttpServletRequest request) {

        return userService.save(userDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getUser(), e.getEntityId())));
    }

    @DeleteMapping(value = "/{user-id}")
    public CompletableFuture<ResourceWithUrl> deleteOneSurvey(@PathVariable("survey-id") String id, HttpServletRequest request) {
        return userService.remove(id)
                .thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getUser(), e.getEntityId())));
    }

    @DeleteMapping
    public void deleteAllUser() throws Exception {
        List<User> usersToDelete = userViewServiceImpl.getAll();
        if (usersToDelete.size() > 0) {
            userService.deleteAll(usersToDelete
                    .stream()
                    .map(User::getId)
                    .collect(Collectors.toList()));
        }
    }

    @PatchMapping(value = "/{user-id}", headers = {"Content-type=application/json"})
    public CompletableFuture<ResourceWithUrl> updateUser(@PathVariable("user-id") String id, @RequestBody UserDto userDto, HttpServletRequest request) {
        return userService.update(id, userDto).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getUser(), e.getEntityId()))
        );
    }

    protected ResourceWithUrl toResource(UserDto userDto, String id) {
        ResourceWithUrl<UserDto> result = new ResourceWithUrl<>(userDto);
        result.setId(id);
        result.setUrl(linkTo(methodOn(SurveyHateoasController.class).getUser(id)).withSelfRel().getHref());
        return result;
    }

}
