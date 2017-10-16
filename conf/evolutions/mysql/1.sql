# Users

-- for MySQL

# --- !Ups
CREATE TABLE users (
      PRIMARY KEY (user_id)
    , user_id BIGINT AUTO_INCREMENT NOT NULL
    , email VARCHAR(200) UNIQUE NOT NULL
    , password VARCHAR(100) NOT NULL
    , user_name VARCHAR(50) NOT NULL
    , registered_date DATE DEFAULT CURRENT_DATE() NOT NULL
--    , is_quitted BOOLEAN DEFAULT FALSE NOT NULL
);

# --- !Downs
DROP TABLE users;
