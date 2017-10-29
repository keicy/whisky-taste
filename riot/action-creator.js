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

  activateBackButton () {
    const isBackButtonActive = true
    this.trigger(Action.ACTIVATE_BACK_BUTTON, isBackButtonActive)
  },

  deactivateBackButton () {
    const isBackButtonActive = false
    this.trigger(Action.DEACTIVATE_BACK_BUTTON, isBackButtonActive)
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
