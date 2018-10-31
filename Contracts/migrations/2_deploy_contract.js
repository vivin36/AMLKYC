var KYC = artifacts.require("./KYC.sol");

module.exports = function(deployer) {
  deployer.deploy(KYC,{privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]});
};
