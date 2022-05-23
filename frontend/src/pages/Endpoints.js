import React, { useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { deleteEndpoint } from '../services/endpoint';
import { Button, notification, Affix } from 'antd';
import EndpointList from '../components/endpoint/EndpointList';
import { PlusOutlined } from '@ant-design/icons';
import AddForm from '../components/endpoint/AddForm';

const Endpoints = () => {
    const { authState } = useOktaAuth()
    const [showForm, setShowForm] = useState(false);
    const [refreshList, setRefreshList] = useState(false);

    function onDelete(id) {
        deleteEndpoint(id, authState.accessToken.accessToken).then(res => {
            if (res.status == 200) {
                notification.open({
                    message: 'Endpoint',
                    type: "success",
                    description:
                        'Endpoint deleted successfully.'
                });
                setRefreshList(!refreshList)
            }
            else {
                notification.open({
                    message: 'Endpoint',
                    type: "error",
                    description:
                        'Failed to delete Endpoint.',
                });
            }
        }).catch(err => {
            notification.open({
                message: 'Endpoint',
                description:
                    'Something went wrong.',
            });
        })
    }

    const onAdd = (response) => {
        if (response.status == 200) {
            notification.open({
                message: 'Endpoint',
                type: "success",
                description:
                    'Endpoint added successfully.'
            });
            setRefreshList(!refreshList)
            setShowForm(false)
        }
        else {
            notification.open({
                message: 'Endpoint',
                type: "error",
                description:
                    'Can\'t add Endpoint.'
            });
        }
    }


    return (<div>
        <EndpointList refresh={refreshList} onDelete={onDelete} />
        <Affix style={{ position: 'fixed', bottom: 10, right: 10 }}>
            <Button size="large" shape='circle' type="primary" onClick={() => setShowForm(true)}>
                <PlusOutlined />
            </Button>
        </Affix>
        <AddForm showForm={showForm} onAdd={onAdd} onCancel={status => setShowForm(status)} />
    </div>)
}
export default Endpoints;