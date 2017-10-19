import route from 'riot-route'

import './new-taste.tag'
import './container.tag'
import './case-one.tag'
import './case-two.tag'
import './underground.tag'

/* .tag でなく index.js で良いかも */

<app>
  <content></content>
  <script>
   /*
   import ac from '../action-creator.js'
   import store from '../store.js'
   import StoreMessage from '../constants/store-message.js'

   this.data = {}
   const self = this

   store.on(StoreMessage.UPDATE_STORE, (data) => {
     self.data = data
     console.log(self.data)
     riot.update()
   })

   this.on('before-mount', ac.getAllReviews())
   this.on('mount', ac.getAllReviews())

   route('/', () => riot.mount('content', 'new-taste', self.data))
   */
   
      route('/', () => riot.mount('content', 'container'))
      route('/test', () => {
      riot.mount('content-header', 'case-one')
      riot.mount('content-body', 'case-two')
      })
      route('/case2', () => {
      riot.mount('content-header', 'case-two')
      riot.mount('content-body', 'case-one')
      })
   
   route.start(true)
  </script>
</app>
