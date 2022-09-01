//  component
import {GetColumn,AddColumn,UpdateColumn,DeleteColumn,ChangeColumnStatus} from "../../../api/Column/Column";

//  react
import {useEffect, useState} from "react"

//  antd
import {Button, Form, Input, InputNumber, Modal, Switch, Table} from 'antd';
import type { ColumnsType } from 'antd/es/table';



function Column ():any{

    interface ColumnDataType {
        key: string,
        columnName: string,
        status: number,
        sortRank: number,
        edit:any[]
    }

    const columns: ColumnsType<ColumnDataType> = [
        {
            title: 'Id',
            dataIndex: 'key',
            key: 'id',
            width: '5%',
            align: 'center'
        },
        {
            title: '栏目名称',
            dataIndex: 'columnName',
            key: 'columnName',
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
                    <Switch checkedChildren="开启" unCheckedChildren="关闭" defaultChecked={bool}  onChange={(status) => changeArticleStatus(status,record)}/>
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
            title: '操作',
            dataIndex: 'edit',
            key: 'edit',
            render:(text:any, record:any) =>{
                return(
                    <div>
                        <Button type="primary" style={{marginRight: 10}} onClick={() => showChange(record)}>
                            修改
                        </Button>
                        <Button type="primary" danger onClick={()=>showDelete(record)}>
                            删除
                        </Button>
                    </div>
                )
            }
        },
    ];

    //  Modal状态
    const [addColumn, setAddColumn] = useState(false);
    const [changeColumn, setChangeColumn] = useState(false);
    const [deleteColumn, setDeleteColumn] = useState(false);
    //  缓冲动画
    const [confirmLoading, setConfirmLoading] = useState(false);
    //  Modal数据
    const [modalData, setModalData] = useState<any>([])
    //  ColumnData
    const [columnData, setColumnData] = useState<any>([])
    //  更新界面
    const [update, setUpdate] = useState(false);

    // antd form hook
    const [addColumnForm] = Form.useForm()
    const [changeColumnForm] = Form.useForm()

    function getStatus(value: boolean){
        if (value){
            return 0
        }else {
            return 1
        }
    }

    useEffect(()=>{
        GetColumn().then((columnData:any)=>{
            setColumnData(columnData)
        })
    },[update])


    //  add column
    function showAddColumn(){
        setAddColumn(true)
    }

    function handleOkAddColumn(){

        //  antd 表单数据验证hook
        addColumnForm.validateFields().then(value => {
            //  开启缓冲动画
            setConfirmLoading(true)
            //  将状态转为int类型
            value.status = getStatus(value.status)
            //  添加请求
            AddColumn(value)
            //  update取反刷新state
            setUpdate(!update)
            //  关闭缓冲动画
            setConfirmLoading(false)
            //  关闭窗口
            setAddColumn(false)
        })
    }

    //  change column
    function showChange(record:any){
        //  根据参数设置表格状态
        function setStatus(status:any){
            return status === 0;
        }

        setChangeColumn(true)
        //  设置table数据
        setModalData(record)
        changeColumnForm.setFieldsValue({
            columnName: record.columnName,
            sortRank: record.sortRank,
            status: setStatus(record.status)
        })

    }

    function handleOkChange(){
        //  antd 表单数据验证hook
        changeColumnForm.validateFields().then(value => {
            //  开启缓冲动画
            setConfirmLoading(true)
            const updateList:any = [
                {
                    columnName: value.columnName,
                    status: getStatus(value.status),
                    sortRank: value.sortRank
                },
                {
                    key: modalData.key,
                    columnName: modalData.columnName,
                    status: modalData.status,
                    sortRank: modalData.sortRank
                }
            ]
            //  更新请求
            UpdateColumn(updateList)
            //  update取反刷新state
            setUpdate(!update)
            //  关闭缓冲动画
            setConfirmLoading(false)
            //  关闭窗口
            setChangeColumn(false)
        });
    }


    //  delete column
    function showDelete(record:any){
        setDeleteColumn(true)
        setModalData(record)
    }

    function handleOkDelete(){
        //  开启缓冲
        setConfirmLoading(true)
        //  发送删除请求
        DeleteColumn(modalData)
        //  update取反刷新state
        setUpdate(!update)
        //  关闭缓冲
        setConfirmLoading(false)
        //  关闭窗口
        setDeleteColumn(false)
    }


    //  change table status
    function changeArticleStatus(status:any,record:any){
        record.status = getStatus(status)
        //  状态更新
        ChangeColumnStatus(record)
        //  update取反刷新state
        setUpdate(!update)
    }


    return(
        <div>
            <Button
                type="primary"
                onClick={showAddColumn}
                style={{
                    marginBottom: 16,
                }}>添加栏目</Button>
                <Table columns={columns} dataSource={columnData} pagination={{
                current: 1,
                pageSize: 10,
                total: 5,
            }}/>

            <Modal
                title="添加栏目"
                visible={addColumn}
                onOk={handleOkAddColumn}
                onCancel={()=>{
                    setAddColumn(false)
                }}
                confirmLoading={confirmLoading}>

                <Form
                    form={addColumnForm}
                    name="addColumn"
                    labelCol={{ span: 8 }}
                    wrapperCol={{ span: 16 }}
                    autoComplete="off"
                    initialValues={{
                        status: true
                    }}
                >
                    <Form.Item
                        label="栏目名称"
                        name="columnName"
                        rules={[{
                            required: true,
                            message: "请输入栏目名称"
                        }]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label="状态"
                        name="status"
                        rules={[{ required: true }]}
                        valuePropName="checked"
                    >
                        <Switch checkedChildren="开启" unCheckedChildren="关闭" />
                    </Form.Item>

                    <Form.Item
                        label="优先级"
                        name="sortRank"
                        rules={[{
                            required: true,
                            message: "请输入数字"
                        }]}
                    >
                        <InputNumber />
                    </Form.Item>


                </Form>

            </Modal>

            <Modal
                title="修改栏目"
                visible={changeColumn}
                onOk={handleOkChange}
                onCancel={()=>{
                    setChangeColumn(false)
                }}
                confirmLoading={confirmLoading}
            >

                <Form
                    form={changeColumnForm}
                    name="changeColumn"
                    labelCol={{ span: 8 }}
                    wrapperCol={{ span: 16 }}
                    autoComplete="off"
                >
                    <Form.Item
                        label="栏目名称"
                        name="columnName"
                        rules={[{
                            required: true,
                            message: "请输入栏目名称"
                        }]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label="状态"
                        name="status"
                        rules={[{ required: true}]}
                        valuePropName= "checked"
                    >
                        <Switch checkedChildren="开启" unCheckedChildren="关闭"  />
                    </Form.Item>

                    <Form.Item
                        label="优先级"
                        name="sortRank"
                        rules={[{
                            required: true,
                            message: "请输入数字"
                        }]}
                    >
                        <InputNumber />
                    </Form.Item>


                </Form>

            </Modal>

            <Modal
                title="删除栏目"
                visible={deleteColumn}
                onOk={handleOkDelete}
                onCancel={()=>{
                    setDeleteColumn(false)
                }}
                confirmLoading={confirmLoading}>

                <div>你确定要删除 {modalData.columnName} 吗?</div>
            </Modal>


        </div>
    )
}

export default Column