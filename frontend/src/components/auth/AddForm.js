import React from 'react';
import { Form, Input, Checkbox, Modal } from 'antd';
import { useOktaAuth } from '@okta/okta-react';
import { addAuth } from '../../services/auths';

const AddForm = ({ showForm, onCancel, onAdd }) => {
    const { authState } = useOktaAuth()

    const onFinish = (values) => {
        console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    const [form] = Form.useForm();

    return (
        <Modal title="Add Auth"
            visible={showForm}
            onOk={() => {
                form
                    .validateFields()
                    .then(values => {
                        form.resetFields();
                        if (values.removeExisting != undefined) {
                            values.removeExisting = values.removeExisting ? 'yes' : 'no'
                        }

                        if (values.removeUnavailable != undefined) {
                            values.removeUnavailable = values.removeUnavailable ? 'yes' : 'no'
                        }
                        addAuth(values, authState.accessToken.accessToken).then(res => onAdd(res)).catch(err => onAdd(err.response))
                        
                    })
                    .catch(info => {
                        console.log('Validate Failed:', info);
                    });
            }}
            onCancel={() => onCancel(false)}>
            <Form
                form={form}
                name="basic"
                labelCol={{ span: 8 }}
                wrapperCol={{ span: 16 }}
                initialValues={{authType: 'userpass'}}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                autoComplete="off"
            >
                <Form.Item
                    label="Id"
                    name="id"
                    rules={[{ required: true, message: 'Please input aor ID!' }]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Auth type"
                    name="authType"
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Username"
                    name="username"
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Password"
                    name="password"
                >
                    <Input />
                </Form.Item>
            </Form>
        </Modal>
    );
};

export default AddForm;