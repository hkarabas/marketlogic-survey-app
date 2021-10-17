package org.marketlogic.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

    @Id
    private  String id;
    @Column
    private  String surveyName;

    public Survey(SurveyDto surveyDto) {
        this.surveyName = surveyDto.getSurveyName();
    }

}