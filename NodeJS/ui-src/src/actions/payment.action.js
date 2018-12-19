import PaymentsConstants from '../constants/paymentConstant';
import { getCall } from './fetch.actions';



const fetchTransferPayments = () => (dispatch) => {
  dispatch({
    type: PaymentsConstants.PAYMENTS_TRANSACTIONDETAILS_FETCH,
    payload: {
      isFetching: true,
    },
  });

  const TransactionDetails = {  
    endpoint: '/payments/transfer',
    success: PaymentsConstants.PAYMENTS_TRANSACTIONDETAILS_SUCCESS,
    error: PaymentsConstants.PAYMENTS_TRANSACTIONDETAILS_FAILURE,
  };

  getCall(TransactionDetails)(dispatch);
};

const fetchRedemptions = () => (dispatch) => {
  dispatch({
    type: PaymentsConstants.PAYMENTS_TRANSACTIONDETAILS_FETCH,
    payload: {
      isFetching: true,
    },
  });

  const RedemptionDetails = {
    endpoint: '/payments/redeem',
    success: PaymentsConstants.PAYMENTS_REDEMPTIONDETAILS_SUCCESS,
    error: PaymentsConstants.PAYMENTS_REDEMPTIONDETAILS_FAILURE
  };

  getCall(RedemptionDetails)(dispatch);
};

export default {
    fetchTransferPayments,
    fetchRedemptions
};
