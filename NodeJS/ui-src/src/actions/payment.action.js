import PaymentsConstants from '../constants/paymentConstant';
import { getCall } from './fetch.actions';


const fetchInputPaymentsTransactionsList = () => (dispatch) => {
  dispatch({
    type: PaymentsConstants.PAYMENTS_INPUTPAYMENTDETAILS_FETCH,
    payload: {
      isFetching: true,
    },
  });

  const TransactionDetails = {  
    endpoint: '/payments/redeem',
    success: PaymentsConstants.PAYMENTS_INPUTPAYMENTDETAILS_SUCCESS,
    error: PaymentsConstants.PAYMENTS_INPUTPAYMENTDETAILS_FAILURE,
  };

  getCall(TransactionDetails)(dispatch);
};



const fetchTransferPayments = () => (dispatch) => {
  dispatch({
    type: PaymentsConstants.PAYMENTS_TRANSFERTRANSACTIONDETAILS_FETCH,
    payload: {
      isFetching: true,
    },
  });

  const TransactionDetails = {  
    endpoint: '/payments/transfer',
    success: PaymentsConstants.PAYMENTS_TRANSFERTRANSACTIONDETAILS_SUCCESS,
    error: PaymentsConstants.PAYMENTS_TRANSFERTRANSACTIONDETAILS_FAILURE,
  };

  getCall(TransactionDetails)(dispatch);
};




export default {
  fetchInputPaymentsTransactionsList,
    fetchTransferPayments,  
};
