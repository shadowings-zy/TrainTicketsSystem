import React from 'react';
import Tools from '../../tools/Tools'
import './LoginRegister.css'
import Button from "../../components/button/Button";

const Login = () => {
  let user = {
    username: '',
    password: ''
  };
  
  const loginInformationChange = (e) => {
    const type = e.target.getAttribute('data-id');
    user[type] = e.target.value;
  };
  
  const login = () => {
    console.log('user login');
    fetch('./login',{
      method:'post',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: Tools.toPostPara(user)
    })
      .then(res=>res.json())
      .then(json => {
        console.log('login', json);
        if (json.statusCode === '1000') {
          localStorage.setItem("user", json.content);
          window.location.href = '#/train';
        } else if (json.statusCode === '1001') {
          alert('您被限制购票');
        } else {
          alert('您输入的账号密码不正确')
        }
      })
  };
  
  const goRegister = () => {
    window.location.href = '#/register'
  };
  
  return (
    <div className='login'>
      <div className='login-title'> 数据库课设-12307火车售票-登录 </div>
      <input className='login-input' placeholder='用户名' data-id='username' onChange={loginInformationChange}/>
      <input className='login-input' placeholder='密码' data-id='password' onChange={loginInformationChange}/>
      <Button class={'login-button'} onClick={login} text={'登录'}/>
      <div className='go-register' onClick={goRegister}> 点击此处注册新用户 </div>
    </div>
  );
};

export default Login;
