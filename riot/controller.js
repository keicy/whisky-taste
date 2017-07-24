import riot from 'riot'
import Action from './action.js'

const ct = riot.observable()

ct.postNewReview = (newReview) => {
  ct.trigger(Action.POST_NEW_REVIEW, newReview)
}

export default ct
