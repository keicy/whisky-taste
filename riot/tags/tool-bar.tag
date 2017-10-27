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
           // store.data.searchWhiskyを設定、一覧側でこれに絞り込みで良い。
           // ボタン押下がめんどくさいのでインクリメンタルサーチで
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
  <style>
   .level {
     margin-bottom: 0.5em;
   }

   .button {
     border-top: none;
     border-radius: 0 0 5px 5px;
   }
  </style>
</tool-bar>
