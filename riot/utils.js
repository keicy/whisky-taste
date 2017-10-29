import route from 'riot-route'

const URL_ROOT = '#' /* RouterのURLルート */

/* 相対URL取得 */
const getURL = (url = location.href) => url.split(URL_ROOT)[1]

/* 画面遷移実行 */
const redirect = (url = '') => route('/' + url)

/* 'whiskyId_name' のURL文字列からwhiskyIdを取得 */
const getWhiskyId = whiskyIdWithName => parseInt(whiskyIdWithName.slice(0, 1))

export {
  getURL,
  redirect,
  getWhiskyId,
}
