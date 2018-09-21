# 数据库课程设计——火车票售票系统
很烦数据库课程设计？本项目应该能够帮到你~<br>
线上访问地址：[http://www.shadowingszy.top/train-tickets/index.html](http://www.shadowingszy.top/train-tickets/index.html)
<br>
<br>
train-tickets-back-end为后台工程文件。<br>
train-tickets-front-end为前端工程文件。<br>
<br>

## 系统开发平台：
开发工具：eclipse，webstorm<br>
开发语言：Java，jsp，css，JavaScript<br>
数据库：MySQL<br>
中间件：tomcat 8.0<br>
后台框架：SpringMVC<br>
前端框架：React（前端只是做了一个展示，不是很重要）<br>
<br>

## 系统功能：
在本系统中，前端和后台使用json进行数据交互，下面的每一个功能均对应着一个接口，括号中为接口的URL，具体实现可以参考源代码。

##### 用户层面：
添加用户，即注册。(/register)<br>
根据用户名和密码获取用户的所有信息，即登录。 (/login) <br>
修改用户。 (/updateUser)<br>

##### 订单层面：
添加订单，在本系统中添加订单就意味着锁定座位。(/lockSeat)<br>
修改订单。(/updateOrder)<br>
删除订单。(/deleteOrder)<br>
根据用户ID获取订单信息。(/queryOrder)<br>

##### 火车层面：
根据车站获取列车ID。(/getTrainByStop)<br>
根据列车ID获取列车信息。(/getTrainByTrainId)<br>
列车换乘查询。(/getTransfer)<br>
查询列车座位剩余。(/getRemainSeats)<br>
查询所有通列车的城市。(/getAllCities)<br>
<br>

## 部署须知：
前端页面的开发需要安装node.js<br>
之后进入train-tickets-front-end文件夹，输入npm install, 然后输入npm run start<br>
访问localhost:3000即可进行开发。<br>
<br>
前端页面部署需要先输入npm run build打包前端页面。<br>
然后将打包出的build文件夹中的全部文件放到train-tickets-back-end/WebContent文件夹中。<br>
<br>
最后进行后台的部署，将train-tickets-back-end项目打包成war文件后，直接丢到tomcat里。<br>
向localhost:8080/TrainTickets/xxx (xxx为对应的接口)发起post请求就可以了。<br>

