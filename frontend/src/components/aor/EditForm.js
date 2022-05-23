import React from 'react';
import { Form, Input, Checkbox, Modal } from 'antd';
import { updateAor } from '../../services/aor';
import { useOktaAuth } from '@okta/okta-react';

const EditForm = ({item, onCancel, onEdit }) => {
    const { authState } = useOktaAuth()

    const onFinish = (values) => {
        console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    const [form] = Form.useForm();

    return (
        <Modal title="Edit AOR"
            visible={item? true: false}
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
                        updateAor(item.id,  values, authState.accessToken.accessToken).then(res => onEdit(res)).catch(err => onEdit(err.response))
                        
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
                    <Input value={item.id} defaultValue={item.id} />
                </Form.Item>

                <Form.Item
                    label="Contact"
                    name="contact"
                >
                    <Input value={item.contact} defaultValue={item.contact} />
                </Form.Item>

                <Form.Item
                    label="Max Contact"
                    name="maxContacts"
                >
                    <Input value={item.maxContacts} defaultValue={item.maxContacts} />
                </Form.Item>

                <Form.Item name="removeExisting" valuePropName="checked" wrapperCol={{ offset: 8, span: 16 }}>
                    <Checkbox defaultValue={item.removeExisting == 'yes'} checked={item.removeExisting == 'yes'}>Remove Existing</Checkbox>
                </Form.Item>

                <Form.Item name="removeUnavailable" valuePropName="checked" wrapperCol={{ offset: 8, span: 16 }}>
                    <Checkbox defaultValue={item.removeUnavailable == 'yes'} checked={item.removeUnavailable == 'yes'}>Remove Unavailable</Checkbox>
                </Form.Item>

            </Form>
        </Modal>
    );
};

export default EditForm;