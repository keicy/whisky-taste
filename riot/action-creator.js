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
    ax.post('/reviews', newReview)
      .then((res) => {
        this.trigger(Action.POST_NEW_REVIEW, res.data.newReview)
      })
  },
})
