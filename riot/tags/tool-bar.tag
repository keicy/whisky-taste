import './review-button.tag'
import './whisky-search.tag'
import './back-button.tag'

<tool-bar>
  <nav>
    <!-- 左側 -->
    <div>
      <review-button store={ opts.store } />
    </div>

    <!-- 右側 -->
    <div>
      <whisky-search store={ opts.store } />
      <back-button store={ opts.store } />
    </div>
  </nav>
  <style>
   nav {
     margin-bottom: 0.5em;
     display: flex;
     justify-content: space-between;
   }

   .button, .input {
     border-top: none;
     border-radius: 0 0 5px 5px;
   }

   @media (max-width: 768px) {
     nav {
       margin-bottom: 1em;
     }
   }
  </style>
</tool-bar>
