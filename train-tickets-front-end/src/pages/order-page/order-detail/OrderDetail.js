import React from 'react';
import './OrderDetail.css';
import Tools from '../../../tools/Tools';
import {OrderSelectTitle, OrderSelectDetail} from '../order-select/OrderSelect';

class OrderDetail extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      orderList: []
    }
  }
  
  // 组件刚挂载时需要先获取到余票情况
  componentDidMount() {
    const user = JSON.parse(localStorage.getItem("user"));
    const para = {
      idcard: user.idcard,
    };
    
    fetch('./queryOrder', {
      method: 'post',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: Tools.toPostPara(para)
    })
      .then(res => res.json())
      .then(json => {
        console.log('queryOrder', json);
        if (json.statusCode === '1000') {
          this.setState({
            orderList: JSON.parse(json.content)
          });
        } else {
          alert('出错啦');
        }
      });
  }
  
  render () {
    const orderItems = this.state.orderList.map((order, index) =>
      <OrderSelectDetail order={order} key={index}/>
    );
    
    return (
      <div className='order-detail'>
        <OrderSelectTitle/>
        {orderItems}
      </div>
    );
  }
}

export default OrderDetail;
