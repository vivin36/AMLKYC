import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form';
import  KYCReducer  from './KYC.reducer';
import paymentReducer from  './Payments.reducer';


const rootReducer = combineReducers({
    form: formReducer,
    KYCReducer,
    paymentReducer
});



export default rootReducer;
