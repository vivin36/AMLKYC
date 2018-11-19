const Customer = artifacts.require("./Customer.sol");

module.exports = function(deployer) {
  deployer.deploy(Customer);  
};
