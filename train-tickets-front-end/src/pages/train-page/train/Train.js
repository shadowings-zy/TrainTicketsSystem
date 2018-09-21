import React from 'react';
import Header from '../../../components/header/Header';
import Navigator from '../../../components/navigator/Navigator';
import TrainDetail from '../train-detail/TrainDetail';
import './Train.css'

const Train = () => (
  <div className='homepage'>
    <Header/>
    <div className='content'>
      <Navigator/>
      <TrainDetail/>
    </div>
  </div>
);

export default Train;
