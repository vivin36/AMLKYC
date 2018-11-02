const KYC = artifacts.require("./KYC.sol");
const ScreeningList = artifacts.require('./ScreeningList.sol');

module.exports = function(deployer) {
  deployer.deploy(KYC,ScreeningList.address);
};
