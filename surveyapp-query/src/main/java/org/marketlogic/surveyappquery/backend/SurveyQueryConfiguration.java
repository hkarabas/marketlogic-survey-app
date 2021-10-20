package org.marketlogic.surveyappquery.backend;


import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.marketlogic.survey.*;
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
public class SurveyQueryConfiguration {

    @Bean
    public UserQueryEventSubscriber userQueryEventSubscriber(UserUpdateServiceImpl userUpdateService) {
        return new UserQueryEventSubscriber(userUpdateService);
    }

    @Bean
    public UserUpdateServiceImpl userUpdateService(UserRepository repository) {
        return new UserUpdateServiceImpl(repository);
    }

    /*****Survey****/
    @Bean
    public SurveyQueryEventSubscriber surveyQueryEventSubscriber(SurveyUpdateServiceImpl surveyUpdateService) {
        return new SurveyQueryEventSubscriber(surveyUpdateService);
    }

    @Bean
    public SurveyUpdateServiceImpl surveyUpdateService(SurveyRepository repository) {
        return new SurveyUpdateServiceImpl(repository);
    }

    /**********UserSurveyAnswer*****/
    @Bean
    public UserSurveyAnswerQueryEventSubscriber userSurveyAnswerQueryEventSubscriber(UserSurveyAnswerUpdateServiceImpl userSurveyAnswerService) {
        return new UserSurveyAnswerQueryEventSubscriber(userSurveyAnswerService);
    }

    @Bean
    public UserSurveyAnswerUpdateServiceImpl userSurveyAnswerUpdateService(UserSurveyAnswerRepository repository) {
        return new UserSurveyAnswerUpdateServiceImpl(repository);
    }

    /*********************Question**************************/
    @Bean
    public QuestionQueryEventSubscriber questionQueryEventSubscriber(QuestionUpdateServiceImpl userSurveyAnswerService) {
        return new QuestionQueryEventSubscriber(userSurveyAnswerService);
    }

    @Bean
    public QuestionUpdateServiceImpl questiomUpdateService(QuestionRepository repository) {
        return new QuestionUpdateServiceImpl(repository);
    }

    /**********Answer******/
    @Bean
    public AnswerQueryEventSubscriber answerQueryEventSubscriberr(AnswerUpdateServiceImpl answerUpdateService) {
        return new AnswerQueryEventSubscriber(answerUpdateService);
    }

    @Bean
    public AnswerUpdateServiceImpl answerUpdateService(AnswerRepository repository) {
        return new AnswerUpdateServiceImpl(repository);
    }


    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }

}
