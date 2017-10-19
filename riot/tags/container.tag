<container>
  {this.data}
  <a href="/#/test"><p>test</p></a>
  <a href="/#/case2"><p>case2</p></a>
  <content-header say="hello" ></content-header>
  <content-body say="{this.data}" ></content-body>
  <script>
   const self = this
   
   this.on('before-mount', () => {
     self.data = 0
   })

   setInterval(() => {
     self.data = self.data + 1
     riot.update()
   }, 1000)
  </script>
</container>
