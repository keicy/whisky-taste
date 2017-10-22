import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-form>
  <div>
    <p>
      <label>ボトル名
        <input ref="whiskyName"
               type="text"
               name="whiskyName"
               required>
      </label>
    </p>

    <p>
      <label>点数
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
    </p>

    <p>
      <label>テイスティングコメント
        <input ref="comment"
               type="text"
               name="comment">
      </label>
    </p>
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
