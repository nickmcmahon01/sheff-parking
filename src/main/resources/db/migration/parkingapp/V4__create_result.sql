
DROP TABLE IF EXISTS RESULT;

CREATE TABLE IF NOT EXISTS RESULT
(
  ID                                     SERIAL       PRIMARY KEY,
  UUID                                   UUID         NOT NULL,
  USER_ID                                UUID         NOT NULL,
  DAYOFYEAR                              INTEGER      NOT NULL,
  INDEX                                  INTEGER      NOT NULL,
  CREATE_DATE_TIME                       TIMESTAMP    NOT NULL,
  CONSTRAINT draw_uuid_idempotent UNIQUE (UUID),
  FOREIGN KEY (USER_ID) REFERENCES PARKINGUSER (UUID)
);

CREATE INDEX draw_dayofyear
    ON RESULT (DAYOFYEAR);