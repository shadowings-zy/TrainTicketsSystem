import Button from '../../../components/button/Button';
import React from 'react';
import './OrderSelect.css';
import Tools from "../../../tools/Tools";

const OrderSelectTitle = () => {
  return (
    <div className='order-select-detail'>
      <div className='order-select-item'> 火车号 </div>
      <div className='order-select-item'> 发车日 </div>
      <div className='order-select-item'> 发车时间 </div>
      <div className='order-select-item'> 起止地点 </div>
      <div className='order-select-item'> 车厢 </div>
      <div className='order-select-item'> 座位号 </div>
      <div className='order-select-item'>  </div>
    </div>
  );
};

const OrderSelectDetail = (props) => {
  const deleteOrder = () => {
    const para = {
      orderId: props.order.orderId,
    };
  
    fetch('./deleteOrder', {
      method: 'post',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: Tools.toPostPara(para)
    })
      .then(res => res.json())
      .then(json => {
        console.log('deleteOrder', json);
        if (json.statusCode === '1000') {
          alert('删除成功');
          window.location.href = '#/train';
        } else {
          alert('出错啦');
        }
      });
  };
  
  return (
    <div className='order-select-detail'>
      <div className='order-select-item'> {props.order.trainName} </div>
      <div className='order-select-item'> {props.order.date} </div>
      <div className='order-select-item'> {props.order.startTime} </div>
      <div className='order-select-item'> {props.order.startStationName + '-' + props.order.endStationName} </div>
      <div className='order-select-item'> {props.order.carriage} </div>
      <div className='order-select-item'> {props.order.seatLocation} </div>
      <Button class={'train-tickets-button-default'} text={'退票'} onClick={deleteOrder}/>
    </div>
  );
};

export {
  OrderSelectDetail,
  OrderSelectTitle
};
