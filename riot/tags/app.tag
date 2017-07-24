import route from 'riot-route'
import store from '../store.js'
import StoreMessage from '../constants/store-message.js'

import './new-taste.tag'
import './case-one.tag'
import './case-two.tag'
import './underground.tag'

<app>
  <content></content>
  <script>
   route('/', () => riot.mount('content', 'new-taste', opts.data))
   route('/one', () => riot.mount('content', 'case-one'))
   route('/two', () => riot.mount('content', 'case-two'))
   route('/two/underground', () => riot.mount('content', 'underground'))
   route.start(true)

   store.on(StoreMessage.UPDATE_STORE, (data) => {
     this.update(data)
   })
  </script>
</app>
