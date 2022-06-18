import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { Button, Popconfirm, Table, Space } from 'antd';
import EditForm from './EditForm';
import { getExtensions } from '../../services/extensions';

const ExtensionList = ({ onDelete, refresh, authList, aorList, onClone }) => {
    const { authState } = useOktaAuth()
    const [endpoints, setEndpoints] = useState();
    const [editItem, setEditItem] = useState(null);

    const getExtensionList = () => {
        getExtensions(authState.accessToken.accessToken).then(res => {
            setEndpoints(res.data)
        }).catch(err => console.log(err))
    }

    useEffect(() => {
        getExtensionList()
    }, [refresh])

    const onEdit = () => {
        setEditItem(null)
        getExtensionList()
    }

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
        },
        {
            title: 'Context',
            dataIndex: 'context',
        },
        {
            title: 'Extension',
            dataIndex: 'extension',
        },
        {
            title: 'Priority',
            dataIndex: 'priority',
        },
        {
            title: 'Application',
            dataIndex: 'app',
        },
        {
            title: 'AppData',
            dataIndex: 'appData',
        },
        {
            title: 'Actions',
            render: (text, record) => {
                return (<Space>
                    <Popconfirm title="Delete?" onConfirm={() => onDelete(record.id)}>
                        <Button danger>Delete</Button>
                    </Popconfirm>
                    
                    <Button type='primary' onClick={() => setEditItem(record)}>Edit</Button>
                    <Button type='primary' onClick={() => onClone(record)}>Clone</Button>
                </Space>
                );
            },
        },
    ];

    return (<>
        <Table dataSource={endpoints} columns={columns} rowKey="id" />
        {editItem && <EditForm authList={authList} aorList={aorList} item={editItem} onEdit={onEdit} onCancel={status => setEditItem(null)} />}
    </>)
}
export default ExtensionList;