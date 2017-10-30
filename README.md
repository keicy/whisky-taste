Whisky Taste
============

A WEB application sample for posting tasting reviews of whiskies.  
  
ウイスキーのレビューを投稿できるウェブサイトを動かすためのWEBアプリケーションです.  

## Description / 概要

Using [Play(Scala)](https://www.playframework.com/documentation/2.5.x/ScalaHome) at back-end, [Riot.js](http://riotjs.com/) and [Bulma](https://bulma.io/) at front-end.  
  
バックエンド(バックエンド)は[Play(Scala)](https://www.playframework.com/documentation/2.5.x/ScalaHome),フロントエンドは[Riot.js](http://riotjs.com/)と[Bulma](https://bulma.io/)で構築しています.  

### Back-end / バックエンド

Using Play and Slick, we can make our API functions asynchronous and then save our server resources.  
  
PlayとSlickの特徴を活かしてAPIの処理を非同期に行うことでサーバーリソースを有効活用できるようにしました.  

### Front-end / フロントエンド

Using Riot.js, we can do component-based UI development. Adopt Flux to control data flow, we can deal with all global states at Store, remove them from each components. So we are able to keep our components as low coupling.  
  
Riot.jsを使用してコンポーネントベースの開発を行いました.データフローにFluxを採用することでアプリケーションのグローバルな状態をStoreに集約し各コンポーネントから排除,コンポーネント間の疎結合を担保しました.  

## Demo / デモ

[https://whisky-taste.herokuapp.com](https://whisky-taste.herokuapp.com)  
  
In the demo, this application is running in Heroku, using [PostgreSQL](https://www.postgresql.org/).  
  
デモ環境(Heroku)ではDBに[PostgreSQL](https://www.postgresql.org/)を使用しています.  

## Technical elements / 使用技術

| ツール | バージョン | 補足説明 |
|:---|:---|:---|
| Play | 2.5.10 | バックエンドのScala開発向けアプリケーションフレームワーク |
| Slick | 3.2.0 | Scala開発向けDBアクセスライブラリ |
| Play-slick | 2.1.0 | PlayからSlickを扱うためのライブラリ |
| SBT | 0.13.11 | バックエンドコード(Play,Scala)のビルドツール |
| Scala | 2.11.7 | - |
| Riot.js | 3.7.3 | フロントエンドのUIフレームワーク |
| Riot-Route | 3.1.2 | フロントエンドのルーティングライブラリ |
| Rollup | 0.45.2 | フロントエンドコード(Javascript)のビルドツール |
| Buble | 0.15.2 | ES2015開発向けトランスパイラ |
| Javascript | ES2015 | - |
| Bulma | 0.6.0 | CSSフレームワーク |
| CSS | 3 | - |
| HTML | 5 | - |

## Requirement / 動作環境

- Java8
- Node v6.11
- PostgreSQL 9.5
- Google Churome (recommended)

## Usage / 動かし方

### Preparation / 準備

1. Create new Database named 'whisky_taste' at PostgreSQL
2. Set your environment variable on your PC
   - `DEV_DB_USER` : Set your PostgreSQL user (Make sure that the user can access created 'whisky_taste' databese!)
   - `DEV_DB_PSWD` : Set your user's password
3. Reload environment variable on your console

 <!-- break list -->

1. PostgreSQLにてデータベース 'whisky_taste' を新規作成
2. お手元のマシンに環境変数を設定
   - `DEV_DB_USER` : あなたのPostgreSQLユーザー (作成した 'whisky_taste' データベースへのアクセス権があることを確認してください!)
   - `DEV_DB_PSWD` : あなたのPostgreSQLユーザーのパスワード
3. コンソールにて環境変数を再読み込み

### Run / 実行

```
git clone git@github.com:keicy/whisky-taste.git
cd whisky-taste
npm init
npm run build
sbt run
```

### Access / サイトアクセス

Then, Access `http://localhost:9000` in your browser (Google-Chrome is recommend).  
  
ブラウザ(Google-Chrome 推奨)から `http://localhost:9000` にアクセスしてください.  
