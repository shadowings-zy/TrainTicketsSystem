import React from 'react';
import Tools from '../../tools/Tools';
import './LoginRegister.css'
import Button from "../../components/button/Button";

const Register = () => {
  let user = {
    username: '',
    idcard: '',
    password: '',
    telephone: ''
  };
  
  const loginInformationChange = (e) => {
    const type = e.target.getAttribute('data-id');
    user[type] = e.target.value;
  };
  
  const register = () => {
    console.log('user register');
    fetch('./register',{
      method:'post',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: Tools.toPostPara(user)
    })
      .then(res=>res.json())
      .then(json => {
        console.log('register', json);
        if (json.statusCode === '1000') {
          localStorage.setItem("user", json.content);
          window.location.href = '#/train';
        } else {
          alert('出错啦')
        }
      })
  };
  
  const goLogin = () => {
    window.location.href = '#/login'
  };
  
  return (
    <div className='login'>
      <div className='login-title'> 数据库课设-12307火车售票-注册 </div>
      <input className='login-input' placeholder='用户名' data-id='username' onChange={loginInformationChange}/>
      <input className='login-input' placeholder='密码' data-id='password' onChange={loginInformationChange}/>
      <input className='login-input' placeholder='身份证号' data-id='idcard' onChange={loginInformationChange}/>
      <input className='login-input' placeholder='手机号' data-id='telephone' onChange={loginInformationChange}/>
      <Button class={'login-button'} onClick={register} text={'注册'}/>
      <div className='go-register' onClick={goLogin}> 点击此处返回注册页面 </div>
    </div>
  );
};

export default Register;
