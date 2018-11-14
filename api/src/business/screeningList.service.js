import Web3 from '../web3';
import screeningListMetaData from '../../../Contracts/build/contracts/ScreeningList.json';
import config from '../config/config';

const web3 = Web3();
const screeningListInstance = new web3.eth.Contract(screeningListMetaData.abi, screeningListMetaData.networks[config.development.network_id].address);

let blackListCustomer = async (accountNumber, name, identificationNumber) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        await screeningListInstance.methods.addBlackListedCustomer(
                accountNumber,
                web3.utils.fromAscii(name),
                web3.utils.fromAscii(identificationNumber))
            .send({
                from: accounts[0],
                gas: 4700000
            });
        return Promise.resolve();
    } catch (err) {
        return Promise.reject(err);
    }
};

let getAllBlackListedCustomers = async () => {
    try {
        let blackListedCustomersList = [];
        let blackListedCustomers = {};

        const customerResponse = await screeningListInstance.methods.getBlackListedCustomers().call();
        const addressesList = customerResponse[0].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const namesList = customerResponse[1].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const identificationNumbersList = customerResponse[2].filter(elem => elem != '0x0000000000000000000000000000000000000000');

        for (var count = 0; count < addressesList.length; count++) {
            blackListedCustomers.address = addressesList[count];
            blackListedCustomers.identificationNumber = web3.utils.toAscii(identificationNumbersList[count]).replace(/\0/g, '');
            blackListedCustomers.name = web3.utils.toAscii(namesList[count]).replace(/\0/g, '');
            blackListedCustomersList.push(blackListedCustomers);
            blackListedCustomers = {};
        }

        return Promise.resolve(BlackListedCustomersList);
    } catch (err) {
        return Promise.reject(err);
    }
};

let whiteListCustomer = async (accountNumber, name, identificationNumber) => {
    try {
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        await screeningListInstance.methods.addWhiteListedCustomer(
                accountNumber,
                web3.utils.fromAscii(name),
                web3.utils.fromAscii(identificationNumber))
            .send({
                from: accounts[0],
                gas: 4700000
            });
        return Promise.resolve();
    } catch (err) {
        return Promise.reject(err);
    }
};

let getAllwhiteListCustomers = async () => {
    try {
        let whiteListedCustomersList = [];
        let whiteListedCustomers = {};

        const customerResponse = await screeningListInstance.methods.getWhiteListedCustomer().call();
        const addressesList = customerResponse[0].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const namesList = customerResponse[1].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        const identificationNumbersList = customerResponse[2].filter(elem => elem != '0x0000000000000000000000000000000000000000');

        for (var count = 0; count < addressesList.length; count++) {
            whiteListedCustomers.address = addressesList[count];
            whiteListedCustomers.identificationNumber = web3.utils.toAscii(identificationNumbersList[count]).replace(/\0/g, '');
            whiteListedCustomers.name = web3.utils.toAscii(namesList[count]).replace(/\0/g, '');
            whiteListedCustomersList.push(whiteListedCustomers);
            whiteListedCustomers = {};
        }

        return Promise.resolve(whiteListedCustomersList);
    } catch (err) {
        return Promise.reject(err);
    }
};


let checkIsWhiteListed = async (accountAddress) => {
    try {
        const screeningListResponse = await screeningListInstance.methods.checkIsWhiteListed(accountAddress).call();
        let response = {
            isWhiteList: screeningListResponse
        }
        return Promise.resolve(response);
    } catch (err) {
        return Promise.reject(err);
    }
};

let checkIsBlackListed = async (accountAddress) => {
    try {
        const screeningListResponse = await screeningListInstance.methods.checkIsBlackListed(accountAddress).call();
        let response = {
            isBlackListed: screeningListResponse
        }
        return Promise.resolve(response);
    } catch (err) {
        return Promise.reject(err);
    }
};

let removeFromBlackListedCustomer = async (accountAddress) => {
    try {
        const accounts = await web3.eth.getAccounts();
        const screeningListResponse = await screeningListInstance.methods.removeBlackListedCustomer(accountAddress).send({
            from: accounts[0],
            gas: 4700000
        });
        return Promise.resolve();
    } catch (err) {
        return Promise.reject(err);
    }
};

let removeFromWhiteListedCustomer = async (accountAddress) => {
    try {
        const accounts = await web3.eth.getAccounts();
        const screeningListResponse = await screeningListInstance.methods.removeWhiteListedCustomer(accountAddress).send({
            from: accounts[0],
            gas: 4700000
        });
        return Promise.resolve();
    } catch (err) {
        return Promise.reject(err);
    }
};




export {
    blackListCustomer,
    whiteListCustomer,
    getAllBlackListedCustomers,
    getAllwhiteListCustomers,
    checkIsWhiteListed,
    checkIsBlackListed,
    removeFromBlackListedCustomer,
    removeFromWhiteListedCustomer
};