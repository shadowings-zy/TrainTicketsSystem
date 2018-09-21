import React from 'react';
import CitySelect from '../city-select/CitySelect';
import TrainSelect from '../train-select/TrainSelect';
import TransferSelect from '../transfer-select/TransferSelect';

class TrainDetail extends React.Component {
  constructor (props) {
    super(props);
    this.state = {trainList: [], transferList: [], date: ''};
    this.setTrainList = this.setTrainList.bind(this);
  }
  
  // CitySelect组件传回值的方法
  setTrainList = (trainList, date) => {
    this.setState({
      trainList: trainList,
      date: date,
      transferList: []
    });
  };
  
  // CitySelect组件传回值的方法
  setTransferList = (transferList) => {
    this.setState({
      trainList: [],
      date: '',
      transferList: transferList
    });
  };
  
  render () {
    let trainSelectComponent = null;
    let transferSelectComponent = null;
    if (this.state.trainList.length > 0) {
      trainSelectComponent = <TrainSelect trainList={this.state.trainList} date={this.state.date}/>;
    }
    if (this.state.transferList.length > 0) {
      transferSelectComponent = <TransferSelect transferList={this.state.transferList}/>;
    }
    return (
      <div className='train'>
        <CitySelect setTrainList={this.setTrainList} setTransferList={this.setTransferList}/>
        {trainSelectComponent}
        {transferSelectComponent}
      </div>
    );
  }
}

export default TrainDetail;
