const ScreeningList = artifacts.require('./ScreeningList.sol');

module.exports = (deployer) => {
  deployer.deploy(ScreeningList,{privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]});
};