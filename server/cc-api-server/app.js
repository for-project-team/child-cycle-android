/* expressJS Hello World 찍기 */
const express = require('express');
const mysql = require('mysql'); // mysql 모듈 생성
const app = express();
const bodyParser = require('body-parser');
const http = require('http');
const url = require('url');
const dateFormat = require('dateformat');
const kmaWeather = require('kma-js').Weather;

// bodyParser 미들웨어
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// 데이터베이스 연동
const client = mysql.createConnection({
  host: 'host ip',
  user: 'user id',
  password: 'password',
  database: 'database name'
});

client.connect(function(err) {
  if(err){
    console.error('error Connection' + err.stack);
  }
});

app.get('/', (req, res) => {
  res.send('Hello World!\n API Server Start');
});

app.get('/users', (req, res) => {
  client.query('select * from userInfo', function(error, result){
      if(!error){
        console.log(result);
        res.json(result);
      }else{
        console.log('Error Query');
        return res.status(404).json({error: 'No User'});
      }
  });
});

app.get('/users/:nickname', (req, res) => {

  const nickname = req.params.nickname;
  console.log(nickname);

  if(!nickname){
    return res.status(400).json({error: 'Invalid nickname'});
  }

  client.query('select * from userInfo where nickname = ?', nickname, function(error, result){
    if(!error){
      console.log(result);
      res.json(result);
    }else{
      console.log(error);
      return res.status(404).json({error: 'No User'});
    }
  });
});

app.get("/weather", (req, res) =>  {
	kmaWeather.townWeather('37.49543016888596', '127.03781811461468')
    .then(data => res.json(data));
});

app.post('/usercreate', (req, res) => {
  const user = {
    'name':req.body.name,
    'gender':parseInt(req.body.gender),
    'weight':parseInt(req.body.weight),
    'height':parseInt(req.body.height),
    'nickname':req.body.nickname,
    'birth':parseInt(req.body.birth),
    'photo':req.body.photo
  };

  if(!user){
    return res.status(400).json({error: 'Invalid Data'});
  }

  client.query('insert into userInfo set ?', user, function(error, result){
    console.log(user)
    if(!error){
      console.log(result);
      res.status(201).json(result);
    }else{
      console.log(error);
      return res.status(404).json({error: 'Error Data'});
    }
  });
});

app.delete('/:nickname', (req, res) => {

  const nickname = req.params.nickname;
  if(!nickname){
      return res.status(400).json({error: 'Invalid nickname'});
  }

  client.query('delete from userInfo where nickname = ?', nickname, function(error, result){
    if(!error){
      console.log(result);
      res.status(201).json(result);
    }else{
      console.log(error);
      return res.status(404).json({error: 'Error Data'});
    }
  });
});

/* 사용자 별 라이딩 데이터 보기 (개략) */
// select r.date, r.ridingTime, r.totalDistance from ridingData r, userInfo u where r.nickname = ? AND u.nickname = ?
app.get('/ridingrecord/:nickname', (req, res) => {
  const nickname = req.params.nickname;
  console.log(nickname);

  if(!nickname){
    return res.status(400).json({error: 'Invalid nickname'});
  }

  // 여러개의 파라미터를 보낼 때는 배열 형태로 보낸다
  client.query('select date, ridingTime, totalDistance from ridingData where nickname = ?', nickname, function(error, result){
    console.log(error);
    if(!error){
      console.log(result);
      res.json(result);
    }else{
      console.log(error);
      console.log('Error Query');
      return res.status(404),json({error: 'No Riding Data'});
    }
  });
});

app.get('/ridingrecord', (req, res) => {
  client.query('select date, ridingTime, totalDistance from ridingData', function(error, result){
    console.log(error);
    if(!error){
      console.log(result);
      res.json(result);
    }else{
      console.log(error);
      console.log('Error Query');
      return res.status(404),json({error: 'No Riding Data'});
    }
  });
});

/*  라이딩 데이터 저장 */
app.post('/ridingdata', (req, res) => {
  const riding = {
    'date':dateFormat(new Date(), "yyyy-mm-dd,h:MM:ssTT"),
    'totalDistance':req.body.totalDistance,
    'avgVelocity':req.body.avgVelocity,
    'calorie':req.body.calorie,
    'ridingTime':req.body.ridingTime,
    'safetyCnt':parseInt(req.body.safetyCnt),
    'warningCnt':parseInt(req.body.warningCnt),
    'nickname':req.body.nickname
  };

  if(!riding){
    return res.status(400).json({error: 'Invalid Data'});
  }

  client.query('insert into ridingData set ?', riding, function(error, result){
    console.log(riding)
    if(!error){
      console.log(result);
      res.status(201).json(result);
    }else{
      console.log(error);
      return res.status(404).json({error: 'Error Riding Data'});
    }
  });
});

app.listen(3000, () => {
   console.log('Example app listening on port 3000!');
});

//module.exports = app;
