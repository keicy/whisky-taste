import riot from 'riot'
import ax from 'axios'
import Action from './constants/action.js'
import ac from './action-creator.js'
import StoreMessage from './constants/store-message.js'

/* TODO オブジェクトとして定義したほうが良さそう */

const store = riot.observable()

// store.data = {}

/* init data */
// 多分ここが非同期なので初期化がうまくいっていない...
//
// 1. 初期化完了したら trigger で通知する
// 2. ルーターがマウントされた時にアクションを受けて初期化する

function setActionHandler (
  action,
  storeMsg,
  updateFn
) {
  ac.on(action, data => {
    updateFn(data)
    store.trigger(storeMsg, store.data)
  })
}

setActionHandler(
  Action.INIT_STORE,
  StoreMessage.STORE_INITED,
  initData => {
    store.data = initData
  })

setActionHandler(
  Action.GET_ALL_REVIEWS,
  StoreMessage.UPDATE_STORE,
  reviews => {
    store.data.reviews = reviews
  })

setActionHandler(
  Action.POST_NEW_REVIEW,
  StoreMessage.UPDATE_STORE,
  newReview => {
    store.data.reviews.push(newReview)
  })

export default store







// var data = {
//   reviews: [],
// }
// 
// // TODO 可変長引数対応 `...data`
// function setActionHandler (action, updateFn) {
//   ac.on(action, data => {
//     updateFn(data)
//   })
// }
// 
// setActionHandler(Action.GET_ALL_REVIEWS, reviews => {
//   console.log(reviews)
//   /*
//    * 代入してもとObjとちがくなるとのと動かなくなるっぽい
//    * やはり根本的な対応が必要そう
//    */
//   // data.reviews = reviews // => NG. 追加も動かない.
//   // data = Object.assign(data, {reviews}) // NG. 追加も動かない.
//   // data.reviews.concat(reviews) // => NG.
//   reviews.forEach((review) => data.reviews.push(review)) // => おしい.
//   console.log(data.reviews)
// })
// 
// setActionHandler(Action.POST_NEW_REVIEW, newReview => {
//   data.reviews.push(newReview) // => OK
//   // data.reviews = [newReview] // => NG
// })
// 
// export default data
