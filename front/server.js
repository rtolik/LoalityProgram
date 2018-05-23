var express  = require('express');
var app      = express();
app.use(express.static(__dirname + '/dist'));
app.listen(8080);
app.listen(() => {
  console.log(`Node server listening on http://localhost:${8080}`);
});

