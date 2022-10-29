import axios from 'axios'
import router from "../router";
import {getToken} from "@/utils/auth";

const request = axios.create({
    baseURL: "/api",
    timeout: 5000
})

// 请求白名单，如果请求在白名单里面，将不会被拦截校验权限
const whiteUrls = ["/user/login","/user/adminLogin","/guest/index","/guest/getFoodList","/info/getInitViewDrug","/info/getNewsById"]
// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    let result = config.url.split("/");
    let data="";
    if (result.length >= 3) {
        data="/"+result[1]+"/"+result[2];
    }
    console.log(data);
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let token = getToken();
    if (token != null) {
        if (!whiteUrls.includes(config.url)) {  // 校验请求白名单
            config.headers['token'] = token;
        }
    }else if(whiteUrls.includes(config.url)){
        return config;
    }else if(whiteUrls.includes(data)){
        return config;
    }
    else {
        router.push("/guest/index")
    }
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request

