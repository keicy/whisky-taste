import riot from 'riot'
import Action from './constants/action.js'
import ac from './action-creator.js'
import StoreMessage from './constants/store-message.js'

const store = riot.observable()

store.data = {
  reviews: [],
}

function setActionHandler (action, updateFn) {
  ac.on(action, data => {
    updateFn(data)
    store.trigger(StoreMessage.UPDATE_STORE, store.data)
  })
}

setActionHandler(Action.GET_ALL_REVIEWS, reviews => {
  store.data.reviews = reviews
})

setActionHandler(Action.POST_NEW_REVIEW, newReview => {
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
