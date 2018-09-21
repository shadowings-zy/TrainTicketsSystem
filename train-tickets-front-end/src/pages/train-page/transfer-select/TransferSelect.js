import React from 'react';
import './TransferSelect.css';

const TransferSelectDetail = (props) => {
  return (
    <div className='transfer-select-detail'>
      <div className='transfer-select-item'> {props.transfer.firstTname} </div>
      <div className='transfer-select-item'> --> </div>
      <div className='transfer-select-item'> {props.transfer.transferStation} </div>
      <div className='transfer-select-item'> --> </div>
      <div className='transfer-select-item'> {props.transfer.secondTname} </div>
    </div>
  );
};

const TransferSelect = (props) => {
  // 遍历数组添加组件
  const listItems = props.transferList.map((item, index) =>
    <TransferSelectDetail key={index} transfer={item}/>
  );
  
  return (
    <div className='transfer-select'>
      <div className='transfer-select-title'> 换乘结果： </div>
      <div> {listItems} </div>
    </div>
  );
};


export default TransferSelect;
