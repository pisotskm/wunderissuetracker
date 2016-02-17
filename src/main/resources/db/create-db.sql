CREATE TABLE Developer (
  id    INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  name  VARCHAR(255)
);

CREATE TABLE issue (
    id    INTEGER IDENTITY PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    creation_date DATE,
    developer_id INTEGER,
    discriminator VARCHAR(255),
    point INTEGER,
    story_status VARCHAR(255),
    priority VARCHAR(255),
    bug_status VARCHAR(255)
);
