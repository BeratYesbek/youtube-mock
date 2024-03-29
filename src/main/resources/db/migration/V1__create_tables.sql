CREATE TABLE users
(
    ID         INT       NOT NULL PRIMARY KEY,
    VERSION    FLOAT       NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    DELETED    BOOLEAN   NOT NULL DEFAULT FALSE,

    FIRSTNAME VARCHAR(255) NOT NULL,
    LASTNAME  VARCHAR(255) NOT NULL,
    EMAIL     VARCHAR(255) NOT NULL,
    PASSWORD  VARCHAR(255) NOT NULL,
    PHONE     VARCHAR(255) NOT NULL
);
