import route from 'riot-route'
import riot from 'riot'

import { redirect } from './utils.js'
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
  route('/new-review', () => {
    riot.mount('content', 'review-form', {store})
  })
  route('/*', (whiskyIdWithName) => {
    const whiskyId = parseInt(whiskyIdWithName.slice(0, 1))
    riot.mount('content', 'review-list', {
      store,
      whiskyId,
    })
  })

  route.start(true)
  redirect()
})
