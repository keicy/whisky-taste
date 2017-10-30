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

INSERT INTO reviews (review_id, whisky_id, score, comment) VALUES (1, 1, 18, '麦の甘み、草の匂い、樹木の薫りが渾然と広がる。奥からはシェリーが漂う。そして訪れる素晴らしい余韻。長く長く、いつまでも続く。');
INSERT INTO reviews (review_id, whisky_id, score, comment) VALUES (2, 2, 17, 'Smooth and rich! Chocolate, wood, spice.');
INSERT INTO reviews (review_id, whisky_id, score, comment) VALUES (3, 2, 18, '甘美でほろ苦い、シェリーの魅力に溢れる恍惚の一本。');
INSERT INTO reviews (review_id, whisky_id, score, comment) VALUES (4, 3, 14, 'My favorite daily bottle! Pretty good aroma. Smooth, woodiness, syrup, marmalade, vanilla.');
INSERT INTO reviews (review_id, whisky_id, score, comment) VALUES (5, 3, 15, '森林に抱かれる心地の一本。舌に残る後味が白眉。長熟ボトルはさらに素晴らしい。');
INSERT INTO reviews (review_id, whisky_id, score, comment) VALUES (6, 4, 12, 'シェリーの風味にオークのバニラ、樽香が溶け合う心地よい一本。少々値が張る。');
SELECT setval('reviews_review_id_seq', (SELECT MAX(review_id) FROM reviews)); -- 上記でPKの値を指定して登録しているため,シーケンスオブジェクトの値を補正

# --- !Downs
DROP TABLE IF EXISTS reviews;
