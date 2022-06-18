import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { Button, Popconfirm, Table, Space } from 'antd';
import EditForm from './EditForm';
import { getEndpoints } from '../../services/endpoint';

const EndpointList = ({ onDelete, refresh, authList, aorList, onClone }) => {
    const { authState } = useOktaAuth()
    const [endpoints, setEndpoints] = useState();
    const [editItem, setEditItem] = useState(null);

    const getEndpointList = () => {
        getEndpoints(authState.accessToken.accessToken).then(res => {
            setEndpoints(res.data)
        }).catch(err => console.log(err))
    }

    useEffect(() => {
        getEndpointList()
    }, [refresh])

    const onEdit = () => {
        setEditItem(null)
        getEndpointList()
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
            title: 'Auth',
            dataIndex: 'auth',
        },
        {
            title: 'Aors',
            dataIndex: 'aors',
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
export default EndpointList;