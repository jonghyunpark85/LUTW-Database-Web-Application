-- Quick stats for external communications

insert into question (prompt, type, aggregate) values (
    'Number of systems installed',
    'NUMBER',
    'systems installed'
); 

insert into question (prompt, type, subtitle, aggregate) values (
    'Number of beneficiaries',
    'NUMBER',
    'Estimations are fine',
    'beneficiaries'
);  

insert into question (prompt, type, aggregate) values (
    'Number of people trained',
    'NUMBER',
    'people trained'
);

-- Information to be shared externally

insert into question (prompt, subtitle, type) values (
    'Project highlights',
    '80-100 words. Some ideas are why the community wanted the project, why it''s important to them, about their livelihood, how this will help, etc', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Education impact',
    '30-45 words on anecdodal examples of education impact of the project, such as number of children that now have light, stories about adult education, story of impact on a specific student (with name)', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Economic development impact',
    '30-45 words on anecdotal examples of the project''s impact on economic development, such as a story of how much people will save, local bussiness activities that exist with solar systems, etc.', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Environmental and Well-Being impact',
    '30-45 words on anecdotal examples of environmental and well-being impacts of the project, such as how many fiewer batteries will be disposed of per year given baseline info, whether there have been fires in homes that are lower risk, whether elderly people can now move around safely at night, whether light is used to scare off foxed etc.', 
    'TEXT'
);

-- LUTW INTERNAL INFORMATION

insert into question (prompt, subtitle, type) values (
    'Logistics',
    'Any unusual major challenges/barriers', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'System design',
    'Was it appropriate? Please insert a picture of unique feature or equipment problem encountered (unique feature can mean a new installation method/improvement or requuest from homeowner about where lights were going to go - such as exterior lighting, lights for storage space or animals, net configurations, etc.).', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'System uptake/project participation',
    'Was it higher or lower than expected? Were there significant changes from other projects? If so, why?', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Training',
    'Please insert a picture of training. Include names of people trained and something interesting about them if possible, i.e. livelihood, why they were interested in learning solar PV skills).', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Highlights from installations',
    'Chalenges, problems, successes, points of failure', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Monitoring and evaluation plans',
    'When do we expect the 1st, 2nd, 3rd to take place? Were data loggers installed and if so where?', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Other project feedback',
    'From community and partner', 
    'TEXT'
);

-- Administration

insert into question (prompt, subtitle, type) values (
    'Working with the local partner',
    'Any information to note about relationship and/or organizational status', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Finance',
    'Significant budget variations, cash disbursement', 
    'TEXT'
);

insert into question (prompt, type) values (
    'Manuals, guides, houeshold data',
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Contributions',
    'How much was paid/did all families pay', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Human resources',
    'Any changes to what was anticipated, who participated in field activities from local partner', 
    'TEXT'
);

-- Project Management

insert into question (prompt, subtitle, type) values (
    'Documentation',
    'Any changes to project documentation', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Process/project steps',
    'Any variance from normal project process', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Communication',
    'Any issues or concerns to do with communicating with the community/partners', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Security',
    'Any abnormal security issues that arose during the project', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Culture/language',
    'Any issues relevant to follow up with the community', 
    'TEXT'
);

insert into question (prompt, subtitle, type) values (
    'Community structure/governance',
    'Any issues to do with governance in community, who are key leaders in community', 
    'TEXT'
);

insert into template (id) values (1);

insert into template_question (template_id, question_id)
    select 1, id from question;
