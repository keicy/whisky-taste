import riot from 'riot'
import ax from 'axios'
import { getURL } from './utils.js'

import Action from './constants/action.js'

export default riot.observable({
  initStore () {
    ax.get('/init')
      .then(initData => {
        this.trigger(Action.INIT_STORE, initData.data)
      })
  },

  setTargetWhisky (whisky) {
    this.trigger(Action.SET_TARGET_WHISKY, whisky)
  },

  activateWhiskySearch () {
    const isWhiskySearchActive = true
    const whiskySearchWord = ''
    this.trigger(Action.ACTIVATE_WHISKY_SEARCH, {
      isWhiskySearchActive,
      whiskySearchWord,
    })
  },

  deactivateWhiskySearch () {
    const isWhiskySearchActive = false
    const whiskySearchWord = ''
    this.trigger(Action.DEACTIVATE_WHISKY_SEARCH, {
      isWhiskySearchActive,
      whiskySearchWord,
    })
  },

  updateWhiskySearchWord (searchWord) {
    this.trigger(Action.UPDATE_WHISKY_SEARCH_WORD, searchWord)
  },

  //TODO FIX
  removeTargetWhisky () {
    this.trigger(Action.REMOVE_TARGET_WHISKY)
  },

  //TODO FIX
  activateBackButton () {
    this.trigger(Action.ACTIVATE_BACK_BUTTON)
  },

  //TODO FIX
  deactivateBackButton () {
    this.trigger(Action.DEACTIVATE_BACK_BUTTON)
  },

  /*
  getAllReviews () {
    ax.get('/reviews')
      .then((res) => {
        this.trigger(Action.GET_ALL_REVIEWS, res.data.reviews)
      })
  },
*/

  postNewReview (newReview) {
    if (newReview.whiskyId) { // レビューのみ登録のAPIを叩く
      const review = {
        whiskyId: newReview.whiskyId,
        score: newReview.score,
        comment: newReview.comment,
      }
      ax.post('/reviews', review)
        .then(res => {
          this.trigger(Action.POST_NEW_REVIEW, res.data.newReview)
        })
    } else { // ウイスキーとレビューを登録するAPIを叩く
      const whisky = {
        whiskyName: newReview.whiskyName,
        distilleryName: newReview.distilleryName,
        country: newReview.country,
        region: newReview.region,
        strength: newReview.strength,
      }
      const review = {
        score: newReview.score,
        comment: newReview.comment,
      }
      const whiskyWithReview = {
        whisky,
        review,
      }
      console.log(whiskyWithReview)
      ax.post('/whiskies/reviews', whiskyWithReview)
        .then(res => {
          this.trigger(Action.POST_NEW_WHISKY_WITH_REVIEW, res.data.newWhiskyWithReview)
        })
    }
  },

  saveURL () {
    const url = getURL()
    this.trigger(Action.SAVE_URL, url)
  },

  enterReviewing () {
    const isReviewing = true
    this.trigger(Action.ENTER_REVIEWING, isReviewing)
  },

  exitReviewing () {
    const isReviewing = false
    this.trigger(Action.EXIT_REVIEWING, isReviewing)
  },
})
