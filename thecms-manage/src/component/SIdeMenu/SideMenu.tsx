//  Local
import "./SideMenu.css"

import {GetSideMenu} from "../../api/SideMenu/GetSideMenu";

//  React
import React, { useState, useEffect} from "react";
import { useNavigate  } from "react-router-dom"

//  Antd
import {DesktopOutlined, PieChartOutlined,  UserOutlined} from "@ant-design/icons";
import {Menu} from "antd";
import type { MenuProps } from 'antd';




const iconList:any = {
    "PieChartOutlined": <PieChartOutlined/>,
    "DesktopOutlined": <DesktopOutlined />,
    "UserOutlined":<UserOutlined />
}

type MenuItem = Required<MenuProps>['items'][number];

function getItem(
    label: React.ReactNode,
    key: React.Key,
    icon: React.ReactNode,
    children: null
    ) {
    return {
        label,
        key,
        icon,
        children,
    } as MenuItem;
}

function SideMenu(){

    const navigate = useNavigate();

    const [menus, setMenus] = useState([]);

    const [selectedKey,setSelectedKey] = useState(String)

    useEffect(()=> {
        GetSideMenu().then((value:any) => {
            let menuList = value
            let menus:any = []
            menuList.forEach((menu:any) => {
                menus.push(getItem(menu.label,menu.key,iconList[menu.icon],null))
            })
            setMenus(menus)
        })
    },[])



    return(
        <div>
            <div className="logo" />
            <Menu theme="dark" selectedKeys={[selectedKey]} mode="inline" items={menus} onClick={(item)=>{
                setSelectedKey(item.key)
                navigate(item.key)
            }}/>
        </div>
    )
}
export default SideMenu;