import React from 'react';
import { Route, Switch } from 'react-router';
import Home from './containers/Home';
import CreateKYC from './containers/createKYC';
import GetKYCDetails from './containers/getKYCDetails';
import TransactionList from './containers/getTransactionsHistory';
import NotFound from './components/404';
import Layout from './hoc/Layout';

const Routes = props => (
  <div>
    <Layout isLoggedIn={true}>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/KYC" component={CreateKYC} />
        <Route exact path="/fetchKYC" component={GetKYCDetails} />
        <Route exact path="/payments" component={TransactionList} /> 
        <Route component={NotFound} />
      </Switch>
    </Layout>
  </div>
);

export default Routes;
