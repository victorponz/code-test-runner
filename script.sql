create table programs
(
    id               integer not null
        constraint programs_pk
            primary key autoincrement,
    class_name       text    not null,
    source_code      text    not null,
    source_code_test text    not null
);

create table jobs
(
    id          integer not null
        constraint jobs_pk
            primary key autoincrement,
    source_code text    not null,
    status      integer not null,
    result_code integer,
    error       text,
    id_program  integer not null
        constraint jobs_programs_id_fk
            references programs
);


