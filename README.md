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

TODO ポスグレ

- Java8
- Node
- Google Churome (recommended)
- PostgresQL ???

## Usage / 動かし方

```
git clone git@github.com:keicy/whisky-taste.git
cd whisky-taste
npm init
npm run build
sbt run
```

Then, Access `http://localhost:9000` in your browser (Google-Chrome is recommend).  
  
ブラウザ(Google-Chrome 推奨)から `http://localhost:9000` にアクセスしてください.  











=========


## 参考情報

### Slick

- http://krrrr38.github.io/slick-doc-ja/v3.0.out/ORM%E3%81%8B%E3%82%89Slick%E3%82%92%E5%88%A9%E7%94%A8%E3%81%99%E3%82%8B%E4%BA%BA%E3%81%B8.html


## 開発コマンド

### H2

| 操作 | コマンド | 補足 |
|:---|:---|:---|
| コンソール | `sbt` > 'h2-browser' | SBTコンソール内から実行しないと正常起動できない（`sbt h2-browser` ではダメ） |
|  |  |  |

### Postgres GUI

```
cd ~/.pgadmin4 && source bin/activate # virtual environment にログイン
python lib/python2.7/site-packages/pgadmin4/pgAdmin4.py # 起動
```


### Play

| 操作 | コマンド | 補足 |
|:---|:---|:---|
| ウォッチ | `sbt ~compile` |  |
| 実行 | `sbt run` | ウォッチコマンドを別ターミナルにて実行しておくと良い |
|  |  |  |
|  |  |  |

### JS

| 操作 | コマンド | 補足 |
|:---|:---|:---|
| ウォッチ | `npm run w` |  |
|  |  |  |
|  |  |  |
|  |  |  |
|  |  |  |
