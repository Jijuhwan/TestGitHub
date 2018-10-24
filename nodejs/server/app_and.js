const express = require('express');
const app = express();

let users = [

  {

    id: 1,

    name: 'WOW'

  },

  {

    id: 2,

    name: 'GOOD'

  },

  {

    id: 3,

    name: 'YEAH'

  }

]

app.get('/users', (req, res) => {

   console.log('who get in here/users');

   res.json(users)

});

app.post('/post', (req, res) => {

   console.log('who get in here post /users');

   var inputData;

   req.on('data', (data) => {

     inputData = JSON.parse(data);

   });

   req.on('end', () => {

     console.log("user_id : " + inputData.user_id + " , name : " + inputData.name);

   });

   res.write("ok!");

   res.end();

});

const hostname = 'localhost';
const port = 3000;

app.listen(port, hostname, () => {
  console.log('Server Connected');
});
module.exports = app;