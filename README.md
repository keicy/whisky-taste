Whisky Taste
============

ウイスキーのレビューを投稿できるウェブサイトを動かすためのWEBアプリケーションです.  
  
A WEB application sample for posting tasting reviews of whiskies.  

## 概要 / Description

データベースは[PostgreSQL](https://www.postgresql.org/),バックエンド(サーバーサイド)は[Play(Scala)](https://www.playframework.com/documentation/2.5.x/ScalaHome),フロントエンドは[Riot.js](http://riotjs.com/)と[Bulma](https://bulma.io/)で構築しています.

- PlayとSlickの特徴を活かしてAPIの処理を非同期に行うことでサーバーリソースを有効活用するようにしました.
- Riot.jsを使用してコンポーネントベースのUI開発を行いました.
- フロントエンドのデータフロー制御にFluxを採用することでアプリケーションのグローバルな状態をStoreに集約し各コンポーネントから排除,コンポーネント間の疎結合を担保しました.
- レスポンシブデザインによりスマートフォンブラウザでの表示に対応しています.
  
Using [PostgreSQL](https://www.postgresql.org/) at DB,[Play(Scala)](https://www.playframework.com/documentation/2.5.x/ScalaHome) at back-end, [Riot.js](http://riotjs.com/) and [Bulma](https://bulma.io/) at front-end.

- Using Play and Slick, we can make our API functions asynchronous and then save our server resources.
- Using Riot.js, we can do component-based UI development.
- Adopt Flux to control data flow, we can deal with all global states at Store, remove them from each components. So we are able to keep our components as low coupling.
- It is compatible with viewing on your smartphone by responsive CSS.

## デモ / Demo

[https://whisky-taste.herokuapp.com](https://whisky-taste.herokuapp.com)  
  
**※ サイト内の画面から自由にレビュー投稿していただいて構いません!**  
  
**Note: In the site you are free to post new reviews!**

## 使用技術 / Technical elements

| ツール | バージョン | 補足説明 |
|:---|:---|:---|
| PostgreSQL | 9.5.9 | データベース |
| Play | 2.5.10 | バックエンドのScala開発向けアプリケーションフレームワーク |
| Slick | 3.2.0 | Scala開発向けDBアクセスライブラリ |
| Play-slick | 2.1.0 | PlayからSlickを扱うためのライブラリ |
| SBT | 0.13.11 | バックエンドコード(Play,Scala)のためのビルドツール |
| Scala | 2.11.7 | - |
| Riot.js | 3.7.3 | フロントエンドのUIフレームワーク |
| Riot-Route | 3.1.2 | フロントエンドのルーティングライブラリ |
| Rollup | 0.45.2 | フロントエンドコード(Javascript)のためのビルドツール |
| Buble | 0.15.2 | ES2015開発向けトランスパイラ |
| Javascript | ES2015 | - |
| Bulma | 0.6.0 | CSSフレームワーク |
| CSS | 3 | - |
| HTML | 5 | - |

## 動作環境 / Requirement

- Java 8
- Node v6.11
- PostgreSQL 9.5
- Google Churome (recommended)

## 動かし方 / Usage

### 準備 / Preparation

1. PostgreSQLにてデータベース `whisky_taste` を新規作成
2. お手元のマシンに環境変数を設定
   - `DEV_DB_USER` : あなたのPostgreSQLユーザー (作成した `whisky_taste` データベースへのアクセス権があることを確認してください)
   - `DEV_DB_PSWD` : あなたのPostgreSQLユーザーのパスワード
3. コンソールにて環境変数を再読み込み

 <!-- break list -->

1. Create new Database named `whisky_taste` at PostgreSQL
2. Set your environment variable on your PC
   - `DEV_DB_USER` : Set your PostgreSQL user (Make sure that the user can access created `whisky_taste` databese)
   - `DEV_DB_PSWD` : Set your user's password
3. Reload environment variable on your console

### 実行 / Run

```
git clone git@github.com:keicy/whisky-taste.git
cd whisky-taste
npm init
npm run build
sbt run
```

### サイトアクセス / Access

WEBブラウザ(Google-Chrome 推奨)から `http://localhost:9000` にアクセスしてください.  
  
Access `http://localhost:9000` in WEB browser (Google-Chrome is recommended).
