//  component
import {AddArticle, DeleteArticleById, GetArticleList, updateArticle} from "../../../api/Article/Article"
import {GetColumn} from "../../../api/Column/Column"

//  react
import {useEffect, useState} from "react"

//  antd
import {Button, Form, message, Modal, Select, Switch, Table, Upload, InputNumber } from 'antd';
import type {ColumnsType} from 'antd/es/table';
import {LoadingOutlined, PlusOutlined} from '@ant-design/icons';
import type {RcFile, UploadFile, UploadProps} from 'antd/es/upload/interface';
import type {UploadChangeParam} from 'antd/es/upload';

import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css'
import {ContentState, convertToRaw, EditorState} from 'draft-js';
import {Editor} from 'react-draft-wysiwyg';
import draftToHtml from 'draftjs-to-html';
import htmlToDraft from "html-to-draftjs";

interface ColumnDataType {
    key: string,
    articleName: string,
    status: number,
    sortRank: number,
    columnName: string,
    edit:any[]
}



function Article():any{
    const columns: ColumnsType<ColumnDataType> = [
        {
            title: 'Id',
            dataIndex: 'key',
            key: 'id',
            width: '5%',
            align: 'center'
        },
        {
            title: '文章名称',
            dataIndex: 'articleName',
            key: 'articleName',
            width: '45%',
        },
        {
            title: '状态',
            dataIndex: 'status',
            key: 'status',
            width: '5%',
            align: 'center',
            render:(text:any, record:any) => {
                let bool = false
                if (record.status === 0){
                    bool = true
                }
                return(
                    <Switch checkedChildren="开启" unCheckedChildren="关闭" defaultChecked={bool}  />
                )
            }
        },
        {
            title: '优先级',
            dataIndex: 'sortRank',
            key: 'sortRank',
            width: '5%',
            align: 'center'
        },
        {
            title: '所属栏目',
            dataIndex: 'parentId',
            key: 'parentId',
            width: '15%',
            align: 'center'
        },
        {
            title: '操作',
            dataIndex: 'edit',
            key: 'edit',
            render:(text:any, record:any) =>{
                return(
                    <div>
                        <Button type="primary" style={{marginRight: 10}} onClick={()=>{
                            ChangeArticleShow(record)
                        }}>
                            修改
                        </Button>
                        <Button type="primary" danger onClick={()=>{
                            console.log(record)
                            DeleteArticleById(record.key).then(r => {console.log(r)})
                            //  刷新界面
                            setUpdate(!update)
                        }}>
                            删除
                        </Button>
                    </div>
                )
            }
        },
    ];

    const [update, setUpdate] = useState<boolean>()

    //  modal
    const [addArticleModal, setAddArticleModal] = useState(false);
    const [changeArticleModal, setChangeArticleModal] = useState(false);

    //  图片上传
    const [uploadImagePath,setUploadImagePath] = useState(String)
    const [loading, setLoading] = useState(false);
    const [imageUrl, setImageUrl] = useState<string>();

    //  富文本编辑器
    const [editorState, setEditorState] = useState(
        () => EditorState.createEmpty(),
    );
    const [changeEditorState, setChangeEditorState] = useState(
        () => EditorState.createEmpty(),
    );

    //  获取栏目列表
    const [columnList,setColumnList] = useState<any>([])
    //  获取文章列表
    const [articleList, setArticleList] = useState<any>()

    //  文章标题input
    const [articleTitle, setArticleTitle] = useState(String)
    //  优先级 input
    const [articleSort, setArticleSort] = useState<String>()
    //  文章状态开关 switch
    const [articleStatus, setArticleStatus] = useState<boolean>()
    //  select 选择栏
    const [clickSelect,setClickSelect] = useState<number>()


    // antd form hook
    const [addArticleForm] = Form.useForm()
    const [changeArticleForm] = Form.useForm()

    const { Option } = Select;

    useEffect(()=>{
        GetArticleList().then((value:any)=>{
            setArticleList(value)
        })
    },[update])

    function setStatus(status:any){
        if (status){
            return 0
        }else {
            return 1
        }
    }


    function AddArticleShow(){
        GetColumn().then(value => {
            setColumnList(value)
        })
        setAddArticleModal(true)
    }

    function AddArticleOK(){

        //  表单数据验证完成
        addArticleForm.validateFields().then((value:any)=> {
            console.log(value)
            let param = {
                "articleName": value.articleName,
                "sortRank": value.sortRank,
                "coverImg": imageUrl,
                "status": value.status,
                "parentId": clickSelect,
                "content": draftToHtml(convertToRaw(editorState.getCurrentContent()))
            }
            AddArticle(param.articleName,param.parentId,param.status,param.sortRank,param.coverImg,param.content)
                .then((value:any)=>{
                //  关闭modal
                setAddArticleModal(false)

                //  刷新界面
                setUpdate(!update)
            })
        })

    }

    // html to draft
    function htmlDraft(content:any){
        const contentBlock = htmlToDraft(content);
        if (contentBlock) {
            const contentState = ContentState.createFromBlockArray(contentBlock.contentBlocks);
            const editorState = EditorState.createWithContent(contentState);
            setChangeEditorState(editorState)
            return editorState
        }
    }


    function ChangeArticleShow(record:any){
        GetColumn().then(value => {
            setColumnList(value)
        })
        setChangeArticleModal(true)
        console.log(record)
        changeArticleForm.setFieldsValue({
            "key": record.key,
            "articleName": record.articleName,
            "sortRank": record.sortRank,
            "coverImg": setImageUrl(record.coverImg),
            "status": setStatus(record.status),
            "parentId": record.parentId,
            "content": htmlDraft(record.content)
        })
    }


    function ChangeArticleOK(){
        console.log("change")
        //  表单数据验证完成
        changeArticleForm.validateFields().then((value:any)=>{
            let param = {
                "key": value.key,
                "articleName": value.articleName,
                "sortRank": value.sortRank,
                "coverImg": uploadImagePath,
                "status": setStatus(value.status),
                "parentId": value.parentId,
                "content": draftToHtml(convertToRaw(changeEditorState.getCurrentContent()))
            }
            updateArticle(param)

            setChangeArticleModal(false)
            //  刷新界面
            setUpdate(!update)
        })
    }


    //  图片上传
    const getBase64 = (img: RcFile, callback: (url: string) => void) => {
        const reader = new FileReader();
        reader.addEventListener('load', () => callback(reader.result as string));
        reader.readAsDataURL(img);
    };

    const beforeUpload = (file: RcFile) => {
        const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!isJpgOrPng) {
            message.error('You can only upload JPG/PNG file!');
        }
        const isLt2M = file.size / 1024 / 1024 < 100;
        if (!isLt2M) {
            message.error('Image must smaller than 2MB!');
        }
        return isJpgOrPng && isLt2M;
    };

    const uploadButton = (
        <div>
            {loading ? <LoadingOutlined /> : <PlusOutlined />}
            <div style={{ marginTop: 8 }}>Upload</div>
        </div>
    );

    const handleChange: UploadProps['onChange'] = (info: UploadChangeParam<UploadFile>) => {
        setUploadImagePath("")
        if (info.file.status === 'uploading') {
            setLoading(true);
            return;
        }
        if (info.file.status === 'done') {
            // Get this url from response in real world.
            getBase64(info.file.originFileObj as RcFile, url => {
                setLoading(false);
                setImageUrl(url);
            });
        }
        //  设置返回图片链接地址
        setUploadImagePath(info.file.response["data"]["path"])
    };




    return(
        <div>

            <Button
                type="primary"
                onClick={AddArticleShow}
                style={{
                    marginBottom: 16,
                }}>添加文章</Button>


            <Table columns={columns} dataSource={articleList} pagination={{
                current: 1,
                pageSize: 10,
                total: 5,
            }}/>

            <Modal title="添加文章" visible={addArticleModal}
                   width={1000}
                   onOk={AddArticleOK}
                   onCancel={()=>{
                       setAddArticleModal(false)
                   }
                }
            >
                <Form
                    name="add"
                    form={addArticleForm}
                    labelCol={{ span: 8 }}
                    wrapperCol={{ span: 16 }}
                    initialValues={{ remember: true }}
                    autoComplete="off"
                >
                    <Form.Item
                        label="文章名称"
                        name="articleName"
                        rules={[{
                            required: true,
                            message: "请输入文章名称"
                        }]}
                        style={{
                            width: "30%"
                        }}
                    >
                        <input />
                    </Form.Item>

                    <Form.Item
                        label="优先级"
                        name="sortRank"
                        rules={[{
                            required: true,
                            message: "请输入文章名称"
                        }]}
                        style={{
                            width: "30%"
                        }}
                    >
                        <InputNumber />
                    </Form.Item>

                    <Form.Item
                        label="状态"
                        name="status"
                        valuePropName="checked"
                        rules={[{
                            required: false,
                        }]}
                        style={{
                            width: "30%"
                        }}
                    >
                        <Switch checkedChildren="开启" unCheckedChildren="关闭"  />
                    </Form.Item>

                    <Form.Item
                        label="栏目"
                        name="parentId"
                        rules={[{
                            required: true,
                            message: "请输入文章名称"
                        }]}
                        style={{
                            width: "30%"
                        }}
                    >
                        <Select style={{ width: 120, marginBottom: 10 }} onChange={(value: any, event:any)=>{
                            setClickSelect(event.key)
                        }}>
                            {columnList.map((item:any)=>{
                                return(
                                    <Option value={item.columnName} key={item.key}>{item.columnName}</Option>
                                )
                            })}
                        </Select>
                    </Form.Item>


                    <Form.Item
                        label="封面图"
                        name="coverImg"
                        valuePropName="fileList"
                        style={{
                            width: "30%"
                        }}
                    >
                        <Upload
                            name="avatar"
                            listType="picture-card"
                            className="avatar-uploader"
                            showUploadList={false}
                            action="/api/upload"
                            beforeUpload={beforeUpload}
                            onChange={handleChange}
                        >
                            {imageUrl ? <img src={imageUrl} alt="avatar" style={{ width: '100%' }} /> : uploadButton}
                        </Upload>
                    </Form.Item>

                    <Form.Item
                        name="content"
                    >
                        <Editor
                            editorState={editorState}
                            wrapperClassName="demo-wrapper"
                            editorClassName="demo-editor"
                            onEditorStateChange={setEditorState}
                            editorStyle={{
                                minHeight: 300
                            }}
                            toolbar={{
                                image: {
                                    urlEnabled: true,
                                    uploadEnabled: true,
                                    alignmentEnabled: true,   // 是否显示排列按钮 相当于text-align
                                    previewImage: true,
                                    inputAccept: 'image/*',
                                    alt: {present: false, mandatory: false,previewImage: true}
                                },
                            }}

                        />
                    </Form.Item>

                </Form>
            </Modal>

            <Modal title="修改文章" visible={changeArticleModal}
                   width={1000}
                   onOk={ChangeArticleOK}
                   onCancel={()=>{
                       setChangeArticleModal(false)
                   }}
            >
                <Form
                    name="update"
                    form={changeArticleForm}
                    labelCol={{ span: 8 }}
                    wrapperCol={{ span: 16 }}
                    initialValues={{ remember: true }}
                    autoComplete="off"
                >

                    <Form.Item
                        label="key"
                        name="key"
                        hidden={true}
                    >
                        <input/>
                    </Form.Item>

                    <Form.Item
                        label="文章名称"
                        name="articleName"
                        rules={[{
                            required: true,
                            message: "请输入文章名称"
                        }]}
                        style={{
                            width: "30%"
                        }}
                    >
                        <input />
                    </Form.Item>

                    <Form.Item
                        label="优先级"
                        name="sortRank"
                        rules={[{
                            required: true,
                            message: "请输入文章名称"
                        }]}
                        style={{
                            width: "30%"
                        }}
                    >
                        <InputNumber />
                    </Form.Item>

                    <Form.Item
                        label="状态"
                        name="status"
                        valuePropName="checked"
                        rules={[{
                            required: false,
                        }]}
                        style={{
                            width: "30%"
                        }}
                    >
                        <Switch checkedChildren="开启" unCheckedChildren="关闭"  />
                    </Form.Item>

                    <Form.Item
                        label="栏目"
                        name="parentId"
                        rules={[{
                            required: true,
                            message: "请输入文章名称"
                        }]}
                        style={{
                            width: "30%"
                        }}
                    >
                        <Select style={{ width: 120, marginBottom: 10 }} onChange={(value: any, event:any)=>{
                            setClickSelect(event.key)
                        }}>
                            {columnList.map((item:any)=>{
                                return(
                                    <Option value={item.columnName} key={item.key}>{item.columnName}</Option>
                                )
                            })}
                        </Select>
                    </Form.Item>


                    <Form.Item
                        label="封面图"
                        name="coverImg"
                        valuePropName="fileList"
                        style={{
                            width: "30%"
                        }}
                    >
                        <Upload
                            name="avatar"
                            listType="picture-card"
                            className="avatar-uploader"
                            showUploadList={false}
                            action="/api/upload"
                            beforeUpload={beforeUpload}
                            onChange={handleChange}
                        >
                            {imageUrl ? <img src={imageUrl} alt="avatar" style={{ width: '100%' }} /> : uploadButton}
                        </Upload>
                    </Form.Item>

                    <Form.Item
                        name="content"
                    >
                        <Editor
                            editorState={changeEditorState}
                            wrapperClassName="demo-wrapper"
                            editorClassName="demo-editor"
                            onEditorStateChange={setChangeEditorState}
                            editorStyle={{
                                minHeight: 300
                            }}
                            toolbar={{
                                image: {
                                    urlEnabled: true,
                                    uploadEnabled: true,
                                    alignmentEnabled: true,   // 是否显示排列按钮 相当于text-align
                                    previewImage: true,
                                    inputAccept: 'image/*',
                                    alt: {present: false, mandatory: false,previewImage: true}
                                },
                            }}

                        />
                    </Form.Item>

                </Form>
            </Modal>

        </div>
    )
}

export default Article