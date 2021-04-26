create table beneficiary_story (
    id  integer primary key, 
    b_name varchar(255), 
    story varchar(255), 
    report_id integer not null
);

create table installed_component (
    id  integer primary key, 
    component varchar(255), 
    contribution double precision, 
    cost double precision, 
    installer varchar(255), 
    location varchar(255), 
    size varchar(255), 
    report_id integer not null
);

create table lesson_learned (
    id  integer primary key,
    lesson varchar(255), 
    report_id integer not null
);

create table media (
    id  integer primary key, 
    media varchar(255), 
    report_id integer not null
);

create table question (
    id integer primary key,
    prompt varchar(255),
    subtitle varchar(255),
    type varchar(255),
    aggregate varchar(255)
);

create table report (
    id  integer primary key, 
    name varchar(255),
    country varchar(255), 
    region varchar(255),
    gps varchar(255),
    install_start date,
    install_end date,
    date_created date
);

create table response (
    id integer primary key,
    float_input double precision,
    input text,
    number_input integer,
    question_id integer,
    report_id integer not null
);

create table template (
    id integer primary key
);

create table template_question (
    template_id integer not null,
    question_id integer not null
);

create table trained_technician (
    id  integer primary key, 
    technician varchar(255), 
    report_id integer not null
);