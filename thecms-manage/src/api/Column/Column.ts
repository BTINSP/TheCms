import http from "../../utils/http";

import {message} from "antd";

export async function GetColumn(){
    let response = null
    await http.get("/api/getColumn").then((value:any) => {
        if (value.data.code === 200){
            response = value.data.data
        }else {
            message.warn("请求错误")
        }
    })
    return response
}

export async function AddColumn(params:any): Promise<void>{
    await http.post("/api/addColumn", params).then((value:any) => {
        if (value.data.code === 200){
            message.success(value.data.msg)
        }else {
            message.error(value.data.msg)
        }
    })
}


export async function UpdateColumn(params:any): Promise<void>{
    await http.post("/api/updateColumn",params).then((value:any) => {
        if (value.data.code === 200){
            message.success(value.data.msg)
        }else {
            message.error(value.data.msg)
        }
    })

}



export async function DeleteColumn(params:any): Promise<void>{
    await http.post("/api/deleteColumn",params).then((value:any) => {
        if (value.data.code === 200){
            message.success(value.data.msg)
        }else {
            message.error(value.data.msg)
        }
    })

}

export async function ChangeColumnStatus(params:any): Promise<void>{
    await http.post("/api/changeColumnStatus",params).then((value:any) => {
        if (value.data.code === 200){
            message.success(value.data.msg)
        }else {
            message.error(value.data.msg)
        }
    })
}


