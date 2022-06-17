import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { deleteEndpoint } from '../services/endpoint';
import { Button, notification, Affix } from 'antd';
import EndpointList from '../components/endpoint/EndpointList';
import { PlusOutlined } from '@ant-design/icons';
import AddForm from '../components/endpoint/AddForm';
import { getAors } from '../services/aor';
import { getAuths } from '../services/auths';

const Endpoints = () => {
    const { authState } = useOktaAuth()
    const [showForm, setShowForm] = useState(false);
    const [refreshList, setRefreshList] = useState(false);

    const [authList,setAuthList] = useState([]);
    const [aorList,setAorList] = useState([]);

    useEffect(() => {
        
        getAors(authState.accessToken.accessToken).then(res => setAorList(res.data)).catch(err => console.log(err))
        getAuths(authState.accessToken.accessToken).then(res => setAuthList(res.data)).catch(err => console.log(err))
    
    }, [])
    

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
        <AddForm authList={authList} aorList={aorList} showForm={showForm} onAdd={onAdd} onCancel={status => setShowForm(status)} />
    </div>)
}
export default Endpoints;