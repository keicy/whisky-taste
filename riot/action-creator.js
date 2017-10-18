import riot from 'riot'
import Action from './constants/action.js'
import ax from 'axios'

export default riot.observable({
  getAllReviews () {
    ax.get('/reviews')
      .then((reviews) => {
        this.trigger(Action.GET_ALL_REVIEWS, reviews.data.reviews)
      })
  },

  postNewReview (newReview) {
    // TODO ここでAPI通信しDBに登録する
    this.trigger(Action.POST_NEW_REVIEW, newReview)
  },
})
