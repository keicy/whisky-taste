import route from 'riot-route'
import riot from 'riot'

import ac from './action-creator.js'
import store from './store.js'
import StoreMessage from './constants/store-message.js'

import './tags/app-layout.tag'
import './tags/new-taste.tag'

ac.initStore()

store.one(StoreMessage.STORE_INITED, () => {
  route('/', () => {
    riot.mount('app', 'app-layout')
    riot.mount('content-body', 'new-taste', {store})
  })
  route.start(true)
})
