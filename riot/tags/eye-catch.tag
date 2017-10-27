import { redirect } from '../utils.js'

<eye-catch>
  <section onclick={ gotoTop }
           class="hero is-primary">
    <div class="hero-body">
      <div class="container">
        <h1 class="title">
          Whisky Taste
        </h1>
        <h2 class="subtitle">
          Tasting Reviews For You
        </h2>
      </div>
    </div>
  </section>
  <script>
   gotoTop () {
     redirect()
   }
  </script>
  <style>
   eye-catch {
     cursor: pointer;
   }
  </style>
</eye-catch>
