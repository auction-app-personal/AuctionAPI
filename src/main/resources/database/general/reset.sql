DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS auction CASCADE;
DROP TABLE IF EXISTS lot CASCADE;
DROP TABLE IF EXISTS bid CASCADE;
DROP TABLE IF EXISTS account_auction CASCADE;

DROP TYPE IF EXISTS user_role CASCADE;
DROP TYPE IF EXISTS auction_status CASCADE;
DROP TYPE IF EXISTS lot_status CASCADE;

DROP CAST (VARCHAR AS INTERVAL);

CREATE TYPE user_role AS ENUM ('ADMIN', 'USER', 'GUEST');
CREATE TYPE auction_status AS ENUM ('CREATED', 'STARTED', 'PAUSED', 'ENDED');
CREATE TYPE lot_status AS ENUM ('CREATED', 'CLOSED', 'OPENED', 'SOLD');

CREATE CAST (VARCHAR AS user_role) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS auction_status) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS lot_status) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS INTERVAL) WITH INOUT AS IMPLICIT;



CREATE TABLE IF NOT EXISTS account
(
    id       SERIAL    NOT NULL,
    name     VARCHAR   NOT NULL,
    email    VARCHAR   NOT NULL,
    password VARCHAR   NOT NULL,
    role     user_role NOT NULL DEFAULT 'GUEST',
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS auction
(
    id              SERIAL         NOT NULL,
    name            VARCHAR        NOT NULL,
    description     VARCHAR,
    status          auction_status NOT NULL DEFAULT 'CREATED',
    start_timestamp TIMESTAMP,
    duration        INTERVAL,
    account_id      INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id)
        REFERENCES account (id)
);

CREATE TABLE lot
(
    id          SERIAL     NOT NULL,
    name        VARCHAR    NOT NULL,
    description VARCHAR,
    start_price MONEY               DEFAULT 0.00,
    status      lot_status NOT NULL DEFAULT 'CREATED',
    order_num   int        NOT NULL,
    auction_id  INT,
    PRIMARY KEY (id),
    FOREIGN KEY (auction_id)
        REFERENCES auction (id)
);

CREATE TABLE bid
(
    id           SERIAL         NOT NULL,
    amount       NUMERIC(10, 2) NOT NULL,
    time_created TIMESTAMP      NOT NULL,
    user_id      INT            NOT NULL,
    lot_id       INT            NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id)
        REFERENCES account (id),
    FOREIGN KEY (lot_id)
        REFERENCES lot (id)
);

CREATE TABLE account_auction
(
    account_id INT NOT NULL,
    auction_id INT NOT NULL,
    PRIMARY KEY (account_id, auction_id),
    FOREIGN KEY (account_id)
        REFERENCES account (id),
    FOREIGN KEY (auction_id)
        REFERENCES auction (id)
);

