package org.marketlogic.surveyappquery.controller;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import({SurveyQueryWebConfiguration.class})
@ComponentScan({"org.marketlogic.survey.common",
        "org.marketlogic.survey.hateoas",
        "org.marketlogic.surveyappquery.controller"})
public class SurveyQueryWebConfiguration implements WebMvcConfigurer {
}
