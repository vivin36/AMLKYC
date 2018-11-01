const ScreeningList = artifacts.require('./ScreeningList.sol');

module.exports = (deployer) => {
  deployer.deploy(ScreeningList);
};