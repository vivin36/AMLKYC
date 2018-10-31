import KYCConstants from '../constants/kycConstant';
import { getCall } from './fetch.actions';

const getDocumentsList = () => (dispatch) => {
  dispatch({
    type: KYCConstants.KYC_CREATE_FETCH,
    payload: {
      isFetching: true,
    },
  });

  const documentProps = {
    endpoint: '',
    success: KYCConstants.KYC_CREATE_SUCCESS,
    error: KYCConstants.KYC_CREATE_FAILURE,
  };

  getCall(documentProps)(dispatch);
};

export default {
  getDocumentsList,
};
