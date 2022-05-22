import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { deleteAor } from '../services/aor';
import { Button, notification, Affix } from 'antd';
import AorList from '../components/AorList';
import { PlusOutlined } from '@ant-design/icons';

const Protected = () => {
    const { authState } = useOktaAuth()
    const [showForm, setShowForm] = useState(false);

    function onDelete(id) {
        deleteAor(id, authState.accessToken.accessToken).then(res => {
            if (res.status == 200) {
                notification.open({
                    message: 'Aor',
                    description:
                        'AOR deleted successfully.'
                });
            }
            else {
                notification.open({
                    message: 'Aor',
                    description:
                        'Failed to delete AOR.',
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

    const onAdd = () => {

    }


    return (<div>
        <AorList onDelete={onDelete} />
        <Affix style={{position:'fixed',bottom:10,right:10}}>
        <Button size="large" shape='circle' type="primary" onClick={() => setShowForm(true)}>
          <PlusOutlined />
        </Button>
      </Affix>
    </div>)
}
export default Protected;