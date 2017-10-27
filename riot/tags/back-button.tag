import { redirect } from '../utils.js'

import StoreMessage from '../constants/store-message.js'

<back-button>
  <button class="button"
          show={isActive}
          onclick={backToWhiskyList}>
    TOPに戻る
  </button>
  <script>
   this.store = opts.store

   setIsActive() {
     this.isActive = this.store.data.isBackButtonActive
   }
   
   updateIsActive() {
     this.setIsActive()
     this.update()
   }
   
   backToWhiskyList() {
     redirect()
   }

   this.store.on(StoreMessage.BACK_BUTTON_SET, this.updateIsActive)

   this.setIsActive()
  </script>
</back-button>
