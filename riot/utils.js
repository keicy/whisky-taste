import route from 'riot-route'

const URL_ROOT = '#' /* RouterのURLルート */
const URL_SEPARETION = '_' /* URLの区切り文字 */

/* 相対URL取得 */
const getURL = (url = location.href) => url.split(URL_ROOT)[1]

/* 画面遷移実行 */
const redirect = (url = '') => route('/' + url)

/* whisky からレビュー一覧ページのURLを取得 */
const getReviewListURL = whisky =>
  '/' + whisky.whiskyId + URL_SEPARETION + whisky.whiskyName.replace(/ /g, URL_SEPARETION)

/* 'whiskyId_name' のURL文字列からwhiskyIdを取得 */
const getWhiskyId = whiskyIdWithName => parseInt(whiskyIdWithName.split(URL_SEPARETION)[0])

export {
  getURL,
  redirect,
  getReviewListURL,
  getWhiskyId,
}
