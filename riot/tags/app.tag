import route from 'riot-route'

import './app-layout.tag'
import './new-taste.tag'
import './case-one.tag'
import './case-two.tag'
import './underground.tag'

/* .tag でなく index.js で良いかも？併合できるかな？ */

/* このファイルを、データやルーティング含めアプリの初期化動作と位置づけると
 * ここでstore初期化のアクションを作成するのは筋が通っている気がする.
 * store の初期化が終わったら `route.start(true)` を呼んでアプリを起動すれば良さそう.
*/

<app>
  <root></root>
  <script>
   import store from '../store.js'

   console.log(store)
   console.log(store.data)
   console.log(store.say)
   console.log(store.data.reviews)

   route('/', () => {
     riot.mount('root', 'app-layout') // ここをappにマウントするようにすればいいかな？
     riot.mount('content-body', 'new-taste', { store })
   })
   
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

   /*
      route('/', () => riot.mount('content', 'container'))
      route('/test', () => {
      riot.mount('content-header', 'case-one')
      riot.mount('content-body', 'case-two')
      })
      route('/case2', () => {
      riot.mount('content-header', 'case-two')
      riot.mount('content-body', 'case-one')
      })
    */
   
   route.start(true)
  </script>
</app>
