import React from 'react';
import Header from '../../../components/header/Header';
import Navigator from '../../../components/navigator/Navigator';
import './Order.css'
import OrderDetail from '../order-detail/OrderDetail';

const Order = () => (
  <div className='homepage'>
    <Header/>
    <div className='content'>
      <Navigator/>
      <OrderDetail/>
    </div>
  </div>
);

export default Order;
