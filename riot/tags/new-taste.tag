import ac from '../action-creator.js'

<new-taste>
  <table>
    <tr each={ opts.reviews }>
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
   postNewReview(e) {
     e.preventDefault()
     ac.postNewReview({
       whiskyName: this.refs.whiskyName.value,
       score: this.refs.score.value,
       comment: this.refs.comment.value,
     })
     // this.refs.whiskyName.value = '' // 的なコードで入力欄をリセットする
     // http://riotjs.com/ja/guide/#カスタムタグの例
   }
  </script>
</new-taste>
