import axios from "axios";//要先安装axios, npm i axios -s

const request =axios.create({
    baseURL:'http://localhost:8080/ikun',//和后端的端口一致
    timeout:5000
})

//request拦截器
//可以自请求发送前对请求做一些处理
//比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

     // 获取localStorage中的admin、doctor和user的token，并添加到请求头
  const admin = localStorage.getItem("admin");
  const doctor = localStorage.getItem("doctor");
  const user = localStorage.getItem("user");

  if (admin && typeof admin === 'string') {
    try {
      const parsedAdmin = JSON.parse(admin);
      config.headers['token'] = parsedAdmin.token;
    } catch (e) {
      console.error("Failed to parse admin data from localStorage", e);
    }
  }

  if (doctor && typeof doctor === 'string') {
    try {
      const parsedDoctor = JSON.parse(doctor);
      config.headers['token'] = parsedDoctor.token;
    } catch (e) {
      console.error("Failed to parse doctor data from localStorage", e);
    }
  }

  if (user && typeof user === 'string') {
    try {
      const parsedUser = JSON.parse(user);
      config.headers['token'] = parsedUser.token;
    } catch (e) {
      console.error("Failed to parse user data from localStorage", e);
    }
  }



    return config
},error => {
    return Promise.reject(error)
});

request.interceptors.response.use(
    response=>{

        //后端的Result
        let res =response.data;
        //兼容后端返回的字符串数据
        if(typeof res === 'string'){
            res =res?JSON.parse(res):res
        }
        return res;
    },
    error => {
        console.log('err'+error)
        return Promise.reject(error)
    }
)

export default request