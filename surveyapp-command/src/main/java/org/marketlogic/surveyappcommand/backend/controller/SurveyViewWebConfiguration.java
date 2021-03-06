package org.marketlogic.surveyappcommand.backend.controller;

import org.marketlogic.surveyappcommand.backend.SurveyCommandBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@Import({SurveyCommandBackendConfiguration.class})
@ComponentScan({"org.marketlogic.survey.common",
        "org.marketlogic.survey.hateoas",
        "org.marketlogic.surveyappcommand.backend.controller"})
public class SurveyViewWebConfiguration implements WebMvcConfigurer {
}
