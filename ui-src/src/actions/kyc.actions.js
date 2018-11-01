import KYCConstants from '../constants/kycConstant';
import { getCall,postCall } from './fetch.actions';



const createKYC = KYCDocuments => (dispatch) => {
  dispatch({
    type: KYCConstants.KYC_CREATE_FETCH,
    payload: {
      isFetching: true,
    },
  });
  KYCDocuments.validationEndDate = new Date().getTime();
  const KYCDetails = {
    data: KYCDocuments,
    endpoint: '/kyc',
    success: KYCConstants.KYC_CREATE_SUCCESS,
    error: KYCConstants.KYC_CREATE_FAILURE,
    route: '/',
  };

  postCall(KYCDetails)(dispatch);
};

const getKYCDetails = (nationalID) => (dispatch) => {
  dispatch({
    type: KYCConstants.KYC_GETDETAILS_FETCH,
    payload: {
      isFetching: true,
    },
  });

  const KYCDetails = {  
    endpoint: '/kyc/'+nationalID,
    success: KYCConstants.KYC_GETDETAILS_SUCCESS,
    error: KYCConstants.KYC_GETDETAILS_FAILURE,
  };

  getCall(KYCDetails)(dispatch);
};




export default {
  createKYC,
  getKYCDetails
};
