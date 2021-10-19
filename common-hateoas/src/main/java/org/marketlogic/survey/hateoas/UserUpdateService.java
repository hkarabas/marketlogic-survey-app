package org.marketlogic.survey.hateoas;

import org.marketlogic.survey.model.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface UserUpdateService {

   public List<User> getAll();

   public CompletableFuture<User> findById(String userId);
}
