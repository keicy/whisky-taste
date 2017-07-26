# Whiskies

# --- !Ups
CREATE TABLE Whiskies (
      whisky_id IDENTITY(1)
    , whisky_name VARCHAR(100) NOT NULL
    , distillery_name VARCHAR(100)
    , country VARCHAR(50)
    , region VARCHAR(50)
    , type VARCHAR(50)
    , strength DOUBLE
    , posted_date DATE DEFAULT CURRENT_DATE() NOT NULL
    , PRIMARY KEY (whisky_id)
);

# --- !Downs
DROP TABLE Whiskies;
