import riot from 'riot'
import Action from './constants/action.js'

export default riot.observable({
  postNewReview (newReview) {

    // TODO ここでAPI通信しDBに登録する

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
