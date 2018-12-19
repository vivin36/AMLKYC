import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Moment from 'react-moment';
import PaymentsActions from '../actions/payment.action';
import CreateKYCForm from '../components/forms/createKYCForm';


class TransactionList extends React.Component {
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
            <h2>Transactions</h2>  


            {this.props.paymentReducer?
             <div className="ml-5">
        <table className="table table-bordered"> 
        <thead>
          <tr>
            <th>
                  ID
                </th>               
                <th>
                amount
                </th>
                <th>
                description
                </th>      
                <th>
                status
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
            <td>{row.amount}</td>
            <td>{row.description}</td>
            <td>{row.status}</td>
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

TransactionList.propTypes = {
    fetchTransferPayments: PropTypes.func.isRequired,
  };

const mapDispatchToProps = dispatch => ({
    fetchTransferPayments: () => {
        PaymentsActions.fetchTransferPayments()(dispatch);
      },
  
});

const mapStateToProps = (state, ownProps) => {
  let  {paymentReducer} = state;
  paymentReducer = paymentReducer['PaymentsDetails']['data'];
  return {
    paymentReducer,
  };
};
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(TransactionList);
