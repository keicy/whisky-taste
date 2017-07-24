import riot from 'riot'
import Action from './action.js'

export default riot.observable({
  postNewReview (newReview) {
    this.trigger(Action.POST_NEW_REVIEW, newReview)
  },
})

/*
const ct = riot.observable()

ct.postNewReview = (newReview) => {
  ct.trigger(Action.POST_NEW_REVIEW, newReview)
}

export default ct
*/
