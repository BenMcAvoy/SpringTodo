CREATE TABLE todos (
    id BIGINT PRIMARY KEY DEFAULT unique_rowid(),
    title VARCHAR(200) NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT false
);
