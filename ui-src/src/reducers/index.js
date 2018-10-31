import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form';
import  KYCReducer  from './KYC.reducer';
//import storage from 'redux-persist/lib/storage';
const rootReducer = combineReducers({
    form: formReducer,
    KYCReducer,
});

// const appReducer = (state, action) => {
//   if (action.type === userConstants.USER_LOGOUT) {
//     Object.keys(state).forEach((key) => {
//       storage.removeItem(`persist:${key}`);
//     });
//     // eslint-disable-next-line
//     state = undefined;
//   }
//   return rootReducer(state, action);
// };

export default rootReducer;
