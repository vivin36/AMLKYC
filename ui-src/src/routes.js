import React from 'react';
import { Route, Switch } from 'react-router';
import Home from './containers/Home';
import CreateKYC from './containers/createKYC';
import NotFound from './components/404';
import Layout from './hoc/Layout';

const Routes = props => (
  <div>
    <Layout isLoggedIn={true}>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/KYC" component={CreateKYC} />
        <Route component={NotFound} />
      </Switch>
    </Layout>
  </div>
);

export default Routes;
