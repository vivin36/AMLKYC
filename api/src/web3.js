import Web3 from 'web3';
import config from './config/config';

export default () =>
  new Web3(new Web3.providers.HttpProvider('http://'+config.nodeOne.host+':'+config.nodeOne.port));
