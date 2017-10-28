import route from 'riot-route'

import StoreMessage from '../constants/store-message.js'

<back-button show={isActive}>
  <button class="button"
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
     route('/')
   }

   this.store.on(StoreMessage.BACK_BUTTON_SET, this.updateIsActive)

   this.setIsActive()
  </script>
</back-button>
