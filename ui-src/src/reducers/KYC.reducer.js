import KYCConstants from '../constants/kycConstant';

const initialState = {
  isFetching: true,
};

export default (state = initialState, action) => {
  console.log(action.type);
  switch (action.type) {
    case KYCConstants.KYC_CREATE_FETCH:
      return {
        ...state,
        isFetching: true,
      };
    case KYCConstants.KYC_CREATE_SUCCESS:
      alert('Success');
      return {
        ...state,
        KYCDetails: action.payload,
        isFetching: false,
      };
    case KYCConstants.KYC_CREATE_FAILURE:
    console.log('Error occured : VM Exception');
    alert('Customer is blacklisted!');
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
