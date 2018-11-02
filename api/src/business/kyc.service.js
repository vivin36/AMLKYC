import Web3 from '../web3';
import kycMetaData from '../../../Contracts/build/contracts/KYC.json';
import config from '../config/config';

const web3 = Web3();
const kycInstance = new web3.eth.Contract(kycMetaData.abi, kycMetaData.networks['1541172738934'].address);

let createKYC = async (id, name, userAddress, dob, gender, validationEndDate) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        await kycInstance.methods.createKYC(
            web3.utils.fromAscii(id), 
            name, 
            userAddress, 
            web3.utils.fromAscii(dob), 
            web3.utils.fromAscii(gender), 
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

let updateKYC = async (id, name, userAddress, dob, gender, validationEndDate) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        await kycInstance.methods.updateKYC(
            web3.utils.fromAscii(id), 
            name, 
            userAddress, 
            web3.utils.fromAscii(dob), 
            web3.utils.fromAscii(gender), 
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

let getKYCDetails = async (id) => {
    try {        
        let details = {};
        let count = 0;
        const response = await kycInstance.methods.getDetailsByID(web3.utils.asciiToHex(id)).call();        
        details.name = response[count++];
        details.userAddress = response[count++];
        details.dob = web3.utils.hexToAscii(response[count++]).replace(/\0/g, '');
        details.gender = web3.utils.hexToAscii(response[count++]).replace(/\0/g, '');
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