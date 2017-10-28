import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<whisky-search show={isActive}>
  <div class="control">
    <input ref="whiskySearch"
           type="text"
           placeholder="ウイスキー検索"
           class="input"
           oninput={updateWhiskySearchWord}>
  </div>
  <script>
   this.store = opts.store

   updateWhiskySearchWord () {
     const searchWord = this.refs.whiskySearch.value
     ac.updateWhiskySearchWord(searchWord)
   }

   toggleActivate () {
     this.refs.whiskySearch.value = ''
     this.isActive = this.store.data.isWhiskySearchActive
     this.update()
   }

   this.store.on(StoreMessage.WHISKY_SEARCH_SET, this.toggleActivate)

   this.isActive = false
  </script>
</whisky-search>
