
DROP TABLE IF EXISTS BAY;

CREATE TABLE IF NOT EXISTS BAY
(
  UUID                                   UUID         PRIMARY KEY,
  DISPLAY_NAME                           TEXT         NOT NULL,
  ACTIVE                                 BOOLEAN      NOT NULL,
  CREATE_DATE_TIME                       TIMESTAMP    NOT NULL,
  CONSTRAINT bay_uuid_idempotent UNIQUE (UUID)
);