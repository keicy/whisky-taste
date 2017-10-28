import route from 'riot-route'

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
     route('/')
   }
  </script>
  <style>
   eye-catch {
     cursor: pointer;
   }

   @media (max-width: 768px) {
     .hero-body {
       padding: 1rem 1.5rem;
     }
   }
  </style>
</eye-catch>
