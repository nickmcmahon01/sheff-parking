
DROP TABLE IF EXISTS PARKINGUSER;

CREATE TABLE IF NOT EXISTS PARKINGUSER
(
  UUID                                   UUID         PRIMARY KEY,
  DISPLAY_NAME                           TEXT         NOT NULL,
  EMAIL                                  TEXT         NULL,
  USER_TYPE                              TEXT         NOT NULL,
  REGISTRATION                           TEXT         NOT NULL,
  HAS_ACCESS_NEEDS                       BOOLEAN      NOT NULL,
  PRIORITY                               BOOLEAN      NOT NULL,
  ACTIVE                                 BOOLEAN      NOT NULL,
  CREATE_DATE_TIME                       TIMESTAMP    NOT NULL,
  CONSTRAINT parkinguser_uuid_idempotent UNIQUE (UUID)
);