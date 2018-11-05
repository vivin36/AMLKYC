import { push } from 'connected-react-router';
import api from '../utilities/api';
import appConstants from '../constants/appConstants';
import projectConstants from '../constants/projectConstants';

/**
 *
 * @param {*} props
 * headers : pass custom headers of any to headers key
 * method : GET/POST
 * mode: CORS/NO-CORS
 * cache: default/others
 * pass other configurations here
 */
export const getCall = props => (dispatch) => {
  dispatch({
    type: appConstants.WEB_REQUEST,
  });
  api
    .get(`${props.endpoint}/${props.data || ''}`)
    .then((res) => {
      console.log(res.status)
      if (res.status === 200) {
        const response = res;
        if (response ) {
          dispatch({
            type: props.success,
            payload: response,
          });

          // Push routes here if any
          if (props.route) {
            if (props.success === projectConstants.PROJECT_DETAILS_SUCCESS) {
              dispatch(
                push({
                  pathname: '/project',
                  state: { project: response.data },
                }),
              );
            } else {
              dispatch(push(props.route));
            }
          }
          dispatch({
            type: appConstants.WEB_REQUEST_SUCCESS,
          });
          return;
        }
        dispatch({ type: props.error, payload: response.message });
        throw new Error(response.message);
      }
    })
    .catch(err => dispatch({
      type: appConstants.WEB_REQUEST_ERROR,
      payload:
          (err.response && err.response.data && err.response.data.message)
          || err.message
          || err,
    }));
};

export const postCall = props => (dispatch) => {
  dispatch({
    type: appConstants.WEB_REQUEST,
  });
  api
    .post(`${props.endpoint}`, props.data)
    .then((res) => {
      if (res.status === 200) {
        const response = res.data;
        if (response) {
          if (response) {
            dispatch({
              type: props.success,
              payload: response,
            });

            // Push routes here if any
            if (props.route) dispatch(push(props.route));
            dispatch({
              type: appConstants.WEB_REQUEST_SUCCESS,
            });
            return;
          }
          dispatch({
            type: props.error,
            payload: response.message,
          });
          throw new Error(response.message);
        }
      }
    })
    .catch((err) => {
      dispatch({
        type: props.error,
        payload: (err.response && err.response.data && err.response.data.message)
        || err.message
        || err,
      });
      return dispatch({
        type: appConstants.WEB_REQUEST_ERROR,
        payload:
            (err.response && err.response.data && err.response.data.message)
            || err.message
            || err,
      });
    });
};

export const putCall = props => (dispatch) => {
  dispatch({
    type: appConstants.WEB_REQUEST,
  });
  api
    .put(`${props.endpoint}/${props.data || ''}`)
    .then((res) => {
      if (res.statusText === 'OK') {
        const response = res.data;
        if (response) {
          if (response.data) {
            dispatch({
              type: props.success,
              payload: response.data,
            });

            // Push routes here if any
            if (props.route) dispatch(push(props.route));
            dispatch({
              type: appConstants.WEB_REQUEST_SUCCESS,
            });
            return;
          }
          dispatch({
            type: props.error,
            payload: response.message,
          });
          throw new Error(response.message);
        }
      }
    })
    .catch(err => dispatch({
      type: appConstants.WEB_REQUEST_ERROR,
      payload:
          (err.response && err.response.data && err.response.data.message)
          || err.message
          || err,
    }));
};

export const filePostCall = props => (dispatch) => {
  const config = { headers: { 'Content-Type': 'multipart/form-data' } };
  api
    .post(`${props.endpoint}`, props.data, config)
    .then((res) => {
      if (res.statusText === 'OK') {
        const response = res.data;
        if (response) {
          if (response.data) {
            dispatch({
              type: props.success,
              payload: response.data,
            });

            // Push routes here if any
            if (props.route) dispatch(push(props.route));

            return;
          }
          dispatch({
            type: props.error,
            payload: response.message,
          });
          throw new Error(response.message);
        }
      }
    })
    .catch(err => dispatch({
      type: appConstants.WEB_REQUEST_ERROR,
      payload:
          (err.response && err.response.data && err.response.data.message)
          || err.message
          || err,
    }));
};
