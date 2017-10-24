import riot from 'riot'
import Action from './constants/action.js'
import ax from 'axios'

export default riot.observable({
  initStore () {
    ax.get('/init')
      .then(initData => {
        this.trigger(Action.INIT_STORE, initData.data)
      })
  },

  getAllReviews () {
    ax.get('/reviews')
      .then((res) => {
        this.trigger(Action.GET_ALL_REVIEWS, res.data.reviews)
      })
  },

  postNewReview (newReview) {
    if (newReview.whiskeyId) { // レビューのみ登録のAPIを叩く
      const review = {
        whiskeyId: newReview.whiskeyId,
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

  startReviewing () {
    const url = location.href
    const isReviewing = true
    this.trigger(Action.START_REVIEWING, {url, isReviewing})
  },

  quitReviewing () {
    const isReviewing = false
    this.trigger(Action.QUIT_REVIEWING, isReviewing)
  },
})
