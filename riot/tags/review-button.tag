import { redirect } from '../utils.js'

import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-button>
  <button class="button is-link"
          onclick={startReviewing}
          disabled={disabled}>
    レビューを書く
  </button>
  <script >
   this.store = opts.store

   startReviewing () {
     ac.startReviewing()
   }
   
   showReviewForm () {
     this.toggleActivate ()
     redirect('/new-review')
   }

   toggleActivate () {
     this.disabled = this.store.data.isReviewing
     this.update()
   }
   
   this.store.on(StoreMessage.REVIEWING_READY, this.showReviewForm)
   this.store.on(StoreMessage.REVIEWING_QUITED, this.toggleActivate)

   this.disabled = false
  </script>
</review-button>
