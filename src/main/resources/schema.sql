DROP TABLE IF EXISTS account_op;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS client;

CREATE TABLE client (
    id INT NOT NULL,
    address VARCHAR(255),
    birth_date DATE,
    full_name VARCHAR(128) NOT NULL,
    phone VARCHAR(64),
    email VARCHAR(128),
    PRIMARY KEY (id)
);


CREATE TABLE account (
    id INT AUTO_INCREMENT,
    balance DECIMAL(20,2) NOT NULL,
    creation_date DATE NOT NULL,
    client_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE account
    ADD CONSTRAINT FK_client_account
    FOREIGN KEY (client_id)
    REFERENCES client (id);

CREATE TABLE account_op (
    id INT AUTO_INCREMENT,
    amount DECIMAL(20,2) NOT NULL,
    operation_type VARCHAR(64),
    operation_date DATE NOT NULL,
    account_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE account_op
     ADD CONSTRAINT FK_account_accountop
     FOREIGN KEY (account_id)
     REFERENCES account (id);