import Action from './constants/action.js'
import ac from './action-creator.js'

const data = {
  reviews: [],
}

// TODO 可変長引数対応 `...data`
function setActionHandler (action, updateFn) {
  ac.on(action, data => {
    updateFn(data)
  })
}

setActionHandler(Action.POST_NEW_REVIEW, newReview => {
  data.reviews.push(newReview)
})

export default data
