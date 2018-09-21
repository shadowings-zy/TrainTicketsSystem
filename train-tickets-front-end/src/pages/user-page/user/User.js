import React from 'react';
import Header from '../../../components/header/Header';
import Navigator from '../../../components/navigator/Navigator';
import UserDetail from '../user-detail/UserDetail';
import './User.css'

const User = () => (
  <div className='homepage'>
    <Header/>
    <div className='content'>
      <Navigator/>
      <UserDetail/>
    </div>
  </div>
);

export default User;
