import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-form>
  <div>
    <div class="field">
      <label class="label">ボトル名</label>
      <div class="control">
        <input ref="whiskyName"
               class="input"
               type="text"
               link="whiskies"
               placeholder="ダブルクリックで選択/入力で新規追加"
        >
        <datalist id="whiskies">
          <!-- TODO ここに option属性をループで展開 -->
        </datalist>
      </div>
    </div>
    <div class="field">
      <label class="label">蒸留所</label>
      <div class="control">
        <input ref="distilleryName"
               class="input"
               type="text"
               placeholder="蒸留所名"
        >
      </div>
    </div>
    <div class="field">
      <label class="label">原産国</label>
      <div class="control">
        <input ref="country"
               class="input"
               type="text"
               placeholder="原産国名"
        >
      </div>
    </div>
    <div class="field">
      <label class="label">原産地域</label>
      <div class="control">
        <input ref="region"
               class="input"
               type="text"
               placeholder="原産地域"
        >
      </div>
    </div>
    <div class="field">
      <label class="label">度数</label>
      <div class="control">
        <input ref="strength"
               class="input"
               type="text"
               placeholder="47.3"
        >
      </div>
    </div>

    <div>
      <label>評価点数
        <input ref="score"
               type="range"
               name="score"
               required
               min="1"
               max="20"
               value="10"
               step="1"
               oninput={ showScore }>
        <span>{ score }</span>
      </label>
    </div>

    <div class="field">
      <label class="label">テイスティングコメント</label>
      <div class="control">
        <input ref="comment"
               class="textarea"
               type="text"
        >
      </div>
    </div>
  </div>

  <div>
    <button class="button"
            onclick={postNewReview}>
      投稿する
    </button>

    <button class="button"
            onclick={quitReviewing}>
      やめる
    </button>
  </div>
  
  <script>
   this.store = opts.store

   initScore () {
     this.score = 10
   }
   
   showScore () {
     this.score = parseInt(this.refs.score.value)
   }

   resetForm () {
     this.refs.whiskyName.value = ''
     this.refs.score.value = 10
     this.initScore()
     this.refs.comment.value = ''
   }

   postNewReview () {
     const whiskyName =  this.refs.whiskyName.value
     const score = this.score
     const comment = this.refs.comment.value
     if (!whiskyName || !score) return
     ac.postNewReview({
       whiskyName,
       score,
       comment,
     })
   }

   quitReviewing () {
     this.resetForm()
     ac.quitReviewing()
   }

   returnBeforePage () {
     window.location.href = this.store.data.url
   }

   this.store.on(StoreMessage.REVIEWS_UPDATED, this.quitReviewing)
   this.store.on(StoreMessage.REVIEWING_QUITED, this.returnBeforePage)

   /* データ初期化 */
   this.initScore()
  </script>
</review-form>
