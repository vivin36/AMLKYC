import Web3 from '../web3';
import kycMetaData from '../../../Contracts/build/contracts/KYC.json';

const web3 = Web3();
const kycInstance = new web3.eth.Contract(kycMetaData.abi, kycMetaData.networks['1540983364653'].address);

let createKYC = async (id, name, userAddress, dob, gender, validationEndDate) => {
    try {
        const accounts = await web3.eth.getAccounts();
        const kyc = await kycInstance.methods.createKYC(
            web3.utils.fromAscii(id), 
            name, 
            userAddress, 
            web3.utils.fromAscii(dob), 
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

let getKYCDetails = async (id) => {
    try {        
        let details = {};
        let count = 0;
        const response = await kycInstance.methods.getDetailsByID(web3.utils.asciiToHex(id)).call();        
        details.name = response[count++];
        details.userAddress = response[count++];
        details.dob = response[count++];
        details.gender = response[count++];
        details.validatedDate = parseInt(response[count++]);
        details.validationEndDate = parseInt(response[count++]);
        return Promise.resolve(details);
    } catch (e) {
        return Promise.reject(e);
    }
};

export { 
    createKYC,
    getKYCDetails
};