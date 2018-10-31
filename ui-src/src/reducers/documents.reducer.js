import documentConstants from '../constants/documentConstant';

const initialState = {
  isFetching: true,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case documentConstants.DOCUMENTS_LIST_FETCH:
      return {
        ...state,
        isFetching: true,
      };

    case documentConstants.DOCUMENTS_LIST_SUCCESS:
      return {
        ...state,
        documentsList: action.payload,
        isFetching: false,
      };
    case documentConstants.DOCUMENTS_LIST_FAILURE:
      return {
        ...state,
        err: action.payload,
      };
    case documentConstants.DOCUMENTS_LIST_PENDING_APPROVAL_FETCH:
      return {
        ...state,
        isFetching: true,
      };
    case documentConstants.DOCUMENTS_LIST_PENDING_APPROVAL_SUCCESS:
      return {
        ...state,
        documentsPendingList: action.payload,
        err: action.payload,
      };
    case documentConstants.DOCUMENTS_LIST_PENDING_APPROVAL_FAILURE:
      return {
        ...state,
        isFetching: false,
        err: action.payload,
      };

    case documentConstants.DOCUMENTS_LIST_FILTER: {
      const token = action.payload.userId;
      /* eslint  array-callback-return: 0  */
      /* eslint consistent-return: 0  */
      const filteredArray = state.documentsList.filter((itm) => {
        /* eslint no-underscore-dangle: 0  */
        if (itm.owner_details[0] && itm.owner_details[0]._id === token) {
          return itm;
        }
      });
      /*  eslint no-param-reassign: 0  */
      state.documentsList = filteredArray;
      return {
        ...state,
        err: action.payload,
      };
    }
    default:
      return state;
  }
};
