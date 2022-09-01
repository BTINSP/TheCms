import http from "../../utils/http";

import {message} from "antd";

function boolToIntForStatus(status: boolean | undefined){
    if (status){
        return 0
    }else {
        return 1
    }
}

//  添加文章
export async function AddArticle(
    title: String,
    columnId: number | undefined,
    status: boolean | undefined,
    sort: String | undefined,
    imagePath: String | undefined,
    content: any
) {
    await http.post("/api/addArticle",{
        "articleName":title,
        "parentId": columnId,
        "status": boolToIntForStatus(status),
        "sortRank":sort,
        "coverImg":imagePath,
        "content":content
    }).then((value:any) => {
        console.log(status)
        if (value.data.code === 200){
            message.success("添加成功")
        }else {
            message.warn("请求错误")
        }
    })
}

//  获取文章列表
export async function GetArticleList(){
    let response = null
    await http.get("/api/getArticleList?start="+ 0 + "&size=" + 10).then((value:any) => {
        if (value.data.code === 200){
            response = value.data.data
        }else {
            message.warn("请求错误")
        }
    })
    return response
}

//  删除文章 by id
export async function DeleteArticleById(id:number){
    await http.post("/api/deleteArticleById",{
        "key":id,
    }).then((value:any) => {
        if (value.data.code === 200){
            message.success("删除成功")
        }else {
            message.warn("请求错误")
        }
    })
}

export async function updateArticle(param:any){
    console.log(param)
    await http.post("/api/updateArticle",param).then((value:any) => {
        if (value.data.code === 200){
            message.success("更新成功")
        }else {
            message.warn("请求错误")
        }
    })
}