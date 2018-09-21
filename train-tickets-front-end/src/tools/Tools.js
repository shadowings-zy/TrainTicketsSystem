// 将对象转换为post请求参数的方法
const Tools = {
  toPostPara: (obj) => {
    let output = '';
    for (const item in obj) {
      if (obj.hasOwnProperty(item)) {
        output = output + item + '=' + obj[item] + '&'
      }
    }
    return output.substring(0, output.length - 1);
  }
};

export default Tools;
