package org.marketlogic.surveyappcommand.backend;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.marketlogic.survey.*;
import org.marketlogic.surveyappcommand.backend.command.*;
import org.marketlogic.surveyappcommand.backend.domain.*;
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
    public UserEventSubscriber userEventSubscriber() {
        return new UserEventSubscriber();
    }

    @Bean
    public AggregateRepository<UserAggregate, UserCommand> aggregateUserRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(UserAggregate.class, eventStore);
    }

    @Bean
    public AggregateRepository<UserBulkDeleteAggregate, UserCommand> bulkDeleteAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(UserBulkDeleteAggregate.class, eventStore);
    }

    @Bean
    public UserService updateUserService(AggregateRepository<UserAggregate, UserCommand> aggregateRepository, AggregateRepository<UserBulkDeleteAggregate, UserCommand> bulkDeleteAggregateRepository) {
        return new UserService(aggregateRepository, bulkDeleteAggregateRepository);
    }

    @Bean
    public UserViewServiceImpl commandUserService(UserRepository repository) {
        return new UserViewServiceImpl(repository);
    }

    /*-------survey-------*/
    @Bean
    public SurveyEventSubscriber surveyEventSubscriber() {
        return new SurveyEventSubscriber();
    }

    @Bean
    public AggregateRepository<SurveyAggregate, SurveyCommand> aggregateSurveyRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(SurveyAggregate.class, eventStore);
    }

    @Bean
    public AggregateRepository<SurveyBulkDeleteAggregate, SurveyCommand> bulkSurveyDeleteAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(SurveyBulkDeleteAggregate.class, eventStore);
    }

    @Bean
    public SurveyService updateSurveyService(AggregateRepository<SurveyAggregate, SurveyCommand> aggregateRepository, AggregateRepository<SurveyBulkDeleteAggregate, SurveyCommand> bulkSurveyDeleteAggregateRepository) {
        return new SurveyService(aggregateRepository, bulkSurveyDeleteAggregateRepository);
    }

    @Bean
    public SurveyViewServiceImpl commandSurveyService(SurveyRepository repository) {
        return new SurveyViewServiceImpl(repository);
    }

    /*********UserSurveyAnswer************/
    @Bean
    public UserSurveyAnswerEventSubscriber userSurveyAnswerEventSubscriber() {
        return new UserSurveyAnswerEventSubscriber();
    }

    @Bean
    public AggregateRepository<UserSurveyAnswerAggregate, UserSurveyAnswerCommand> aggregateUserSurveyAnswerRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(UserSurveyAnswerAggregate.class, eventStore);
    }

    @Bean
    public AggregateRepository<UserSurveyAnswerBulkDeleteAggregate, UserSurveyAnswerCommand> bulkUserSurveyAnswerDeleteAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(UserSurveyAnswerBulkDeleteAggregate.class, eventStore);
    }

    @Bean
    public UserSurveyAnswerService updateUserSurveyAnswerService(AggregateRepository<UserSurveyAnswerAggregate, UserSurveyAnswerCommand> aggregateRepository,
                                                                 AggregateRepository<UserSurveyAnswerBulkDeleteAggregate, UserSurveyAnswerCommand> bulkUserSurveyAnswerDeleteAggregateRepository) {
        return new UserSurveyAnswerService(aggregateRepository, bulkUserSurveyAnswerDeleteAggregateRepository);
    }

    @Bean
    public UserSurveyAnswerServiceImpl commandUserSurveyAnswerService(UserSurveyAnswerRepository repository) {
        return new UserSurveyAnswerServiceImpl(repository);
    }

    /***********Question**********/

    @Bean
    public QuestionEventSubscriber questionEventSubscriber() {
        return new QuestionEventSubscriber();
    }

    @Bean
    public AggregateRepository<QuestionAggregate, QuestionCommand> aggregateQuestionRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(QuestionAggregate.class, eventStore);
    }

    @Bean
    public AggregateRepository<QuestionBulkDeleteAggregate, QuestionCommand> bulkQuestionDeleteAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(QuestionBulkDeleteAggregate.class, eventStore);
    }

    @Bean
    public QuestionService updateQuestionService(AggregateRepository<QuestionAggregate, QuestionCommand> aggregateRepository,
                                                 AggregateRepository<QuestionBulkDeleteAggregate, QuestionCommand> bulkQuestionDeleteAggregateRepository) {
        return new QuestionService(aggregateRepository, bulkQuestionDeleteAggregateRepository);
    }

    @Bean
    public QuestionViewServiceImpl commandQuestionService(QuestionRepository repository) {
        return new QuestionViewServiceImpl(repository);
    }

    /*************Answer****************************/
    @Bean
    public AnswerEventSubscriber EventSubscriber() {
        return new AnswerEventSubscriber();
    }

    @Bean
    public AggregateRepository<AnswerAggregate, AnswerCommand> aggregateAnswerRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(AnswerAggregate.class, eventStore);
    }

    @Bean
    public AggregateRepository<AnswerBulkDeleteAggregate, AnswerCommand> bulkAnswerDeleteAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(AnswerBulkDeleteAggregate.class, eventStore);
    }

    @Bean
    public AnswerService updateAnswerService(AggregateRepository<AnswerAggregate, AnswerCommand> aggregateRepository,
                                             AggregateRepository<AnswerBulkDeleteAggregate, AnswerCommand> bulkAnswerDeleteAggregateRepository) {
        return new AnswerService(aggregateRepository, bulkAnswerDeleteAggregateRepository);
    }

    @Bean
    public AnswerViewServiceImpl commandAnswerService(AnswerRepository repository) {
        return new AnswerViewServiceImpl(repository);
    }


    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }
}


