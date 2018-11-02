pragma solidity ^0.4.23;
/**
 * @title ScreeningList
 */
contract ScreeningList {

    struct Customer {
        bytes32 accountNumber;
        string name;
    }

    Customer[] private blackListedCustomers;
    
    address private owner = msg.sender;

    modifier onlyOwner(address _account) {
        require(msg.sender == _account, "Unauthorized Access!");
        _;
    }

    /**
     * @dev Adds a blacklisted customer
     * @param _accountNumber Customer Account Number
     * @param _name Customer Name
     */
    function addBlackListedCustomer(bytes32 _accountNumber, string _name) onlyOwner(owner) external {
        Customer memory customer;
        customer.accountNumber = _accountNumber;
        customer.name = _name;

        blackListedCustomers.push(customer);
    }
    
    /**
     * @dev Removes a blacklisted customer
     * @param _accountNumber Customer Account Number
     */
    function removeBlackListedCustomer(bytes32 _accountNumber) onlyOwner(owner) external {
        for(uint index = 0; index < blackListedCustomers.length; index++) {
            if(blackListedCustomers[index].accountNumber == _accountNumber) {
                delete blackListedCustomers[index];
                break;
            }
        }
    }

    /**
     * @dev Retrieves a black listed company based on id
     * @param _accountNumber Customer Account Number
     * @return string
     */ 
    function getBlackListedCustomer(bytes32 _accountNumber) external view returns (string) {
        for(uint index = 0; index < blackListedCustomers.length; index++) {
            if(blackListedCustomers[index].accountNumber == _accountNumber) {
                return (blackListedCustomers[index].name);
            }
        }
    }
    
    /**
     * @dev Checks whether a company is black listed or not
     * @param _accountNumber Customer Account Number
     * @return bool
     */
    function checkIsBlackListed(bytes32 _accountNumber) external view returns (bool) {
        for(uint index = 0; index < blackListedCustomers.length; index++) {
            if(blackListedCustomers[index].accountNumber == _accountNumber) {
                return true;
            }
        }
    }
}