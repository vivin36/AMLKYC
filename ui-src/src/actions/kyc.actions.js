import KYCConstants from '../constants/kycConstant';
import { getCall,postCall } from './fetch.actions';



const createKYC = KYCDocuments => (dispatch) => {
  dispatch({
    type: KYCConstants.KYC_CREATE_FETCH,
    payload: {
      isFetching: true,
    },
  });
  const  d = new Date();
  const c = new Date(d.getFullYear() + 1, d.getMonth(),  d.getDate())
  KYCDocuments.validationEndDate = c.getTime();
  KYCDocuments.kycStatus = 0;
  if (KYCDocuments.isParent === "true")
    KYCDocuments.isParent = true;
  else
  KYCDocuments.isParent = false;
  const KYCDetails = {
    data: KYCDocuments,
    endpoint: '/kyc',
    success: KYCConstants.KYC_CREATE_SUCCESS,
    error: KYCConstants.KYC_CREATE_FAILURE,
    route: '/fetchKYC',
  };

  postCall(KYCDetails)(dispatch);
};

const getKYCDetails = (accountNumber) => (dispatch) => {
  dispatch({
    type: KYCConstants.KYC_GETDETAILS_FETCH,
    payload: {
      isFetching: true,
    },
  });

  const KYCDetails = {  
    endpoint: '/kyc/'+accountNumber,
    success: KYCConstants.KYC_GETDETAILS_SUCCESS,
    error: KYCConstants.KYC_GETDETAILS_FAILURE,
  };

  getCall(KYCDetails)(dispatch);
};




export default {
  createKYC,
  getKYCDetails
};
