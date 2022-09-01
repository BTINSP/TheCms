import {message} from "antd";
import axios from "axios";


import { createBrowserHistory } from 'history';
const history = createBrowserHistory();




const http = axios.create({
    timeout: 5000
})

http.interceptors.request.use(requests=>{
    return requests
}, error => {
    message.error(error)
    return error
})


http.interceptors.response.use((response:any) =>{
    if (response.status === 200) {
        return response
    }
    message.error(response)
    console.log(response)
},error => {
    if (error.response.status === 403){
        message.warn("请先登录")
        history.push("/manage/login")
    }
    return error
})

export default http
