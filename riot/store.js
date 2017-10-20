import riot from 'riot'
import ax from 'axios'
import Action from './constants/action.js'
import ac from './action-creator.js'
import StoreMessage from './constants/store-message.js'

/* TODO オブジェクトとして定義したほうが良いかも？ */

const store = riot.observable()

function setActionHandler (
  action,
  storeMsg,
  updateFn
) {
  ac.on(action, data => {
    updateFn(data)
    store.trigger(storeMsg)
  })
}

setActionHandler(
  Action.INIT_STORE,
  StoreMessage.STORE_INITED,
  initData => { store.data = initData }
)

/*
setActionHandler(
  Action.GET_ALL_REVIEWS,
  StoreMessage.REVIEWS_UPDATED,
  reviews => {
    store.data.reviews = reviews
  })
 */

setActionHandler(
  Action.POST_NEW_REVIEW,
  StoreMessage.REVIEWS_UPDATED,
  newReview => store.data.reviews.push(newReview)
)

export default store
