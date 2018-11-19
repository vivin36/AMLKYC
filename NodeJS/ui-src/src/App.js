import React from 'react';
import { Provider } from 'react-redux';
import {
  ConnectedRouter,
  connectRouter,
  routerMiddleware,
} from 'connected-react-router';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import { PersistGate } from 'redux-persist/integration/react';

import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import { createBrowserHistory } from 'history';
import thunk from 'redux-thunk';
import { createLogger } from 'redux-logger';
import reducers from './reducers/index';
import 'react-toastify/dist/ReactToastify.css';
import Routes from './routes';
// import auth from './middleware/auth';

const history = createBrowserHistory();
const reactRouterMiddleware = routerMiddleware(history);
const logger = createLogger({
  predicate: (getState, action) => !action.type.includes('@@redux-form'),
});

const middleWares = [thunk, logger, reactRouterMiddleware];

const persistConfig = {
  key: 'root',
  storage,
};

const persistedReducer = persistReducer(
  persistConfig,
  connectRouter(history)(reducers),
);

const store = createStore(
  persistedReducer,
  composeWithDevTools(applyMiddleware(...middleWares)),
);

const App = () => (
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistStore(store)}>
      <ConnectedRouter history={history}>
        <Routes history={history} />
      </ConnectedRouter>
    </PersistGate>
  </Provider>
);

export default App;
