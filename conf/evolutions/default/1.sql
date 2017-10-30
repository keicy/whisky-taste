# whiskies

# --- !Ups
CREATE TABLE whiskies (
      PRIMARY KEY (whisky_id)
    , whisky_id SERIAL NOT NULL
    , whisky_name VARCHAR(100) NOT NULL
    , distillery_name VARCHAR(30)
    , country VARCHAR(30)
    , region VARCHAR(30)
--    , type VARCHAR(50)
    , strength REAL
--    , created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
--    , updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO whiskies (whisky_id, whisky_name, distillery_name, country, region, strength) VALUES (1, 'Knockando 21 yo 1990', 'Knockando', 'Scotland', 'Speyside', 43.0);
INSERT INTO whiskies (whisky_id, whisky_name, distillery_name, country, region, strength) VALUES (2, 'GlenDronach 21 yo Parliament', 'The GlenDronach', 'Scotland', 'Speyside', 48.0);
INSERT INTO whiskies (whisky_id, whisky_name, distillery_name, country, region, strength) VALUES (3, 'Auchentoshan 12 yo', 'Auchentoshan', 'Scotland', 'Lowland', 40.0);
INSERT INTO whiskies (whisky_id, whisky_name, distillery_name, country, region, strength) VALUES (4, 'Johnnie Walker Red Label', '', 'Scotland', '', 40.0);
INSERT INTO whiskies (whisky_id, whisky_name, distillery_name, country, region, strength) VALUES (5, '山崎 12年', '山崎蒸溜所', '日本', '大阪', 43.0);
SELECT setval('whiskies_whisky_id_seq', (SELECT MAX(whisky_id) FROM whiskies)); -- 上記でPKの値を指定して登録しているため,シーケンスオブジェクトの値を補正

# --- !Downs
DROP TABLE IF EXISTS whiskies;
