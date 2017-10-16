# reviews

# --- !Ups
CREATE TABLE reviews (
      PRIMARY KEY (review_id)
    , review_id  SERIAL NOT NULL
--    , user_id BIGINT NOT NULL
--    , whisky_id BIGINT NOT NULL
--    , title VARCHAR(50) NOT NULL
    , whisky_name VARCHAR(100) NOT NULL
    , score SMALLINT DEFAULT 10 NOT NULL
            CHECK (0 <= score AND score <= 20)
    , comment VARCHAR(200)
    , posted_date DATE DEFAULT CURRENT_DATE NOT NULL
--    , created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
--    , updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO reviews (whisky_name, score, comment) VALUES ('Knockando 21 yo 1990', 16, 'I love it! Aftertaste sustains long long long...');
INSERT INTO reviews (whisky_name, score, comment) VALUES ('GlenDronach 21 yo Parliament', 16, 'Smooth and rich! Chocolate, wood, spicy.');
INSERT INTO reviews (whisky_name, score, comment) VALUES ('Auchentoshan 12 yo', 13, 'My favorite daily bottle! Pretty good aroma. Smooth, woodiness, syrup, marmalade.');

# --- !Downs
DROP TABLE reviews;
