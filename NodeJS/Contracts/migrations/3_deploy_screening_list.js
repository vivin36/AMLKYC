const ScreeningList = artifacts.require("./ScreeningList.sol");

module.exports = function(deployer) {
  deployer.deploy(ScreeningList);  
};
