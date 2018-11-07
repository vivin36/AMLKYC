const CustomerRepo = artifacts.require("./CustomerRepo.sol");

module.exports = function(deployer) {
  deployer.deploy(CustomerRepo);  
};
