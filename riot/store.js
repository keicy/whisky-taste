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

setActionHandler(
  Action.SET_TARGET_WHISKY,
  StoreMessage.TARGET_WHISKY_SET,
  whisky => { store.data.targetWhisky = whisky }
)

setActionHandler(
  Action.REMOVE_TARGET_WHISKY,
  StoreMessage.TARGET_WHISKY_REMOVED,
  () => { store.data.targetWhisky = null }
)

setActionHandler(
  Action.ACTIVATE_BACK_BUTTON,
  StoreMessage.BACK_BUTTON_SET,
  () => { store.data.isBackButtonActive = true }
)

setActionHandler(
  Action.DEACTIVATE_BACK_BUTTON,
  StoreMessage.BACK_BUTTON_SET,
  () => { store.data.isBackButtonActive = false }
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
  Action.SAVE_URL,
  StoreMessage.URL_SAVED,
  url => {
    store.data.url = url
  }
)

setActionHandler(
  Action.ENTER_REVIEWING,
  StoreMessage.REVIEWING_ENTERED,
  t => {
    store.data.isReviewing = t
  }
)

setActionHandler(
  Action.EXIT_REVIEWING,
  StoreMessage.REVIEWING_EXITED,
  f => {
    store.data.isReviewing = f
  }
)

setActionHandler(
  Action.POST_NEW_REVIEW,
  StoreMessage.REVIEWS_UPDATED,
  newReview => store.data.reviews.push(newReview)
)

setActionHandler(
  Action.POST_NEW_WHISKY_WITH_REVIEW,
  StoreMessage.WHISKY_AND_REVIEW_UPDATED,
  newWhiskyWithReview => {
    store.data.whiskies.push(newWhiskyWithReview.whiskies)
    store.data.reviews.push(newWhiskyWithReview.reviews)
  }
)

export default store
