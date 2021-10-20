package org.marketlogic.survey;

import org.marketlogic.survey.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
