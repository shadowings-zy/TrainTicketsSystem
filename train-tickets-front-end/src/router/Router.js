import React from 'react';
import {HashRouter, Route, Switch} from 'react-router-dom';
import Login from '../pages/login-register-page/Login';
import Register from '../pages/login-register-page/Register';
import Train from '../pages/train-page/train/Train';
import Order from '../pages/order-page/order/Order';
import User from '../pages/user-page/user/User';


const Router = () => (
  <HashRouter>
    <Switch>
      <Route exact path='/' component={Login}/>
      <Route exact path='/login' component={Login}/>
      <Route exact path='/register' component={Register}/>
      <Route exact path='/train' component={Train}/>
      <Route path='/order' component={Order}/>
      <Route path='/user' component={User}/>
    </Switch>
  </HashRouter>
);

export default Router;
