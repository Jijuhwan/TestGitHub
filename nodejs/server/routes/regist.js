var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var urlencodedParser = bodyParser.urlencoded({ extended: false })
var jsonParser = bodyParser.json();

/* regist */
/* id, password, month, height, weight, gender*/
router.post('/', jsonParser, urlencodedParser, function(req, res) {
  if (!req.body)
  { 
    return res.sendStatus(400);
  }
  var id = req.body.id;
  var pwd = req.body.pwd;
  var month = req.body.month;
  var height = req.body.height;
  var weight = req.body.weight;
  var gender = req.body.gender;

  res.send(id +'\n' +pwd + '\n' + month + 
  '\n'+ height + '\n' + weight + '\n' + gender);

  console.log(id +'\n' +pwd + '\n' + month + 
  '\n'+ height + '\n' + weight + '\n' + gender);
});

module.exports = router;