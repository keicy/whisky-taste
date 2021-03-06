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
    store.trigger(storeMsg, data)
  })
}

setActionHandler(
  Action.INIT_STORE,
  StoreMessage.STORE_INITED,
  initData => { store.data = initData }
)

setActionHandler(
  Action.ACTIVATE_WHISKY_SEARCH,
  StoreMessage.WHISKY_SEARCH_SET,
  activate => {
    store.data.isWhiskySearchActive = activate.isWhiskySearchActive
    store.data.whiskySearchWord = activate.whiskySearchWord
  }
)

setActionHandler(
  Action.DEACTIVATE_WHISKY_SEARCH,
  StoreMessage.WHISKY_SEARCH_SET,
  deactivate => {
    store.data.isWhiskySearchActive = deactivate.isWhiskySearchActive
    store.data.whiskySearchWord = deactivate.whiskySearchWord
  }
)

setActionHandler(
  Action.UPDATE_WHISKY_SEARCH_WORD,
  StoreMessage.WHISKY_SEARCH_WORD_UPDATED,
  searchWord => { store.data.whiskySearchWord = searchWord }
)

setActionHandler(
  Action.ACTIVATE_BACK_BUTTON,
  StoreMessage.BACK_BUTTON_SET,
  t => { store.data.isBackButtonActive = t }
)

setActionHandler(
  Action.DEACTIVATE_BACK_BUTTON,
  StoreMessage.BACK_BUTTON_SET,
  f => { store.data.isBackButtonActive = f }
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
  url => { store.data.url = url }
)

setActionHandler(
  Action.ENTER_REVIEWING,
  StoreMessage.REVIEWING_ENTERED,
  t => { store.data.isReviewing = t }
)

setActionHandler(
  Action.EXIT_REVIEWING,
  StoreMessage.REVIEWING_EXITED,
  f => { store.data.isReviewing = f }
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
    store.data.whiskies.push(newWhiskyWithReview.whisky)
    store.data.reviews.push(newWhiskyWithReview.review)
  }
)

export default store
