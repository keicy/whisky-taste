import StoreMessage from '../constants/store-message.js'

<review-list>
  <ul>
    <li each={ reviews }
        class="box">
      <div class="columns">

        <div class="column is-2">
          <div class="title is-5">
            評価点
          </div>
          <span class="" >{ score }</span> / 20 点
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
  <script>
   this.store = opts.store

   init () {
     this.whisky = this.store.data.targetWhisky
     this.setReviews()
   }

   setReviews() {
     this.reviews = this.store.data.reviews.filter(
       r => r.whiskyId === this.store.data.targetWhisky.whiskyId
     )
   }

   updateReviews() {
     this.setReviews()
     this.update()
   }

   this.store.on(StoreMessage.REVIEWS_UPDATED, this.updateReviews)

   /* データ初期化 */
   this.init()
  </script>
</review-list>
