import React from 'react';
import './TrainSelect.css'
import Button from '../../../components/button/Button';
import Tools from '../../../tools/Tools';

const TrainSelectTitle = () => {
  return (
    <div className='train-select-detail'>
      <div className='train-select-item'> 火车号</div>
      <div className='train-select-item'> 发车时间 </div>
      <div className='train-select-item'> 票价 </div>
      <div className='train-select-item'> 一等座 </div>
      <div className='train-select-item'> 二等座 </div>
      <div className='train-select-item'>  </div>
      <div className='train-select-item'>  </div>
    </div>
  );
};

class TrainSelectDetail extends React.Component {
  constructor(props) {
    super(props);
    this.firstClassSeat = [];
    this.secondClassSeat = [];
    this.buyTickets = this.buyTickets.bind(this);
    this.state = {
      first: '',
      second: ''
    };
  }
  
  // 组件刚挂载时需要先获取到余票情况
  componentDidMount() {
    const para = {
      trainId: this.props.train.trainId,
      date: this.props.date,
      fromStopId: this.props.train.startIndex,
      toStopId: this.props.train.endIndex
    };
  
    fetch('./getRemainSeats', {
      method: 'post',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: Tools.toPostPara(para)
    })
      .then(res => res.json())
      .then(json => {
        console.log('getRemainSeats', json);
        if (json.statusCode === '1000') {
          this.firstClassSeat = [];
          this.secondClassSeat = [];
        
          const seats = JSON.parse(json.content);
        
          for (const item of seats) {
            if (item.seatType === '一等座') {
              this.firstClassSeat.push(item);
            } else if (item.seatType === '二等座') {
              this.secondClassSeat.push(item);
            }
          }
        
          this.setState({
            first: this.firstClassSeat.length,
            second: this.secondClassSeat.length
          });
        } else {
          alert('出错啦');
        }
      });
  }
  
  // 点击购买一等票
  buyFirstClassTickets = () => {
    this.buyTickets(this.firstClassSeat[0]);
  };
  
  // 点击购买二等票
  buySecondClassTickets = () => {
    this.buyTickets(this.secondClassSeat[0])
  };
  
  // 点击购票
  buyTickets = (seat) => {
    const user = JSON.parse(localStorage.getItem('user'));
    const train = this.props.train;
    const date = this.props.date;
    
    const para = {
      userId: user.userId,
      passengerId: user.idcard,
      userName: user.userName,
      trainId: train.trainId,
      trainName: train.trainName,
      carriage: seat.carriage,
      seatType: seat.seatType,
      seatId: seat.seatId,
      seatLocation: seat.seatLocation,
      startTime: train.startTime,
      startStopId: train.startIndex,
      startStationName: train.start,
      endStopId: train.endIndex,
      endStationName: train.end,
      date: date,
      createAt: '2018-12-31',
      status: '已付款'
    };
    console.log(Tools.toPostPara(para));
    
    fetch('./lockSeat', {
      method: 'post',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: Tools.toPostPara(para)
    })
      .then(res => res.json())
      .then(json => {
        console.log('lockSeat', json);
        if (json.statusCode === '1000') {
          const order = JSON.parse(json.content);
          alert('您已成功购买 '+ order.date + '的' + order.trainName + '次列车，您的座位是' + order.carriage + '车' + order.seatLocation);
          window.location.href = '#/order';
        } else {
          alert('出错啦');
        }
      });
  };
  
  render() {
    return (
      <div className='train-select-detail'>
        <div className='train-select-item'> {this.props.train.trainName} </div>
        <div className='train-select-item'> {this.props.train.startTime} </div>
        <div className='train-select-item'> {this.props.date} </div>
        <div className='train-select-item'> {this.state.first} </div>
        <div className='train-select-item'> {this.state.second} </div>
        <Button class={'train-tickets-button-default'} text={'一等座购票'} onClick={this.buyFirstClassTickets}/>
        <Button class={'train-tickets-button-default'} text={'二等座购票'} onClick={this.buySecondClassTickets}/>
      </div>
    );
  }
}

const TrainSelect = (props) => {
  // 遍历数组添加组件
  const listItems = props.trainList.map((item, index) =>
    <TrainSelectDetail key={index} train={item} date={props.date}/>
  );

  return (
    <div className='train-select'>
      <div className='train-select-title'> 查询结果： </div>
      <TrainSelectTitle/>
      <div> {listItems} </div>
    </div>
  );
};

export default TrainSelect;
