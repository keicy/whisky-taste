import { redirect } from '../utils.js'

import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-button>
  <div>
    <button class="button is-link"
            onclick={saveURL}
            hide={isReviewing}>
      レビューを書く
    </button>
    <button class="button"
            onclick={returnBeforePage}
            show={isReviewing}>
      レビューをやめる
    </button>
  </div>
  <script >
   this.store = opts.store

   saveURL () {
     ac.saveURL()
   }

   returnBeforePage () {
     redirect(this.store.data.url)
   }

   showReviewForm () {
     redirect('/new-review')
   }

   toggleActivate () {
     this.isReviewing = this.store.data.isReviewing
     this.update()
   }

   this.store.on(StoreMessage.URL_SAVED, this.showReviewForm)
   this.store.on(StoreMessage.REVIEWING_ENTERED, this.toggleActivate)
   this.store.on(StoreMessage.REVIEWING_EXITED, this.toggleActivate)

   this.isReviewing = false
  </script>
</review-button>
