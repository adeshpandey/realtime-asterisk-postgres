import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { Button, Popconfirm, Table, Space } from 'antd';
import EditForm from './EditForm';
import { getAuths } from '../../services/auths';

const AuthList = ({ onDelete, refresh }) => {
    const { authState } = useOktaAuth()
    const [auths, setAuths] = useState();
    const [editItem, setEditItem] = useState(null);

    const getAuthList = () => {
        getAuths(authState.accessToken.accessToken).then(res => {
            setAuths(res.data)
        }).catch(err => console.log(err))
    }

    useEffect(() => {
        getAuthList()
    }, [refresh])

    const onEdit = () => {
        setEditItem(null)
        getAuthList()

    }

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
        },
        {
            title: 'Auth Type',
            dataIndex: 'authType',
        },
        {
            title: 'Username',
            dataIndex: 'username',
        },
        {
            title: 'Password',
            dataIndex: 'password',
        },
        {
            title: 'Actions',
            render: (text, record) => {
                return (<>
                    <Popconfirm title="Delete?" onConfirm={() => onDelete(record.id)}>
                        <Button>Delete</Button>
                    </Popconfirm>
                    <Space />
                    <Button onClick={() => setEditItem(record)}>Edit</Button>
                </>
                );
            },
        },
    ];

    return (<>

        <Table dataSource={auths} columns={columns} rowKey="id" />
        {editItem && <EditForm item={editItem} onEdit={onEdit} onCancel={status => setEditItem(null)} />}
    </>)
}
export default AuthList;