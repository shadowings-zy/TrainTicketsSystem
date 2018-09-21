import React from 'react';
import './Button.css';

const Button = (props) => {
  return (
    <div className={props.class} onClick={props.onClick}> {props.text} </div>
  );
};

export default Button;
