import riot from 'riot'
import route from 'riot-route'

import './tags/index.tag'
import './tags/case-one.tag'
import './tags/case-two.tag'
import './tags/underground.tag'

route('/', () => riot.mount('app', 'index'))
route('/one', () => riot.mount('app', 'case-one'))
route('/two', () => riot.mount('app', 'case-two'))
route('/two/underground', () => riot.mount('app', 'underground'))
// route('/two/underground/*', (id) => riot.mount('app', 'underground', {id})) // pass param sample

route.start(true)
