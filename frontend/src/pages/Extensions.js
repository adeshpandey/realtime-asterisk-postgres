import { Affix, Button, notification } from 'antd'
import React, { useState } from 'react'
import { PlusOutlined } from '@ant-design/icons';
import AddForm from '../components/extension/AddForm'
import ExtensionList from '../components/extension/ExtensionList'
import { deleteExtension } from '../services/extensions';
import { useOktaAuth } from '@okta/okta-react';

export default function Extensions() {

  const { authState } = useOktaAuth()
  const [showForm, setShowForm] = useState(false);
  const [refreshList, setRefreshList] = useState(false);
  const [initialValues, setInitialValues] = useState(null);


  function onDelete(id) {
    deleteExtension(id, authState.accessToken.accessToken).then(res => {
        if (res.status == 200) {
            notification.open({
                message: 'Extension',
                type: "success",
                description:
                    'Extension deleted successfully.'
            });
            setRefreshList(!refreshList)
        }
        else {
            notification.open({
                message: 'Extension',
                type: "error",
                description:
                    'Failed to delete Extension.',
            });
        }
    }).catch(err => {
        notification.open({
            message: 'Extension',
            description:
                'Something went wrong.',
        });
    })
}

const onAdd = (response) => {
    if (response.status == 200) {
        notification.open({
            message: 'Extension',
            type: "success",
            description:
                'Extension added successfully.'
        });
        setRefreshList(!refreshList)
        setShowForm(false)
    }
    else {
        notification.open({
            message: 'Extension',
            type: "error",
            description:
                'Can\'t add Extension.'
        });
    }
}

const cloneItem = clonedItem => {
    setInitialValues(clonedItem);
    setShowForm(true)
}

  return (<div>
    <ExtensionList refresh={refreshList} onDelete={onDelete} onClone={(clonedItem) => cloneItem(clonedItem)} />
    <Affix style={{ position: 'fixed', bottom: 10, right: 10 }}>
      <Button size="large" shape='circle' type="primary" onClick={() => setShowForm(true)}>
        <PlusOutlined />
      </Button>
    </Affix>
    <AddForm initialValues={initialValues} showForm={showForm} onAdd={onAdd} onCancel={status => setShowForm(status)} />
  </div>
  )
}
