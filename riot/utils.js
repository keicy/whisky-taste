const URL_ROOT = '#' /* RouterのURLルート */

/* 相対URL取得 */
const getURL = (url = location.href) => url.split(URL_ROOT)[1]

export {
  getURL,
}
