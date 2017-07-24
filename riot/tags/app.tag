<app>
  <content></content>
  <script>
   import route from 'riot-route'

   import store from '../store.js'

   import './new-taste.tag'
   import './case-one.tag'
   
   route('/', () => riot.mount('content', 'new-taste', opts.data))
   route('/one', () => riot.mount('content', 'case-one'))
   route.start(true)

   store.on('from-store', (data) => {
     this.update(data)
   })
  </script>
</app>
