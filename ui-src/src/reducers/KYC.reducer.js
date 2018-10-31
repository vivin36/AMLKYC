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
      return {
        ...state,
        documentsList: action.payload,
        isFetching: false,
      };
    case KYCConstants.KYC_CREATE_FAILURE:
      return {
        ...state,
        err: action.payload,
      }; 
    default:
      return state;
  }
};
