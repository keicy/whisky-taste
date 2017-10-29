import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

import './whisky-box.tag'

<review-list>
  <div>
    <whisky-box whisky={whisky} />
    <table class="table is-fullwidth">
      <thead>
        <tr>
          <td class="score">評価点<br>(20点)</td>
          <td>テイスティングコメント</td>
        </tr>
      </thead>
      <tbody>
        <tr each={ reviews }>
          <td class="score">{ score }</td>
          <td>{ comment }</td>
        </tr>
      </tbody>
    </table>
  </div>

  <script>
   this.store = opts.store
   this.whiskyId = opts.whiskyId

   init () {
     this.whisky = this.store.data.whiskies.find(w =>
       w.whiskyId === this.whiskyId
     )
     this.setReviews()
   }

   setReviews () {
     this.reviews = this.store.data.reviews.filter(r =>
       r.whiskyId === this.whiskyId
     )
   }

   /*
   // 現状毎表示時にmountしなおしているのでなくて良い.
   // mountではなくshow={t/f}で切り替えの時には役に立つ.
      updateReviews() {
        this.setReviews()
        this.update()
      }

      this.store.on(StoreMessage.REVIEWS_UPDATED, this.updateReviews)
      this.store.on(StoreMessage.WHISKY_AND_REVIEW_UPDATED, this.updateReviews)
    */

   this.on('before-mount', () => ac.activateBackButton())

   this.on('unmount', () => ac.deactivateBackButton())

   /* データ初期化 */
   this.init()
  </script>

  <style>
   .score {
     width: 5em;
     text-align: center;
   }

   tbody .score {
     font-weight: bold;
     font-size: 1.25em;
     padding: 0.25em 0.5em;
     vertical-align: middle;
   }
  </style>
</review-list>
