import React from 'react';
import { Form, Input, Checkbox, Modal, Select } from 'antd';
import { useOktaAuth } from '@okta/okta-react';
import { addExtension } from '../../services/extensions';

const AddForm = ({ showForm, onCancel, onAdd, initialValues }) => {
    const { authState } = useOktaAuth()

    const onFinish = (values) => {
        console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    const [form] = Form.useForm();

    return (
        <Modal title="Add Endpoint"
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
                        addExtension(values, authState.accessToken.accessToken).then(res => onAdd(res)).catch(err => onAdd(err.response))

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
                initialValues={initialValues}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                autoComplete="off"
            >
                <Form.Item
                    label="Context"
                    name="context"
                    rules={[{ required: true, message: 'Please input context!' }]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Extension"
                    name="extension"
                    rules={[{ required: true, message: 'Please input extension!' }]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Priority"
                    name="priority"
                    rules={[{ required: true, message: 'Please input priority!' }]}
                >
                    <Input type="number" />
                </Form.Item>

                <Form.Item
                    label="Application"
                    name="app"
                    rules={[{ required: true, message: 'Please input asterisk app name!' }]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Application Data"
                    name="appData"
                    rules={[{ required: true, message: 'Please input asterisk app options!' }]}
                >
                    <Input />
                </Form.Item>

                
            </Form>
        </Modal>
    );
};

export default AddForm;