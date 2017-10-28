import route from 'riot-route'

import ac from '../action-creator.js'
import StoreMessage from '../constants/store-message.js'

<review-form>
  <form onsubmit={ postNewReview }>

    <div class="field">
      <label class="label">ボトル名</label>
      <div class="control">
        <input ref="whiskyName"
               type="text"
               list="whiskies"
               placeholder="ダブルクリックで選択/入力で新規追加"
               class="input"
               required
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
               readonly={ knownWhiskyId }
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
               readonly={ knownWhiskyId }
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
               readonly={ knownWhiskyId }
        >
      </div>
    </div>

    <div class="field">
      <label class="label">度数</label>
      <div class="control">
        <input ref="strength"
               type="number"
               step=0.1
               pattern="^\d{1,2}(\.\d)?$"
               placeholder=47.3
               class="input"
               readonly={ knownWhiskyId }
        >
      </div>
    </div>

    <div class="field">
      <label class="label">評価点</label>
      <div id="score">
        <input ref="score"
               type="range"
               name="score"
               min=1
               max=20
               value=10
               step=1
               required
               oninput={ showScore }
        >
        <div id="score-display">
          { score }
        </div>
      </div>
    </div>

    <div class="field">
      <label class="label">テイスティングコメント</label>
      <div class="control">
        <textarea ref="comment"
                  class="textarea">
        </textarea>
      </div>
    </div>

    <div class="field">
      <p class="control">
        <input type="submit"
               value="投稿する"
               class="button is-link">
      </p>
    </div>

  </form>
  
  <script>
   this.store = opts.store

   initScore () {
     this.score = 10
   }

   init () {
     this.whiskies = this.store.data.whiskies
     this.knownWhiskyId = null
     this.initScore()
   }

   whiskyNameSelected () {
     const whiskyName =  this.refs.whiskyName.value
     const whisky = this.whiskies.find(w => w.whiskyName === whiskyName)
     if (whisky) {
       this.knownWhiskyId = whisky.whiskyId
       this.setWhiskyForm (whisky)
     } else {
       this.knownWhiskyId = null
       this.resetWhiskyForm ()
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

   postNewReview (e) {
     e.preventDefault()
     const whiskyId = this.knownWhiskyId
     const whiskyName =  this.refs.whiskyName.value
     const distilleryName = this.refs.distilleryName.value
     const country = this.refs.country.value
     const region = this.refs.region.value
     const strength = parseFloat(this.refs.strength.value)
     const score = this.score
     const comment = this.refs.comment.value
     if (!whiskyName || !score) return
     ac.postNewReview({
       whiskyId,
       whiskyName,
       distilleryName,
       country,
       region,
       strength,
       score,
       comment,
     })
   }

   /*
      quitReviewing () {
      // this.resetForm() // TODO いらない模様（毎回新しくマウントされるので）
      ac.quitReviewing()
      }
    */

   returnBeforePage () {
     route(this.store.data.url)
   }

   this.store.on(StoreMessage.REVIEWS_UPDATED, this.returnBeforePage)
   this.store.on(StoreMessage.WHISKY_AND_REVIEW_UPDATED, this.returnBeforePage)
   this.on('before-mount', () => ac.enterReviewing())
   this.on('unmount', () => ac.exitReviewing())

   /* データ初期化 */
   this.init()
  </script>

  <style>
   #score {
     display: flex;
   }
   #score-display {
     font-size: 1.3em;
     margin-left: 0.4em;
     font-weight: bold;
   }
  </style>
</review-form>
