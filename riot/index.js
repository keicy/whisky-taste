import route from 'riot-route'
import riot from 'riot'

import ac from './action-creator.js'
import store from './store.js'
import StoreMessage from './constants/store-message.js'

import './tags/app-layout.tag'
import './tags/whisky-list.tag'
import './tags/review-form.tag'
import './tags/review-list.tag'
import './tags/review-button.tag'

ac.initStore()

store.one(StoreMessage.STORE_INITED, () => {
  riot.mount('app', 'app-layout', {store})

  route('/', () => {
    riot.mount('content', 'whisky-list', {store})
  })
  // TODO /reviews/new ?
  route('/new-review', () => {
    riot.mount('content', 'review-form', {store})
  })
  // TODO もう少し良くならないか？
  route('/*', (whiskyName) => {
    riot.mount('content', 'review-list', {store})
  })

  route.start(true)
  route('/')
})
