import React, { useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { Button, notification, Affix, Modal } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import { deleteAuth } from '../services/auths';
import AuthList from '../components/auth/AuthList';
import AddForm from '../components/auth/AddForm';

const Protected = () => {
    const { authState } = useOktaAuth()
    const [showForm, setShowForm] = useState(false);
    const [refreshList, setRefreshList] = useState(false);

    function onDelete(id) {
        deleteAuth(id, authState.accessToken.accessToken).then(res => {
            if (res.status == 200) {
                notification.open({
                    message: 'Auth',
                    type: "success",
                    description:
                        'Auth deleted successfully.'
                });
                setRefreshList(!refreshList)
            }
            else {
                notification.open({
                    message: 'Auth',
                    type: "error",
                    description:
                        'Failed to delete Auth.',
                });
            }
        }).catch(err => {
            notification.open({
                message: 'Aor',
                description:
                    'Something went wrong.',
            });
        })
    }

    const onAdd = (response) => {
        if (response.status == 200) {
            notification.open({
                message: 'Auth',
                type: "success",
                description:
                    'Auth added successfully.'
            });
            setRefreshList(!refreshList)
            setShowForm(false)
        }
        else {
            notification.open({
                message: 'Auth',
                type: "error",
                description:
                    'Can\'t add Auth.'
            });
        }
    }


    return (<div>
        <AuthList refresh={refreshList} onDelete={onDelete} />
        <Affix style={{ position: 'fixed', bottom: 10, right: 10 }}>
            <Button size="large" shape='circle' type="primary" onClick={() => setShowForm(true)}>
                <PlusOutlined />
            </Button>
        </Affix>
        <AddForm showForm={showForm} onAdd={onAdd} onCancel={status => setShowForm(status)} />
    </div>)
}
export default Protected;