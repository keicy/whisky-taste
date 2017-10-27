import './eye-catch.tag'
import './tool-bar.tag'

<app-layout>
  <eye-catch />
  <!-- メインコンテンツ -->
  <section class="columns">
    <div class="column is-offset-2 is-8 is-offset-2">
      <tool-bar store={ opts.store } />
      <div id="wrapper">
        <content />
      </div>
    </div>
  </section>

  <style>
   @media (max-width: 768px) {
     #wrapper {
       margin: 0 0.3em;
     }
   }
  </style>
</app-layout>
