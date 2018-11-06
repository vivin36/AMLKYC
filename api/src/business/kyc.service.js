import Web3 from '../web3';
import kycMetaData from '../../../Contracts/build/contracts/KYC.json';
import config from '../config/config';

const web3 = Web3();
const kycInstance = new web3.eth.Contract(kycMetaData.abi, kycMetaData.networks['1541488714690'].address);

let createKYC = async (accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        console.log(accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate);
        await kycInstance.methods.createKYC(
            web3.utils.fromAscii(accountNumber), 
            name,
            customerType,
            kycStatus,
            isParentCustomer, 
            validationEndDate)
            .send({
                from: accounts[0],
                gas: 4700000                
            });
        return Promise.resolve();
    } catch (err) {
        return Promise.reject(err);
    }
};

let updateKYC = async (accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        await kycInstance.methods.updateKYC(
            web3.utils.fromAscii(accountNumber), 
            name, 
            customerType, 
            kycStatus, 
            isParentCustomer, 
            validationEndDate)
            .send({
                from: accounts[0],
                gas: 4700000
            });
        return Promise.resolve();    
    } catch (err) {
        return Promise.reject(err);
    }
};

let getKYCDetails = async (accountNumber) => {
    try {        
        let details = {};
        let count = 0;
        const response = await kycInstance.methods.getDetailsByID(web3.utils.asciiToHex(accountNumber)).call();        
        details.name = response[count++];
        details.customerType = parseInt(response[count++]);
        details.kycStatus = parseInt(response[count++]);
        details.isParent = response[count++];        
        details.validatedDate = parseInt(response[count++]);
        details.validationEndDate = parseInt(response[count++]);
        return Promise.resolve(details);
    } catch (err) {
        return Promise.reject(err);
    }
};

export { 
    createKYC,
    updateKYC,
    getKYCDetails
};