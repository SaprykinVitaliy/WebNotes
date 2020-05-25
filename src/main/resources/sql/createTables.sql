CREATE TABLE note_metadata
(
    id               SERIAL PRIMARY KEY,
    creation_time    timestamp,
    last_change_time timestamp
);


CREATE TABLE note_versions
(
    id                       integer REFERENCES note_metadata (id),
    version                  SERIAL UNIQUE NOT NULL,
    time_of_version_creation timestamp,
    header                   varchar(100),
    text                     text,
    PRIMARY KEY (id, version)
);

INSERT INTO note_metadata (creation_time, last_change_time)
VALUES ('1999-01-08 04:05:06', '1999-01-08 04:05:30'),
       ('2020-05-25 11:53:06', '2020-05-25 11:53:06');

INSERT INTO note_versions (id, time_of_version_creation, header, text)
VALUES ('1', '1999-01-08 04:05:06', 'Тестовая заметка', 'Это тестовая заметка - версия 1'),
       ('1', '1999-01-08 04:05:30', 'Тестовая заметка', 'Это тестовая заметка - версия 2'),
       ('2', '2020-05-25 11:53:06', 'Другая тестовая заметка', 'Это тестовая заметка ещё не изменялась');

