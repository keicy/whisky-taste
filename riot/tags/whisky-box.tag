<whisky-box>
  <div class="box">
    <div class="title is-4">
      { whisky.whiskyName }
    </div>
    <div class="columns">
      <div class="column is-2">
        度数: { whisky.strength }％
      </div>
      <div class="column">
        蒸留所: { whisky.distilleryName }
      </div>
      <div class="column">
        原産: { whisky.country }
      </div>
      <div class="column">
        産地: { whisky.region }
      </div>
    </div>
  </div>

  <script>
   this.whisky = opts.whisky
  </script>
</whisky-box>
