import './eye-catch.tag'
import './tool-bar.tag'

<app-layout>
  <section>
    <eye-catch />
  </section>
  <!-- メインコンテンツ -->
  <section class="columns">
    <div class="column is-offset-2 is-8 is-offset-2">
      <tool-bar store={ opts.store } />

      <content></content>

      <post-form></post-form>
      <item-list></item-list>
    </div>
  </section>
</app-layout>
