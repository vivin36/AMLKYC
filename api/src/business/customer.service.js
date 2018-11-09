import Web3 from '../web3';
import customerMetaData from '../../../Contracts/build/contracts/Customer.json';
import config from '../config/config';
import csvdata from 'csvdata';

const web3 = Web3();
let customerInstance = new web3.eth.Contract(customerMetaData.abi, customerMetaData.networks[config.development.network_id].address);

let createCustomerDetails = async (account, name, customerType, kycStatus, isParentCustomer) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        
        let response = {};

        let custAccountAddress = await web3.eth.personal.newAccount(account);             

        await customerInstance.methods.createCustomerDetails(
            custAccountAddress,
            web3.utils.fromAscii(account),
            web3.utils.fromAscii(name),
            customerType,
            //kycStatus,
            0,
            isParentCustomer)
            .send({
                from: accounts[0],
                gas: 4700000                
            });
            response.custAccountAddress = custAccountAddress;
            response.account = account;
            response.name = name;
            response.customerType = customerType;
            response.kycStatus = kycStatus;
            response.isParentCustomer = isParentCustomer;
        return Promise.resolve(response);
    } catch (err) {
        return Promise.reject(err);
    }
};

let updateCustomerDetails = async (address, account, name, customerType, kycStatus, isParentCustomer) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        
        await customerInstance.methods.updateCustomerDetails(
            address,
            web3.utils.fromAscii(account),
            web3.utils.fromAscii(name), 
            customerType, 
            kycStatus, 
            isParentCustomer)
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
        
        const customerResponse = await customerInstance.methods.getAllCustomerDetails().call();

        const addressesList = customerResponse[0].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const accountsList = customerResponse[1].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const namesList = customerResponse[2].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const custTypesList= customerResponse[3].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const statusesList = customerResponse[4].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const isParentsList = customerResponse[5];
        
        for (var count = 0; count < addressesList.length; count++) {
            customerDetails.address =  addressesList[count];   
            customerDetails.account = web3.utils.toAscii(accountsList[count]).replace(/\0/g, '');
            customerDetails.name = web3.utils.toAscii(namesList[count]).replace(/\0/g, '');
            customerDetails.custType = custTypesList[count];
            customerDetails.status = statusesList[count];        
            customerDetails.isParent = isParentsList[count];
            customerDetailsList.push(customerDetails);
            customerDetails = {};            
        }
        
        return Promise.resolve(customerDetailsList);
    } catch (err) {
        return Promise.reject(err);
    }
};

let getCustomerDetails = async (address) => {
    try {
        let customerDetails = {};
        let count = 0;

        
        let customerResponse = await customerInstance.methods.getCustomerDetails(address).call();
        customerDetails.account = web3.utils.toAscii(customerResponse[count++]).replace(/\0/g, '');
        customerDetails.name = web3.utils.toAscii(customerResponse[count++]).replace(/\0/g, '');
        customerDetails.customerType = parseInt(customerResponse[count++]);
        customerDetails.kycStatus = parseInt(customerResponse[count++]);
        customerDetails.isParentCustomer = customerResponse[count++];  
        return Promise.resolve(customerDetails);
    } catch(err) {
        return Promise.reject(err);
    }
};

let validateAndParseFile = async (filePath) => {
    let accounts = [], names = [], custTypes = [], isParent = [], accountAddresses = [];
    const ethAccounts = await web3.eth.getAccounts();

    let parsedData = await csvdata.load(filePath);

    for(var count  = 0; count < parsedData.length; count++) {
        accounts.push(web3.utils.fromAscii(parsedData[count].ACCOUNT));
        names.push(web3.utils.fromAscii(parsedData[count].NAME));
        custTypes.push(parsedData[count].CUSTOMER_TYPE);
        isParent.push(parsedData[count].PARENT_OR_SUBSIDIARY);
    }

    accountAddresses.push('0x1d82269cea0a74829a7f727016dd608f12c34fac');
    accountAddresses.push('0x8cb7af8f38616b84a8ecc0b0a327d04e02b11bea');
    accountAddresses.push('0xf5c7cdc2348ababb3ead65eef69ceca3f0f933e8');

    console.log(accountAddresses, accounts, names, custTypes, isParent);

    await customerInstance.methods.createCustomerDetailsBatch(
        accountAddresses,
        accounts,
        names,
        custTypes,
        isParent)
        .send({
            from: ethAccounts[0],
            gas: 4700000
    });    
};

export { 
    createCustomerDetails,
    updateCustomerDetails,
    getAllCustomerDetails,
    getCustomerDetails,
    validateAndParseFile
};