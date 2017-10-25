import { redirect } from '../utils.js'

import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<whisky-list>
  <ul>
    <li each={ whiskies }>
      <!-- <a href={ '#/' + whiskyName.replace(/ /g, '_') } > -->
      <a href={ '#/' + whiskyId + '/' +  whiskyName.replace(/ /g, '_')} >
      <div class="box">
        { whiskyName }
      </div>
      </a>
    </li>
  </ul>
  <script>
   this.store = opts.store

   setWhiskies() {
     this.whiskies = this.store.data.whiskies
   }

   updateWhiskies() {
     this.setWhiskies()
     this.update()
   }

   this.store.on(StoreMessage.WHISKY_AND_REVIEW_UPDATED, this.updateWhiskies)

   /* データ初期化 */
   this.setWhiskies()
  </script>
</whisky-list>
