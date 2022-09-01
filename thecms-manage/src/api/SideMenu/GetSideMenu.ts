import http from "../../utils/http";

import {message} from "antd";

export async function GetSideMenu(){
    let response = null
    await http.get("/api/getMenu").then(( value:any ) => {
        if (value.data.code === 200) {
            response = value.data.data
        } else {
            message.warn("请求错误")
        }

    })
    return response
}