CREATE SEQUENCE client_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE client
(
    id           BIGINT       NOT NULL DEFAULT NEXTVAL('client_seq') PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    branch       VARCHAR(255),
    address      VARCHAR(255)
);
alter sequence client_seq owned by client.id;

CREATE SEQUENCE contact_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE contact
(
    id         BIGINT              NOT NULL DEFAULT NEXTVAL('contact_seq') PRIMARY KEY,
    first_name VARCHAR(255)        NOT NULL,
    last_name  VARCHAR(255)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    phone      VARCHAR(50),
    password   VARCHAR(255)        NOT NULL,
    client_id  BIGINT,
    CONSTRAINT fk_contact_client FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
);
alter sequence contact_seq owned by contact.id;

CREATE SEQUENCE task_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE task
(
    id          BIGINT       NOT NULL DEFAULT NEXTVAL('task_seq') PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    status      VARCHAR(50)  NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    end_at      TIMESTAMP,
    contact_id  BIGINT,
    CONSTRAINT fk_task_contact FOREIGN KEY (contact_id) REFERENCES contact (id) ON DELETE CASCADE
);
alter sequence task_seq owned by task.id;

INSERT INTO contact (first_name, last_name, email, phone, password, client_id)
VALUES
    ('Admin', 'User', 'admin@example.com', '000-000-0000', '1234', null);
