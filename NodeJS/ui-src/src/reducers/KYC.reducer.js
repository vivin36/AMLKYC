import KYCConstants from '../constants/kycConstant';

const initialState = {
  isFetching: true,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case KYCConstants.KYC_CREATE_FETCH:
      return {
        ...state,
        isFetching: true,
      };
    case KYCConstants.KYC_CREATE_SUCCESS:
      alert(('address', action.payload.address));
      return {
        ...state,
        KYCDetails: action.payload,
        isFetching: false,
      };
    case KYCConstants.KYC_CREATE_FAILURE:
      alert('Problem Creating User');
      return {
        ...state,
        err: action.payload,
      };
    case KYCConstants.KYC_GETDETAILS_SUCCESS:
      return {
        ...state,
        KYCDetails: action.payload,
        isFetching: false,
      };
    case KYCConstants.KYC_GETDETAILS_FAILURE:
      return {
        ...state,
        err: action.payload,
      };
    default:
      return state;
  }
};