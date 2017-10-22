import route from 'riot-route'
import riot from 'riot'

import ac from './action-creator.js'
import store from './store.js'
import StoreMessage from './constants/store-message.js'

import './tags/app-layout.tag'
import './tags/review-form.tag'
import './tags/review-list.tag'
import './tags/review-button.tag'

ac.initStore()

store.one(StoreMessage.STORE_INITED, () => {
  route('/', () => {
    riot.mount('app', 'app-layout')
    riot.mount('eyecatch', 'review-button', {store})
//    riot.mount('post-form', 'review-form')
    riot.mount('item-list', 'review-list', {store})
  })
  route('/new-review', () => {
    riot.mount('post-form', 'review-form', {store})
  })
  route.start(true)
})
