
DROP TABLE IF EXISTS REQUEST;

CREATE TABLE IF NOT EXISTS REQUEST
(
  UUID                                   UUID         PRIMARY KEY,
  USER_ID                                UUID         NOT NULL,
  DAYOFYEAR                              INTEGER      NOT NULL,
  PRIORITY                               INTEGER      NOT NULL,
  ACTIVE                                 BOOLEAN      NOT NULL,
  CREATE_DATE_TIME                       TIMESTAMP    NOT NULL,
  CONSTRAINT request_uuid_idempotent UNIQUE (UUID),
  FOREIGN KEY (USER_ID) REFERENCES PARKINGUSER (UUID)
);

CREATE INDEX request_dayofyear
    ON REQUEST (DAYOFYEAR, ACTIVE);