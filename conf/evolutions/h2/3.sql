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

INSERT INTO Reviews (whisky_name, score, comment) VALUES ('Knockando 21 yo 1990', 16, 'I love it! Aftertaste sustains long long long...');
INSERT INTO Reviews (whisky_name, score, comment) VALUES ('GlenDronach 21 yo Parliament', 16, 'Smooth and rich! Chocolate, wood, spicy.');
INSERT INTO Reviews (whisky_name, score, comment) VALUES ('Auchentoshan 12 yo', 13, 'My favorite daily bottle! Pretty good aroma. Smooth, woodiness, syrup, marmalade.');

# --- !Downs
DROP TABLE Reviews;
