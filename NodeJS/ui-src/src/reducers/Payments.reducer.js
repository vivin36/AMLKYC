import PaymentsConstants from '../constants/paymentConstant';

const initialState = {
  isFetching: true,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case PaymentsConstants.PAYMENTS_TRANSACTIONDETAILS_FETCH:
      return {
        ...state,
        isFetching: true,
      };
      case PaymentsConstants.PAYMENTS_TRANSACTIONDETAILS_SUCCESS:
      return {
        ...state,
        PaymentsDetails: action.payload,
        isFetching: false,
      };
    case PaymentsConstants.PAYMENTS_TRANSACTIONDETAILS_FAILURE:
      return {
        ...state,
        err: action.payload,
      };
      case PaymentsConstants.PAYMENTS_REDEMPTIONDETAILS_FETCH:
      return {
        ...state,
        isFetching: true,
      };
      case PaymentsConstants.PAYMENTS_REDEMPTIONDETAILS_SUCCESS:
      return {
        ...state,
        RedemptionDetails: action.payload,
        isFetching: false,
      };
    case PaymentsConstants.PAYMENTS_REDEMPTIONDETAILS_FAILURE:
      return {
        ...state,
        err: action.payload,
      };
    default:
      return state;
  }
};
