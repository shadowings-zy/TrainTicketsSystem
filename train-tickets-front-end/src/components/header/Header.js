import React from 'react';
import './Header.css';
import Button from "../button/Button";

const Header = () => {
  const quit = () => {
    window.location.href = '#/login'
  };
  
  return (
    <div className='header'>
      <div className='title'>12307-火车票售票系统</div>
      <Button class={'quit'} onClick={quit} text={'退出'}/>
    </div>
  );
};

export default Header;
