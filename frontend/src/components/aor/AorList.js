import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { getAors } from '../../services/aor';
import { Button, Popconfirm, Table, Space } from 'antd';
import EditForm from './EditForm';

const AorList = ({ onDelete, refresh }) => {
    const { authState } = useOktaAuth()
    const [aors, setAors] = useState();
    const [editItem, setEditItem] = useState(null);

    useEffect(() => {
        getAors(authState.accessToken.accessToken).then(res => {
            setAors(res.data)
        }).catch(err => console.log(err))
    }, [refresh])

    const onEdit = () => {
        setEditItem(null)
        getAors(authState.accessToken.accessToken).then(res => {
            setAors(res.data)
        }).catch(err => console.log(err))
    }

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
        },
        {
            title: 'Contact',
            dataIndex: 'contact',
        },
        {
            title: 'Max Contact',
            dataIndex: 'maxContacts',
        },
        {
            title: 'Remove Existing',
            dataIndex: 'removeExisting',
            render: text => String(text)
        },
        {
            title: 'Remove Unavailable',
            dataIndex: 'removeUnavailable',
            render: text => String(text)
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
    
        <Table dataSource={aors} columns={columns} rowKey="id" />
        {editItem && <EditForm item={editItem} onEdit={onEdit} onCancel={status => setEditItem(null)} />}
    </>)
}
export default AorList;