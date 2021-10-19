package org.marketlogic.surveyappcommand.backend;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.marketlogic.survey.UserRepository;
import org.marketlogic.surveyappcommand.backend.command.UserCommand;
import org.marketlogic.surveyappcommand.backend.domain.UserAggregate;
import org.marketlogic.surveyappcommand.backend.domain.UserBulkDeleteAggregate;
import org.marketlogic.surveyappcommand.backend.domain.UserEventSubscriber;
import org.marketlogic.surveyappcommand.backend.domain.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Configuration
@EntityScan("org.marketlogic.survey")
@EnableJpaRepositories("org.marketlogic.survey")
@EnableEventHandlers
public class SurveyBackendConfiguration {

  @Bean
  public UserEventSubscriber todoEventSubscriber() {
    return new UserEventSubscriber();
  }

  @Bean
  public AggregateRepository<UserAggregate, UserCommand> aggregateRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<>(UserAggregate.class, eventStore);
  }

  @Bean
  public AggregateRepository<UserBulkDeleteAggregate, UserCommand> bulkDeleteAggregateRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<>(UserBulkDeleteAggregate.class, eventStore);
  }

  @Bean
  public UserService updateService(AggregateRepository<UserAggregate, UserCommand> aggregateRepository, AggregateRepository<UserBulkDeleteAggregate, UserCommand> bulkDeleteAggregateRepository) {
    return new UserService(aggregateRepository, bulkDeleteAggregateRepository);
  }

  @Bean
  public UserViewServiceImpl commandService(UserRepository repository) {
    return new UserViewServiceImpl(repository);
  }


  @Bean
  public HttpMessageConverters customConverters() {
    HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
    return new HttpMessageConverters(additional);
  }
}


