import riot from 'riot'

import ct from './controller.js'
import Action from './action.js'

const store = riot.observable()

store.data = {
  whisky: [],
}

ct.on('from-ct', (newReview) => {
  console.log(newReview)
  store.data.whisky.push(newReview)
  console.log(store.data.whisky)
  store.trigger('from-store', store.data)
})

export default store
