import './review-button.tag'
import './back-button.tag'

<tool-bar>
  <nav class="level">
    <!-- Left side -->
    <div class="level-left">
      <review-button store={ opts.store } />
    </div>

    <!-- Right side -->
    <div class="level-right">
      <back-button store={ opts.store } />
      <!-- 検索フィード
      <div class="field has-addons">
        <p class="control">
          <input class="input" type="text" placeholder="Find a post">
        </p>
        <p class="control">
          <button class="button">
            Search
          </button>
        </p>
      </div>
      -->
    </div>
  </nav>
</tool-bar>
