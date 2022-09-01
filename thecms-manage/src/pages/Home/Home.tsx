//  component
import SideMenu from "../../component/SIdeMenu/SideMenu"
import Column from "./Column/Column"
import Article from "./Article/Article";
//  react
import React, { useState } from 'react'
import {Route,  Routes} from "react-router-dom"

//  antd
import {Breadcrumb, Layout} from 'antd';
const { Header, Content, Footer, Sider } = Layout


function Home ()  {
    const [collapsed, setCollapsed] = useState(false);
    return (
        <Layout style={{ minHeight: '100vh', }}>
            <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
                <SideMenu/>
            </Sider>
            <Layout className="site-layout">
                <Header className="site-layout-background" style={{padding: 0,}} />
                <Content style={{margin: '0 16px',}}>
                    {/*<Breadcrumb style={{margin: '16px 0',}}>*/}
                    {/*    <Breadcrumb.Item>User</Breadcrumb.Item>*/}
                    {/*    <Breadcrumb.Item>Bill</Breadcrumb.Item>*/}
                    {/*</Breadcrumb>*/}
                    <div className="site-layout-background" style={{padding: 24, minHeight: 360,}}>

                        <Routes>
                            {/*<Route path={''} element={<Index />}></Route>*/}
                            <Route path={'column'} element={<Column />}></Route>
                            <Route path={'article'} element={<Article />}></Route>
                        </Routes>

                    </div>
                </Content>
                {/*<Footer style={{textAlign: 'center',}}>*/}
                {/*    Ant Design ©2018 Created by Ant UED*/}
                {/*</Footer>*/}
            </Layout>
        </Layout>
    );
}

export default Home