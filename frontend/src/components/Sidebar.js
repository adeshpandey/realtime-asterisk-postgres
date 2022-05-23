import {Layout, Menu} from "antd";
import { useState } from "react";
import {Link} from 'react-router-dom'
import {
    UserOutlined,
    MailOutlined,
    KeyOutlined
  } from '@ant-design/icons';

export default function({collapsed}){
    const {Sider} = Layout;

    return (<Sider trigger={null} collapsible collapsed={collapsed}>
        <div className="logo" />
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={['1']}
          items={[
            {
              key: '1',
              icon: <MailOutlined />,
              label: (<Link to="/aors">Aors</Link>)
            },
            {
              key: '2',
              icon: <KeyOutlined />,
              label: (<Link to="/auths">Auths</Link>)
            },
            {
              key: '3',
              icon: <UserOutlined />,
              label: (<Link to="/endpoints">Endpoints</Link>)
            },
          ]}
        />
      </Sider>)
}