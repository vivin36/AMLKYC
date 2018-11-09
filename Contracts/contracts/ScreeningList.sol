pragma solidity ^0.4.23;
/**
 * @title ScreeningList
 */
contract ScreeningList {

    struct Customer {
        bytes12 accountNumber;
        bytes32 name;
    }

    Customer[] private blackListedCustomers;
    Customer[] private whiteListedCustomers;
    
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
    function addBlackListedCustomer(bytes12 _accountNumber, bytes32 _name) onlyOwner(owner) external {
        Customer memory customer;
        customer.accountNumber = _accountNumber;
        customer.name = _name;

        blackListedCustomers.push(customer);
    }
    
    /**
     * @dev Removes a blacklisted customer
     * @param _accountNumber Customer Account Number
     */
    function removeBlackListedCustomer(bytes12 _accountNumber) onlyOwner(owner) external {
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
    function getBlackListedCustomer(bytes12 _accountNumber) external view returns (bytes32) {
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
    function checkIsBlackListed(bytes12 _accountNumber) external view returns (bool) {
        for(uint index = 0; index < blackListedCustomers.length; index++) {
            if(blackListedCustomers[index].accountNumber == _accountNumber) {
                return true;
            }
        }
    }
    
    /**
     * @dev Adds a whitelisted customer
     * @param _accountNumber Customer Account Number
     * @param _name Customer Name
     */
    function addWhiteListedCustomer(bytes12 _accountNumber, bytes32 _name) onlyOwner(owner) external {
        Customer memory customer;
        customer.accountNumber = _accountNumber;
        customer.name = _name;

        whiteListedCustomers.push(customer);
    }
    
    /**
     * @dev Removes a whitelisted customer
     * @param _accountNumber Customer Account Number
     */
    function removeWhiteListedCustomer(bytes12 _accountNumber) onlyOwner(owner) external {
        for(uint index = 0; index < blackListedCustomers.length; index++) {
            if(whiteListedCustomers[index].accountNumber == _accountNumber) {
                delete whiteListedCustomers[index];
                break;
            }
        }
    }

    /**
     * @dev Retrieves a white listed company based on id
     * @param _accountNumber Customer Account Number
     * @return string
     */ 
    function getWhiteListedCustomer(bytes12 _accountNumber) external view returns (bytes32) {
        for(uint index = 0; index < whiteListedCustomers.length; index++) {
            if(whiteListedCustomers[index].accountNumber == _accountNumber) {
                return (whiteListedCustomers[index].name);
            }
        }
    }
    
    /**
     * @dev Checks whether a company is white listed or not
     * @param _accountNumber Customer Account Number
     * @return bool
     */
    function checkIsWhiteListed(bytes12 _accountNumber) external view returns (bool) {
        for(uint index = 0; index < whiteListedCustomers.length; index++) {
            if(whiteListedCustomers[index].accountNumber == _accountNumber) {
                return true;
            }
        }
    }
}