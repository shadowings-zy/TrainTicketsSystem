import React from 'react';
import './CitySelect.css';
import Button from '../../../components/button/Button';
import Tools from "../../../tools/Tools";

class CitySelect extends React.Component {
  constructor (props) {
    super(props);
    this.city = ['沈阳', '济南', '深圳', '北京', '上海', '广州', '长春'];
    this.date = ['2019-01-01', '2019-01-02', '2019-01-03'];
    this.fromSelect = 0;
    this.toSelect = 0;
    this.dateSelect = 0;
    this.fromChange = this.fromChange.bind(this);
    this.toChange = this.toChange.bind(this);
    this.dateChange = this.dateChange.bind(this);
    this.queryTrain = this.queryTrain.bind(this);
  }
  
  fromChange = (e) => {
    this.fromSelect = e.target.value;
  };
  
  toChange = (e) => {
    this.toSelect = e.target.value;
  };
  
  dateChange = (e) => {
    this.dateSelect = e.target.value;
  };
  
  queryTrain = () => {
    const para = {
      fromCity: this.city[this.fromSelect],
      toCity: this.city[this.toSelect],
      date: this.date[this.dateSelect]
    };
  
    if (this.fromSelect !== this.toSelect) {
      this.props.setTrainList([]);
      
      // 请求查询接口
      fetch('./getTrainByStop',{
        method:'post',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: Tools.toPostPara(para)
      })
        .then(res => res.json())
        .then(json => {
          console.log('getTrainByStop', json);
          if (json.statusCode === '1000') {
            const outputList = JSON.parse(json.content);
            this.props.setTrainList(outputList, this.date[this.dateSelect]);
            
            if (outputList.length === 0) {
              alert('无查询结果');
            }
          } else {
            alert('出错啦');
          }
        });
    } else {
      alert('不可以选择同一个地点');
    }
  };
  
  queryTransfer = () => {
    const para = {
      fromCity: this.city[this.fromSelect],
      toCity: this.city[this.toSelect],
      date: this.date[this.dateSelect]
    };
  
    if (this.fromSelect !== this.toSelect) {
      this.props.setTrainList([]);
  
      // 请求换乘接口
      fetch('./getTransfer', {
        method: 'post',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: Tools.toPostPara(para)
      })
        .then(res => res.json())
        .then(json => {
          console.log('getTransfer', json);
          if (json.statusCode === '1000') {
            const outputList = JSON.parse(json.content);
            this.props.setTransferList(outputList);
  
            if (outputList.length === 0) {
              alert('无查询结果');
            }
          } else {
            alert('出错啦');
          }
        });
    } else {
      alert('不可以选择同一个地点');
    }
  };
  
  render() {
    return (
      <div className='city-select'>
        <select className='city-selector' defaultValue='0' onChange={this.fromChange}>
          <option value ='0'> {this.city[0]} </option>
          <option value ='1'> {this.city[1]} </option>
          <option value ='2'> {this.city[2]} </option>
          <option value ='3'> {this.city[3]} </option>
          <option value ='4'> {this.city[4]} </option>
          <option value ='5'> {this.city[5]} </option>
          <option value ='6'> {this.city[6]} </option>
        </select>
        <div className='city-selector-content'> 至 </div>
        <select className='city-selector' defaultValue='0' onChange={this.toChange}>
          <option value ='0'> {this.city[0]} </option>
          <option value ='1'> {this.city[1]} </option>
          <option value ='2'> {this.city[2]} </option>
          <option value ='3'> {this.city[3]} </option>
          <option value ='4'> {this.city[4]} </option>
          <option value ='5'> {this.city[5]} </option>
          <option value ='6'> {this.city[6]} </option>
        </select>
        <select className='city-selector' defaultValue='0' onChange={this.dateChange}>
          <option value ='0'> {this.date[0]} </option>
          <option value ='1'> {this.date[1]} </option>
          <option value ='2'> {this.date[2]} </option>
        </select>
        <Button class={'train-tickets-button-default'} text={'查询'} onClick={this.queryTrain}/>
        <Button class={'train-tickets-button-default'} text={'换乘查询'} onClick={this.queryTransfer}/>
      </div>
    );
  }
}

export default CitySelect;
