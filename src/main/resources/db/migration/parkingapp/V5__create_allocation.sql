
DROP TABLE IF EXISTS ALLOCATION;

CREATE TABLE IF NOT EXISTS ALLOCATION
(
  ID                                     SERIAL       PRIMARY KEY,
  UUID                                   UUID         NOT NULL,
  BAY_ID                                 UUID         NOT NULL,
  RESULT_ID                              INTEGER      NOT NULL,
  DAYOFYEAR                              INTEGER      NOT NULL,
  CREATE_DATE_TIME                                TIMESTAMP    NOT NULL,
  CONSTRAINT allocation_uuid_idempotent UNIQUE (UUID),
  FOREIGN KEY (BAY_ID) REFERENCES BAY (UUID),
  FOREIGN KEY (RESULT_ID) REFERENCES RESULT (UUID)
);

CREATE INDEX alocation_dayofyear
    ON ALLOCATION (DAYOFYEAR);