import riot from 'riot'

import store from './store.js'

import './tags/app.tag'

riot.mount('app', { data: store.data, })
