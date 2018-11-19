

module.exports = {
  networks: {
    development: {
      host: 'localhost',
      port: 8545,
      network_id: '777', 
      gas: 4700000,
      gasPrice:0
    },
	nodeone: {
      host: 'localhost',
      port: 22000,
      network_id: '*', // Match any network id
      gasPrice: 0,
      gas: 4700000
    },
    nodefour:  {
      host: 'localhost',
      port: 22003,
      network_id: "*", // Match any network id
      gasPrice: 0,
      gas: 4700000
    },
  }
};

