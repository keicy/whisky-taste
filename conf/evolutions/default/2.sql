# reviews

# --- !Ups
CREATE TABLE reviews (
      PRIMARY KEY (review_id)
    , FOREIGN KEY (whisky_id) REFERENCES whiskies(whisky_id)
    , review_id  SERIAL NOT NULL
--    , user_id BIGINT NOT NULL
    , whisky_id INT NOT NULL
--    , title VARCHAR(50) NOT NULL
    , score SMALLINT DEFAULT 10 NOT NULL
            CHECK (0 <= score AND score <= 20)
    , comment VARCHAR(500)
    , posted_date DATE DEFAULT CURRENT_DATE NOT NULL
--    , created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
--    , updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO reviews (review_id, whisky_id, score, comment) VALUES (1, 1, 17, 'I love it! Aftertaste sustains long long long...');
INSERT INTO reviews (review_id, whisky_id, score, comment) VALUES (2, 2, 17, 'Smooth and rich! Chocolate, wood, spicy.');
SELECT setval('reviews_review_id_seq', (SELECT MAX(review_id) FROM reviews)); -- 上記でPKの値を指定して登録しているため,シーケンスオブジェクトの値を補正

# --- !Downs
DROP TABLE IF EXISTS reviews;
