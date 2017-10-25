import { redirect } from '../utils.js'

<eye-catch>
  <section onclick={ gotoTop }
           class="hero is-primary">
    <div class="hero-body">
      <div class="container">
        <h1 class="title">
          Primary title
        </h1>
        <h2 class="subtitle">
          Primary subtitle
        </h2>
      </div>
    </div>
  </section>
  <script>
   gotoTop () {
     redirect()
   }
  </script>
</eye-catch>
