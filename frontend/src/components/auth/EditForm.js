import React from 'react';
import { Form, Input, Modal, Select } from 'antd';
import { useOktaAuth } from '@okta/okta-react';
import { updateAuth } from '../../services/auths';

const EditForm = ({ item, onCancel, onEdit }) => {
    const { authState } = useOktaAuth()
    const {Option} = Select;

    const onFinish = (values) => {
        console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    const [form] = Form.useForm();

    return (
        <Modal title="Edit Auth"
            visible={item ? true : false}
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
                        updateAuth(item.id, values, authState.accessToken.accessToken).then(res => onEdit(res)).catch(err => onEdit(err.response))

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
                initialValues={item}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                autoComplete="off"
            >
                <Form.Item
                    label="Id"
                    name="id"
                    rules={[{ required: true, message: 'Please input auth ID!' }]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Auth Type"
                    name="authType"
                >
                    <Select
                        placeholder="Select auth type"
                        allowClear
                    >
                        <Option value="userpass">Userpass</Option>
                        <Option value="md5">MD5</Option>
                        <Option value="google_oauth">Google Oauth</Option>
                    </Select>
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

export default EditForm;