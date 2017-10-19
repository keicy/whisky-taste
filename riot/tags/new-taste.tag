import ac from '../action-creator.js'

<new-taste>
  <table>
    <tr each={ this.reviews }>
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
               onchange={ onName }>
      </label>
    </p>

    <p>
      <label>点数
        <input ref="score"
               type="number"
               name="score"
               min="1"
               max="20"
               value="10"
               onchange={ onDate }>
      </label>
    </p>

    <p>
      <label>テイスティングコメント
        <input ref="comment"
               type="text"
               name="comment"
               onchange={ onComment }>
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

   resetForm() {
     this.refs.whiskyName.value = ''
     this.refs.score.value = 10
     this.refs.comment.value = ''
   }

   postNewReview(e) {
     e.preventDefault()
     ac.postNewReview({
       whiskyName: this.refs.whiskyName.value,
       score: parseInt(this.refs.score.value),
       comment: this.refs.comment.value,
     })
       this.resetForm()
   }

   this.store.on(StoreMessage.REVIEWS_UPDATED, this.updateReviews)

   this.setReviews() // データ初期化
  </script>
</new-taste>
