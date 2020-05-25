CREATE TABLE noteMetadata
(
    id               SERIAL PRIMARY KEY,
    creation_time    timestamp,
    last_change_time timestamp
);


CREATE TABLE note_versioned
(
    id            integer REFERENCES noteMetadata (id),
    version       SERIAL UNIQUE NOT NULL,
    creation_time timestamp,
    header        varchar(100),
    text          text,
    PRIMARY KEY (id, version)
);
