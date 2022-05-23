import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { Button, Popconfirm, Table, Space } from 'antd';
import EditForm from './EditForm';
import { getEndpoints } from '../../services/endpoint';

const EndpointList = ({ onDelete, refresh }) => {
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

        <Table dataSource={endpoints} columns={columns} rowKey="id" />
        {editItem && <EditForm item={editItem} onEdit={onEdit} onCancel={status => setEditItem(null)} />}
    </>)
}
export default EndpointList;