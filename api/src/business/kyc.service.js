import Web3 from '../web3';
import kycMetaData from '../../../Contracts/build/contracts/KYC.json';

const web3 = Web3();
const kycInstance = new web3.eth.Contract(kycMetaData.abi, kycMetaData.networks['1540983364653'].address);

let createKYC = async (id, name, userAddress, age, gender, validationEndDate) => {
    try {
        console.log(id, name, userAddress, age, gender, validationEndDate,	web3.utils.fromAscii('rewre'));
        const accounts = await web3.eth.getAccounts();
        const kyc = await kycInstance.methods.createKYC(
            web3.utils.fromAscii(id), 
            name, 
            userAddress, 
            age, 
            web3.utils.fromAscii(gender), 
            validationEndDate)
            .send({
                from: accounts[0],
                gas: 4700000
                //privateFor: ['']
        });
        return Promise.resolve();
    } catch (e) {
        return Promise.reject(e);
    }
};

export { createKYC };