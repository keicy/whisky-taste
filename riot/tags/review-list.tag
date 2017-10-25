import StoreMessage from '../constants/store-message.js'

<review-list>
  <table>
    <tr each={ reviews }>
      <td>{ whiskyName }</td>
      <td>{ score }</td>
      <td>{ comment }</td>
    </tr>
  </table>
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
