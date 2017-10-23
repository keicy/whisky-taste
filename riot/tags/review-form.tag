import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-form>
  <div>
    <div class="field">
      <label class="label">ボトル名</label>
      <div class="control">
        <input ref="whiskyName"
               type="text"
               list="whiskies"
               placeholder="ダブルクリックで選択/入力で新規追加"
               class="input"
               onchange={ whiskyNameSelected }
        >
        <datalist id="whiskies">
          <virtual each={ whiskies }>
            <option>{ whiskyName }</option>
          </virtual>
        </datalist>
      </div>
    </div>

    <div class="field">
      <label class="label">蒸留所</label>
      <div class="control">
        <input ref="distilleryName"
               type="text"
               placeholder="蒸留所名"
               class="input"
               readonly={ knownWhiskeyId }
        >
      </div>
    </div>

    <div class="field">
      <label class="label">原産国</label>
      <div class="control">
        <input ref="country"
               type="text"
               placeholder="原産国名"
               class="input"
               readonly={ knownWhiskeyId }
        >
      </div>
    </div>

    <div class="field">
      <label class="label">原産地域</label>
      <div class="control">
        <input ref="region"
               type="text"
               placeholder="原産地域"
               class="input"
               readonly={ knownWhiskeyId }
        >
      </div>
    </div>

    <div class="field">
      <label class="label">度数</label>
      <div class="control">
        <input ref="strength"
               type="text"
               placeholder=47.3
               class="input"
               readonly={ knownWhiskeyId }
        >
      </div>
    </div>

    <div>
      <label>評価点数
        <!-- TODO 初期値を "10" でなく 10 にする. 他の属性値も! -->
        <input ref="score"
               type="range"
               name="score"
               required
               min="1"
               max="20"
               value="10"
               step="1"
               oninput={ showScore }
        >
        <span>{ score }</span>
      </label>
    </div>

    <div class="field">
      <label class="label">テイスティングコメント</label>
      <div class="control">
        <input ref="comment"
               type="text"
               class="textarea"
        >
      </div>
    </div>
  </div>

  <div>
    <button class="button"
            onclick={ postNewReview }>
      投稿する
    </button>

    <button class="button"
            onclick={ quitReviewing }>
      やめる
    </button>
  </div>
  
  <script>
   this.store = opts.store

   initScore () {
     this.score = 10
   }

   init () {
     this.whiskies = this.store.data.whiskies
     this.knownWhiskeyId = null
     this.initScore()
   }

   whiskyNameSelected () {
     const whiskyName =  this.refs.whiskyName.value
     const whisky = this.whiskies.find(w => w.whiskyName === whiskyName)
     if (whisky) {
       this.knownWhiskeyId = whisky.whiskyId
       this.setWhiskyForm (whisky)
       this.update()
     } else {
       this.knownWhiskeyId = null
       this.resetWhiskyForm ()
       this.update()
     }
   }

   setWhiskyForm (whisky) {
     this.refs.distilleryName.value = whisky.distilleryName
     this.refs.country.value = whisky.country
     this.refs.region.value = whisky.region
     this.refs.strength.value = whisky.strength
   }

   resetWhiskyForm () {
     this.refs.distilleryName.value = ''
     this.refs.country.value = ''
     this.refs.region.value = ''
     this.refs.strength.value = ''
   }

   showScore () {
     this.score = parseInt(this.refs.score.value)
   }

   // TODO 見直しor削除
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
     this.resetForm() // TODO いらないかも？
     ac.quitReviewing()
   }

   returnBeforePage () {
     window.location.href = this.store.data.url
   }

   this.store.on(StoreMessage.REVIEWS_UPDATED, this.quitReviewing)
   this.store.on(StoreMessage.REVIEWING_QUITED, this.returnBeforePage)

   /* データ初期化 */
   this.init()
  </script>
</review-form>
