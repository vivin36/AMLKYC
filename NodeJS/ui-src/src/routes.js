import React from 'react';
import { Route, Switch } from 'react-router';
import Home from './containers/Home';
import CreateKYC from './containers/createKYC';
import GetKYCDetails from './containers/getKYCDetails';
import TransactionList from './containers/getTransferTransactions';
import RedeemTransactionList from './containers/getRedeemTransactions';
import TransferPaymentsList from './containers/getTransferTransactions';
import NotFound from './components/404';
import Layout from './hoc/Layout';
import InputPaymentsList from './containers/getInputPaymentTransactions';

const Routes = props => (
  <div>
    <Layout isLoggedIn={true}>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/KYC" component={CreateKYC} />
        <Route exact path="/fetchKYC" component={GetKYCDetails} />
        <Route exact path="/payments" component={TransactionList} /> 
        <Route exact path = "/redemptions" component = {RedeemTransactionList} />
        <Route exact path="/redeempayments" component={InputPaymentsList} />
        <Route exact path="/transferpayments" component={TransferPaymentsList} />
        <Route component={NotFound} />
      </Switch>
    </Layout>
  </div>
);

export default Routes;
