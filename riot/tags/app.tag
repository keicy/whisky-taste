import route from 'riot-route'

import './new-taste.tag'
import './case-one.tag'
import './case-two.tag'
import './underground.tag'

/* .tag でなく index.js で良いかも */

<app>
  <content></content>
  <script>
   import ac from '../action-creator.js'
   import store from '../store.js'
   import StoreMessage from '../constants/store-message.js'

   const self = this
   self.data = {}

   store.on(StoreMessage.UPDATE_STORE, (data) => {
     self.update({data: data})
   })

   this.on('mount', ac.getAllReviews())

   route('/', () => riot.mount('content', 'new-taste', self.data))
   
   // route('/', () => riot.mount('content', 'new-taste', data)) // これで機能する, Store が Observable である必要すらない. Store 更新が検知され画面表示も更新される.
   route('/one', () => riot.mount('content', 'case-one'))
   route('/two', () => riot.mount('content', 'case-two'))
   route('/two/underground', () => riot.mount('content', 'underground'))
   route.start(true)
  </script>
</app>
