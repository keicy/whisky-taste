import { redirect } from '../utils.js'

import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-button>
  <button class="button is-link"
          onclick={saveURL}
          disabled={disabled}>
    レビューを書く
  </button>
  <script >
   this.store = opts.store

   saveURL () {
     ac.saveURL()
   }

   showReviewForm () {
     redirect('/new-review')
   }

   toggleActivate () {
     this.disabled = this.store.data.isReviewing
     this.update()
   }

   this.store.on(StoreMessage.URL_SAVED, this.showReviewForm)
   this.store.on(StoreMessage.REVIEWING_ENTERED, this.toggleActivate)
   this.store.on(StoreMessage.REVIEWING_EXITED, this.toggleActivate)

   this.disabled = false
  </script>
</review-button>
