# Users

# --- !Ups
CREATE TABLE Users (
      user_id IDENTITY(1)
    , user_name VARCHAR(50) NOT NULL
    , password VARCHAR(100) NOT NULL
    , email VARCHAR(200) UNIQUE NOT NULL
    , registered_date DATE DEFAULT CURRENT_DATE() NOT NULL
    , is_quitted BOOLEAN DEFAULT FALSE NOT NULL
    , PRIMARY KEY (user_id)
);

# --- !Downs
DROP TABLE Users;
