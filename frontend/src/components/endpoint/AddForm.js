import React from 'react';
import { Form, Input, Checkbox, Modal, Select } from 'antd';
import { useOktaAuth } from '@okta/okta-react';
import { addEndpoint } from '../../services/endpoint';

const { Option } = Select;
const codecList = ["ulaw","alaw","wav","gsm","slin"];

const AddForm = ({ showForm, onCancel, onAdd, authList, aorList, initialValues }) => {
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
                        addEndpoint(values, authState.accessToken.accessToken).then(res => onAdd(res)).catch(err => onAdd(err.response))

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
                    label="Id"
                    name="id"
                    rules={[{ required: true, message: 'Please input endpoint id!' }]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Context"
                    name="context"
                    rules={[{ required: true, message: 'Please input context!' }]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    label="Allow"
                    name="allow"
                >
                    <Select mode="multiple">
                        {codecList.map(v => <Option key={v}>{v}</Option>)}
                    </Select>
                </Form.Item>

                <Form.Item
                    label="Auth"
                    name="auth"
                >
                    <Select>
                        {authList.map(v => <Option key={v.id}>{v.id}</Option>)}
                    </Select>
                </Form.Item>

                <Form.Item
                    label="Aors"
                    name="aors"
                >
                    <Select mode="multiple">
                        {aorList.map(v => <Option key={v.id}>{v.id}</Option>)}
                    </Select>
                </Form.Item>

            </Form>
        </Modal>
    );
};

export default AddForm;