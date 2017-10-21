import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-button>
  <button class="button"
          onclick={startReview}
          disabled={disabled} >
    レビューを書く
  </button>
  <script >
   this.store = opts.store

   startReview () {
     ac.startReview()
   }
   
   showReviewForm () {
     this.toggleActive()
     window.location.href = '#/new-review'
   }

   toggleActive () {
     this.disabled = !this.disabled
     this.update()
   }

   this.disabled = false
   this.store.on(StoreMessage.REVIEWING_READY, this.showReviewForm)
  </script>
</review-button>
