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
   
   setReviews() {
     this.reviews = this.store.data.reviews
   }

   updateReviews() {
     this.setReviews()
     this.update()
   }

   this.store.on(StoreMessage.REVIEWS_UPDATED, this.updateReviews)

   /* データ初期化 */
   this.setReviews()
  </script>
</review-list>
