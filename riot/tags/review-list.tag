import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-list>
  <!--
  <ul>
    <li each={ reviews }
        class="box">
      <div class="columns">

        <div class="column is-2">
          <div class="title is-5">
            評価点
          </div>
          { score } / 20 点
        </div>
        <div class="column">
          <div class="title is-5">
            テイスティングコメント
          </div>
          { comment }
        </div>
        
      </div>
    </li>
  </ul>
  -->

  <div>
    <table class="table is-fullwidth">
      <thead>
        <tr>
          <td>評価点</td>
          <td>テイスティングコメント</td>
        </tr>
      </thead>
      <tbody>
        <tr each={ reviews }>
          <td>{ score } / 20</td>
          <td>{ comment }</td>
        </tr>
      </tbody>
    </table>
  </div>

  <script>
   this.store = opts.store

   init () {
     this.whisky = this.store.data.targetWhisky
     this.setReviews()
   }

   setReviews () {
     this.reviews = this.store.data.reviews.filter(
       r => r.whiskyId === this.store.data.targetWhisky.whiskyId
     )
   }

   this.on('before-mount', () => {
     ac.activateBackButton()
     this.setReviews()
   })

   this.on('unmount', () => { ac.deactivateBackButton() })

   /* データ初期化 */
   this.init()
  </script>
</review-list>
