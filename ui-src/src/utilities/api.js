import Axios from 'axios';
import config from '../config';

const axiosInstance = Axios.create({
  baseURL: config.baseUrl,
  timeout: config.timeout,
  headers: {
    'Content-Type': 'application/json',
  },
});

axiosInstance.interceptors.request.use((request) => {
  if (!request.headers['x-access-token']) {
    axiosInstance.defaults.headers.common[
      'x-access-token'
    ] = window.localStorage.getItem('api-key');
  }
  // eslint-disable-next-line
  console.info("Starting Request", request);
  return request;
});

axiosInstance.interceptors.response.use((response) => {
  // eslint-disable-next-line
  console.info("Response:", response);
  return response;
});

export default axiosInstance;
