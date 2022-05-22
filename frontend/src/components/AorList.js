import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { getAors } from '../services/aor';
import { Button, Popconfirm, Table, notification } from 'antd';

const AorList = ({onDelete}) => {
    const { authState } = useOktaAuth()
    const [aors, setAors] = useState();

    useEffect(() => {
        getAors(authState.accessToken.accessToken).then(res => {
            setAors(res.data)
        }).catch(err => console.log(err))
    }, [])

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
            title: 'Remove Existing',
            dataIndex: 'removeExisting',
            render: text => String(text)
        },
        {
            title: 'Actions',
            render: (text, record) => {
                return (
                    <Popconfirm title="Delete?" onConfirm={() => onDelete(record.id)}>
                        <Button>Delete</Button>
                    </Popconfirm>
                );
            },
        },
    ];

    return <Table dataSource={aors} columns={columns} rowKey="id" />
}
export default AorList;