USE
surveyapp;

DROP table IF EXISTS user;
DROP table IF EXISTS survey;
DROP table IF EXISTS usersurveyanswer;
DROP table IF EXISTS question;
DROP table IF EXISTS answer;

create table user
(
    id       varchar(255) PRIMARY KEY,
    username varchar(255),
    deleted  BOOLEAN
);

create table survey
(
    id         varchar(255) PRIMARY KEY,
    surveyname varchar(255),
    deleted    BOOLEAN
);

create table user_survey_answer
(
    id        varchar(255) PRIMARY KEY,
    survey_id varchar(255),
    answer_id varchar(255),
    user_id   varchar(255),
    deleted   BOOLEAN
);


create table question
(
    id             varchar(255) PRIMARY KEY,
    survey_id      varchar(255),
    question_asked varchar(255),
    deleted        BOOLEAN
);
create table answer
(
    id          varchar(255) PRIMARY KEY,
    question_id varchar(255),
    answer_desc varchar(255)
)


