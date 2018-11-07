import Web3 from '../web3';
import customerRepoMetaData from '../../../Contracts/build/contracts/CustomerRepo.json';
import customerMetaData from '../../../Contracts/build/contracts/Customer.json';
import config from '../config/config';

const web3 = Web3();
const customerRepoInstance = new web3.eth.Contract(customerRepoMetaData.abi, customerRepoMetaData.networks[config.development.network_id].address);

let createCustomerDetails = async (accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        await customerRepoInstance.methods.createCustomer(
            web3.utils.fromAscii(accountNumber)
        )
        .send({
            from: accounts[0],
            gas: 4700000
        });
        
        let customerContractAddress = await customerRepoInstance.methods.getCustomerAddress(web3.utils.fromAscii(accountNumber)).call();

        console.log("CustomerContractAddress:: ", customerContractAddress);
        let customerInstance = new web3.eth.Contract(customerMetaData.abi, customerContractAddress);

        await customerInstance.methods.createCustomerDetails(
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

let updateCustomerDetails = async (accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        let customerContractAddress = await customerRepoInstance.methods.getCustomerAddress(web3.utils.fromAscii(accountNumber)).call();
        
        let customerInstance = new web3.eth.Contract(customerMetaData.abi, customerContractAddress);

        await customerInstance.methods.updateCustomerDetails(
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

let getAllCustomerDetails = async () => {
    try {  
        let customerDetailsList = [];
        let customerDetails = {};
        let count = 0;
        const customerRepoResponse = await customerRepoInstance.methods.getAllAddresses().call();
        const customerContractAddresses = customerRepoResponse.filter(elem => elem != '0x0000000000000000000000000000000000000000');
        for (var customerContractAddress of customerContractAddresses) {
            let customerInstance = new web3.eth.Contract(customerMetaData.abi, customerContractAddress);
            let customerResponse = await customerInstance.methods.getCustomerDetails().call();
            customerDetails.name = customerResponse[count++];
            customerDetails.customerType = parseInt(customerResponse[count++]);
            customerDetails.kycStatus = parseInt(customerResponse[count++]);
            customerDetails.isParent = customerResponse[count++];        
            customerDetails.validatedDate = parseInt(customerResponse[count++]);
            customerDetails.validationEndDate = parseInt(customerResponse[count++]);
            customerDetailsList.push(customerDetails);
            customerDetails = {};
            count = 0;
        }
        
        return Promise.resolve(customerDetailsList);
    } catch (err) {
        return Promise.reject(err);
    }
};

let getCustomerDetails = async (accountNumber) => {
    try {
        let customerDetails = {};
        let count = 0;

        let customerContractAddress = await customerRepoInstance.methods.getCustomerAddress(web3.utils.fromAscii(accountNumber)).call();        
        let customerInstance = new web3.eth.Contract(customerMetaData.abi, customerContractAddress);
        let customerResponse = await customerInstance.methods.getCustomerDetails().call();
        customerDetails.name = customerResponse[count++];
        customerDetails.customerType = parseInt(customerResponse[count++]);
        customerDetails.kycStatus = parseInt(customerResponse[count++]);
        customerDetails.isParentCustomer = customerResponse[count++];        
        customerDetails.validatedDate = parseInt(customerResponse[count++]);
        customerDetails.validationEndDate = parseInt(customerResponse[count++]);        

        return Promise.resolve(customerDetails);
    } catch(err) {
        return Promise.reject(err);
    }
};

export { 
    createCustomerDetails,
    updateCustomerDetails,
    getAllCustomerDetails,
    getCustomerDetails
};