import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Moment from 'react-moment';
import PaymentsActions from '../actions/payment.action';

class RedeemTransactionList extends React.Component {

    constructor(props) {
      super(props);
      this.state = { transaction: {} };
    }
    
    componentDidMount() {
      this.props.fetchRedemptions();
    }
    
    render() {
      return (
        <div>
          <div className="row">
            <div className="mt-5 block-position ml-5">
              <h5>Redemption Transfer List</h5>
              {this.props.paymentReducer?
               <div className="table-small">
              
          <table className="table table-hover table-dark table-striped "> 
          <thead>
            <tr>
              <th>
                    ID
                  </th>
                  <th>
                  Currency
                  </th>
                  <th>
                  Amount
                  </th>                       
                  <th>
                  Account ID
                  </th>  
                  <th>
                  Account Number
                  </th> 
                  <th>
                  Redemption Reference Number
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
              <td>{row.currency}</td>
              <td>{row.amount}</td>
              <td>{row.accountId}</td>
              <td>{row.accountNumber}</td>
              <td>{row.redeemRefNo}</td>              
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
  
  RedeemTransactionList.propTypes = {
    fetchRedemptions: PropTypes.func.isRequired,
    };
  
  const mapDispatchToProps = dispatch => ({
      fetchRedemptions: () => {
          PaymentsActions.fetchRedemptions()(dispatch);
        },
    
  });
  
  const mapStateToProps = (state, ownProps) => {
    let  {paymentReducer} = state;
    paymentReducer = paymentReducer['RedemptionDetails']
    return {
      paymentReducer,
    };
  };
  export default connect(
    mapStateToProps,
    mapDispatchToProps,
  )(RedeemTransactionList);