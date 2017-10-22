import riot from 'riot'
import Action from './constants/action.js'
import ac from './action-creator.js'
import StoreMessage from './constants/store-message.js'

/* TODO オブジェクトとして定義したほうが良いかも？ */


/* TODO 
 * store.data いらないっぽい.直接 store.reviews に格納すれば良い.
 * 初期化は Object.assign で.
 * あるいはプロパティをforでひとつづつ移す.
 */

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

setActionHandler(
  Action.START_REVIEWING,
  StoreMessage.REVIEWING_READY,
  startReview => {
    store.data.url = startReview.url
    store.data.isReviewing = startReview.isReviewing
  }
)

setActionHandler(
  Action.QUIT_REVIEWING,
  StoreMessage.REVIEWING_QUITED,
  quitReview => {
    store.data.isReviewing = quitReview
  }
)

export default store
