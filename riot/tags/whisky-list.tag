import { redirect } from '../utils.js'
import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

import './whisky-box.tag'

<whisky-list>
  <ul>
    <li each={ whiskies }
        onclick={ parent.gotoReviewList }>
      <whisky-box whisky={this} />
    </li>
  </ul>
  <script>
   this.store = opts.store

   setWhiskies() {
     const whiskies = this.store.data.whiskies
     const searchWord = this.store.data.whiskySearchWord
     if (searchWord) {
       this.whiskies = whiskies.filter(
         w => w.whiskyName.toLowerCase().includes(searchWord.toLowerCase())
       )
     } else {
       this.whiskies = whiskies
     }
   }

   updateWhiskies() {
     this.setWhiskies()
     this.update()
   }

   gotoReviewList(e) {
     const whisky = e.item
     redirect('/' + whisky.whiskyId + '_' + whisky.whiskyName.replace(/ /g, '_'))
   }

   // 現状マウント時にstoreからデータ取得しなおしているのでなくて良い.
   // this.store.on(StoreMessage.WHISKY_AND_REVIEW_UPDATED, this.updateWhiskies)

   this.store.on(StoreMessage.WHISKY_SEARCH_WORD_UPDATED, this.updateWhiskies)

   this.on('before-mount', () => ac.activateWhiskySearch())

   this.on('unmount', () => ac.deactivateWhiskySearch())
   
   /* データ初期化 */
   this.setWhiskies()
  </script>
  <style>
   .box {
     cursor: pointer;
   }

   li:not(:last-child) {
     margin-bottom: 0.5em;
   }
  </style>
</whisky-list>
