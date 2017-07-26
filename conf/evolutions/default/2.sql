# Reviews

# --- !Ups
CREATE TABLE Reviews (
      review_id IDENTITY(1)
    , user_id BIGINT NOT NULL
    , whisky_id BIGINT NOT NULL
    , posted_date DATE DEFAULT CURRENT_DATE() NOT NULL
    , title VARCHAR(50) NOT NULL
    , score TINYINT UNSIGNED DEFAULT 10 NOT NULL
            CHECK score BETWEEN 0 AND 20
    , comment VARCHAR(1000)
    , PRIMARY KEY (review_id)
);

# --- !Downs
DROP TABLE Reviews;
