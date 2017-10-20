import ac from '../action-creator.js'

<new-taste>
  <table>
    <tr each={ reviews }>
      <td>{ whiskyName }</td>
      <td>{ score }</td>
      <td>{ comment }</td>
    </tr>
  </table>
  
  <form onsubmit={ postNewReview }>
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

    <p>
      <input
        type="submit"
        name="taste"
        value="投稿する">
    </p>
  </form>

  <script>
   import StoreMessage from '../constants/store-message.js'

   this.store = opts.store

   setReviews() {
     this.reviews = this.store.data.reviews
   }

   updateReviews() {
     this.setReviews()
     this.update()
   }

   initScore() {
     this.score = 10
   }
   
   showScore() {
     this.score = parseInt(this.refs.score.value)
   }

   resetForm() {
     this.refs.whiskyName.value = ''
     this.refs.score.value = 10
     this.initScore()
     this.refs.comment.value = ''
   }

   postNewReview(e) {
     e.preventDefault()
     const whiskyName =  this.refs.whiskyName.value
     const score = this.score
     const comment = this.refs.comment.value
     if (!whiskyName || !score) return
     ac.postNewReview({
       whiskyName,
       score,
       comment,
     })
     this.resetForm()
   }

   this.store.on(StoreMessage.REVIEWS_UPDATED, this.updateReviews)

   /* データ初期化 */
   this.setReviews()
   this.initScore()
  </script>
</new-taste>
