import React from 'react';
import './Navigator.css';

function Navigator() {
  function clickNavigator(e) {
    const type = e.target.getAttribute('data-id');
    switch (type) {
      case '车票':
        window.location.href='#/train';
        break;
      case '订单':
        window.location.href='#/order';
        break;
      case '我的':
        window.location.href='#/user';
        break;
      default:
        break;
    }
  }
  return (
    <div className='navigator' onClick={clickNavigator}>
      <div className='navigator-content' data-id='车票'> 车票 </div>
      <div className='navigator-content' data-id='订单'> 订单 </div>
      <div className='navigator-content' data-id='我的'> 我的 </div>
    </div>
  );
}

export default Navigator;
