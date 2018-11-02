const KYC = artifacts.require("./KYC.sol");
const ScreeningList = artifacts.require('./ScreeningList.sol');

module.exports = function(deployer) {
  deployer.deploy(ScreeningList).then(function() {
    return deployer.deploy(KYC,ScreeningList.address);
  });  
};
