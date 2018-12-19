import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Moment from 'react-moment';
import PaymentsActions from '../actions/payment.action';
import CreateKYCForm from '../components/forms/createKYCForm';


class InputPaymentsList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { transaction: {} };
  }


  componentDidMount() {
    this.props.fetchInputPaymentsTransactionsList();
  }
  
  render() {
    return (
      <div>
        <div className="row">
          <div className="mt-5 block-position ml-5">
            <h5>Input Payments List</h5>
            {this.props.paymentReducer?
             <div className="table-small">
        <table className="table able-hover table-dark table-striped "> 
        <thead>
          <tr>
            <th>
                  ID
                </th>               
                <th>
                Amount
                </th>
                <th>
                Description
                </th>     
                <th>
                Account Number
                </th>  
                <th>
                 Account Id
                </th>  
            
                <th>
                Status
                </th>            
                <th>
                Comments
                </th> 
                </tr>             
                </thead>
                <tbody> 

                        {this.props.paymentReducer.map((row, i) =>
          <tr key={i}>
            <td>{row.id}</td>
            <td>{row.amount} {row.currency}</td>
            <td>{row.description}</td>
            <td>{row.accountNumber}</td>
            <td>{row.accountId}</td>
            <td>{row.status =='S'?'Success':'Failure'}</td>
            <td>{row.comments}</td>
          </tr>
        )}
               </tbody>  
             </table></div> :''}


          </div>
        </div>
      </div>
    );
  }
}

InputPaymentsList.propTypes = {
  fetchInputPaymentsTransactionsList: PropTypes.func.isRequired,
  };

const mapDispatchToProps = dispatch => ({
  fetchInputPaymentsTransactionsList: () => {
        PaymentsActions.fetchInputPaymentsTransactionsList()(dispatch);
      },
  
});

const mapStateToProps = (state, ownProps) => {
  let  {paymentReducer} = state;
  paymentReducer = paymentReducer['PaymentsTransferDetails'];
  return {
    paymentReducer,
  };
};
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(InputPaymentsList);
