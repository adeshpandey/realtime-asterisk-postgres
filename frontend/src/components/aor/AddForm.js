import React from 'react';
import { Form, Input, Button, Checkbox, Modal } from 'antd';
import { addAor } from '../../services/aor';
import { useOktaAuth } from '@okta/okta-react';

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
        <Modal title="Add AOR"
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
                        addAor(values, authState.accessToken.accessToken).then(res => onAdd(res)).catch(err => onAdd(err.response))
                        
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
                initialValues={{ remember: true }}
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
                    label="Contact"
                    name="contact"
                >
                    <Input defaultValue={null} />
                </Form.Item>

                <Form.Item
                    label="Max Contact"
                    name="maxContacts"
                >
                    <Input defaultValue={1} />
                </Form.Item>

                <Form.Item name="removeExisting" valuePropName="checked" wrapperCol={{ offset: 8, span: 16 }}>
                    <Checkbox>Remove Existing</Checkbox>
                </Form.Item>

                <Form.Item name="removeUnavailable" valuePropName="checked" wrapperCol={{ offset: 8, span: 16 }}>
                    <Checkbox>Remove Unavailable</Checkbox>
                </Form.Item>

            </Form>
        </Modal>
    );
};

export default AddForm;