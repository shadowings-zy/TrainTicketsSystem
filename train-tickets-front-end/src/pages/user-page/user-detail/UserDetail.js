import React from 'react';
import './UserDetail.css';
import Button from '../../../components/button/Button';
import Tools from '../../../tools/Tools';

function UserDetail() {
  const user = JSON.parse(localStorage.getItem('user'));
  
  function userInformationChange(e) {
    const type = e.target.getAttribute('data-id');
    user[type] = e.target.value;
  }
  
  function updateUser() {
    console.log('update user');
    fetch('./updateUser',{
      method:'post',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: Tools.toPostPara(user)
    })
      .then(res=>res.json())
      .then(json=>{
        console.log('updateUser', json);
        alert('修改成功');
      })
  }
  
  return (
    <div className='user'>
      <input className='user-input' placeholder={'用户名 初始值为: ' + user.userName} data-id='username' onChange={userInformationChange}/>
      <input className='user-input' placeholder={'密码 初始值为: ' + user.password} data-id='password' onChange={userInformationChange}/>
      <input className='user-input' placeholder={'电话号码 初始值为: ' + user.telephone} data-id='telephone' onChange={userInformationChange}/>
      <input className='user-input' placeholder={'身份证号 初始值为: ' + user.idcard} data-id='idcard' onChange={userInformationChange}/>
      <div className='status-select'>
        <div className='status-select-text'> 选择用户状态： </div>
        <select className='status-select-content' data-id='status' defaultValue='正常' onChange={userInformationChange}>
          <option value ='正常'> 正常 </option>
          <option value ='限制购票'> 限制购票 </option>
        </select>
      </div>
      <Button class={'update-user'} text={'修改'} onClick={updateUser}/>
      <div className='tips'> 这里的修改仅作为功能演示，为了其他功能的正常运行，不应该对身份证号和用户名进行更改 </div>
    </div>
  );
}

export default UserDetail;
