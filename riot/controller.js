import riot from 'riot'
import Action from './action.js'

const ct = riot.observable()

ct.postNewReview = (newReview) => {
  console.log(newReview)
  ct.trigger('from-ct', newReview)
}

export default ct
