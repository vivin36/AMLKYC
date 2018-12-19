import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Moment from 'react-moment';
import PaymentsActions from '../actions/payment.action';
import CreateKYCForm from '../components/forms/createKYCForm';


class TransferPaymentsList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { transaction: {} };
  }


  componentDidMount() {
    this.props.fetchTransferPayments();
  }



  render() {
    return (
      <div>
        <div className="row">
          <div className="mt-5 block-position ml-5">
            <h5>Payments Transfers List</h5>
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
                Receiver Account Id
                </th>  
                <th>
                Receiver Account Number
                </th> 
                <th>
                Sender Account Id
                </th>  
                <th>
                Sender Account Number
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
            <td>{row.receiverAccountId}</td>
            <td>{row.receiverAccountNumber}</td>
            <td>{row.senderAccountId}</td>
            <td>{row.senderAccountNumber}</td>
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

TransferPaymentsList.propTypes = {
    fetchTransferPayments: PropTypes.func.isRequired,
  };

const mapDispatchToProps = dispatch => ({
    fetchTransferPayments: () => {
        PaymentsActions.fetchTransferPayments()(dispatch);
      },
  
});

const mapStateToProps = (state, ownProps) => {
  let  {paymentReducer} = state;
  paymentReducer = paymentReducer ? paymentReducer['PaymentsTransferDetails']:{};
  return {
    paymentReducer,
  };
};
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(TransferPaymentsList);
