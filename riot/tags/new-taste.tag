import ct from '../controller.js'

<new-taste>
  <table>
    <tr each={ opts.whisky }>
      <td>{ name }</td>
      <td>{ date }</td>
      <td>{ comment }</td>
    </tr>
  </table>
  
  <form onsubmit={ postNewReview }>
    <p>
      <label>銘柄
        <input
            type="text"
            name="name"
            onchange={ onName }>
      </label>
    </p>

    <p>
      <label>日付
        <input
            type="date"
            name="date"
            onchange={ onDate }>
      </label>
    </p>

    <p>
      <label>テイスティングコメント
        <input
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
   var name
   var date
   var comment

   onName(e) {
     name = e.target.value
   }
   onDate(e) {
     date = e.target.value
   }
   onComment(e) {
     comment = e.target.value
   }
   postNewReview(e) {
     e.preventDefault()
     ct.postNewReview({
       name,
       date,
       comment,
     })
   }
  </script>
</new-taste>
