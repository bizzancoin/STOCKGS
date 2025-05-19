var express = require('express');
var app = express();

app.use(express.static('./dist'));

app.listen(2333);
console.log('本地静态测试，历史模式路由刷新就会not get')
console.log('访问localhost:2333')
