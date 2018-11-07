import Web3 from '../web3';
import screeningListMetaData from '../../../Contracts/build/contracts/ScreeningList.json';
import config from '../config/config';

const web3 = Web3();
const screeningListInstance = new web3.eth.Contract(screeningListMetaData.abi, screeningListMetaData.networks[config.development.network_id].address);

let blackListCustomer = async (accountNumber, name) => {
    try {        
        const key = config.nodeFour.key;
        const accounts = await web3.eth.getAccounts();
        await screeningListInstance.methods.addBlackListedCustomer(
            web3.utils.fromAscii(accountNumber),
            name)
            .send({
                from: accounts[0],
                gas: 4700000
            });
        return Promise.resolve();    
    } catch (err) {
        return Promise.reject(err);
    }
};

export {
    blackListCustomer
};