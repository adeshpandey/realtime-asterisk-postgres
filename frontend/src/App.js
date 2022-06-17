import 'antd/dist/antd.css';
import './App.css';
import React from 'react';
import { SecureRoute, Security, LoginCallback } from '@okta/okta-react';
import { OktaAuth, toRelativeUrl } from '@okta/okta-auth-js';
import { useHistory, BrowserRouter as Router, Route, withRouter, Switch } from "react-router-dom";

import Home from './Home';
import Aors from './pages/Aors';
import Auths from './pages/Auths';
import Endpoints from './pages/Endpoints';
import { useState } from 'react';
import { Layout } from 'antd';
import Sidebar from './components/Sidebar';
import {
  MenuUnfoldOutlined,
  MenuFoldOutlined
} from '@ant-design/icons';

const oktaAuth = new OktaAuth({
  issuer: `https://${process.env.REACT_APP_OKTA_HOST_NAME}.okta.com/oauth2/default`,
  clientId: process.env.REACT_APP_OKTA_FRONTEND_CLIENT_ID,
  redirectUri: window.location.origin + '/login/callback'
});

function App() {
  const history = useHistory();

  const restoreOriginalUri = async (_oktaAuth, originalUri) => {
    history.replace(toRelativeUrl(originalUri || '/', window.location.origin));
  };

  return (
    <Security oktaAuth={oktaAuth} restoreOriginalUri={restoreOriginalUri} >
      <Route path='/' exact={true} component={Home} />
      <SecureRoute path='/aors' component={Aors} />
      <SecureRoute path='/auths' component={Auths} />
      <SecureRoute path='/endpoints' component={Endpoints} />
      <Route path='/login/callback' component={LoginCallback} />
    </Security>
  );
}

const AppWithRouter = withRouter(App);

export default () => {
  const { Header, Content, Footer } = Layout;
  const [collapsed, setCollapsed] = useState(false);

  const toggle = () => {
    setCollapsed(!collapsed);
  }

  return <Router>
    <Layout style={{height:"100vh"}}>
      <Sidebar collapsed={collapsed} />
      <Layout>
        <Header className="site-layout-background" style={{ padding: 0 }}>
          {React.createElement(collapsed ? MenuUnfoldOutlined : MenuFoldOutlined, {
            className: 'trigger',
            onClick: toggle,
          })}
        </Header>
        <Content
          className="site-layout-background"
          style={{
            margin: '24px 16px',
            padding: 24,
            minHeight: 280,
          }}
        >
          <Switch>
            <AppWithRouter />
          </Switch>
        </Content>
        <Footer style={{ textAlign: 'center' }}>Made with Love by ADESH PANDEY using Ant Design</Footer>

      </Layout>
    </Layout>
  </Router>
}
