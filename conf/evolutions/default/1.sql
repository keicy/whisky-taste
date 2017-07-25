# Usersテーブルを生成

# --- !Ups
CREATE TABLE users(id serial, name text);

# --- !Downs
DROP TABLE users;
