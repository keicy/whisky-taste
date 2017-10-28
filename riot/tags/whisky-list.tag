import route from 'riot-route'

import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<whisky-list>
  <ul>
    <li each={ whiskies }
        onclick={ parent.gotoReviewList }
        class="box">
      <div class="title is-4">
        { whiskyName }
      </div>
      <div class="columns">
        <div class="column is-2">
          度数: { strength }％
        </div>
        <div class="column">
          蒸留所: { distilleryName }
        </div>
        <div class="column">
          原産: { country }
        </div>
        <div class="column">
          産地: { region }
        </div>
      </div>
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

   gotoReviewList(e) {
     const whisky = e.item
     ac.setTargetWhisky(whisky)
     route('/' + whisky.whiskyName.replace(/ /g, '_'))
   }

   this.store.on(StoreMessage.WHISKY_AND_REVIEW_UPDATED, this.updateWhiskies)
   this.on('before-mount', () => ac.removeTargetWhisky())

   /* データ初期化 */
   this.setWhiskies()
  </script>
  <style>
   .box {
     cursor: pointer;
   }
   .title {
     margin-bottom: 1rem;
   }

   @media (max-width: 768px) {
     .column {
       padding: 0.25rem 1.75rem;
     }
   }
  </style>
</whisky-list>
