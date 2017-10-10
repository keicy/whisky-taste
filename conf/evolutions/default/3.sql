# Reviews

# --- !Ups
CREATE TABLE Reviews (
      PRIMARY KEY (review_id)
    , review_id IDENTITY(1)
--    , user_id BIGINT NOT NULL
--    , whisky_id BIGINT NOT NULL
--    , title VARCHAR(50) NOT NULL
    , whisky_name VARCHAR(50) NOT NULL
    , score TINYINT UNSIGNED DEFAULT 10 NOT NULL
            CHECK score BETWEEN 0 AND 20
    , comment VARCHAR(1000)
    , posted_date DATE DEFAULT CURRENT_DATE() NOT NULL
);

# --- !Downs
DROP TABLE Reviews;
