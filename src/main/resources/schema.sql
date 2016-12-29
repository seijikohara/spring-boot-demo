DROP TABLE IF EXISTS todo CASCADE;
CREATE TABLE todo (
  id       SERIAL,
  title    TEXT    NOT NULL,
  details  TEXT,
  finished BOOLEAN NOT NULL
);