import riot from 'riot'
import Action from './constants/action.js'
import StoreMessage from './constants/store-message.js'
import ct from './controller.js'

const store = riot.observable()

store.data = {
  reviews: [],
}

function setActionHandler (action, updateFn) {
  ct.on(action, data => {
    updateFn(data)
    store.trigger(StoreMessage.UPDATE_STORE, store.data)
  })
}

setActionHandler(Action.POST_NEW_REVIEW, newReview => {
  store.data.reviews.push(newReview)
})

/*

store.update = () => {
  store.trigger(Constants.UPDATE_STORE, store.data)
}

store.setActionResolver = (action, updateFn) => {
  ct.on(action, data => {
    updateFn(data)
    store.update()
  })
}

store.setActionResolver(Action.POST_NEW_REVIEW, newReview => {
  store.data.whisky.push(newReview)
})
*/

/*
ct.on(Action.POST_NEW_REVIEW, (newReview) => {
  store.data.whisky.push(newReview)
  store.update() // 高階関数による共通化↑
})
*/

export default store
