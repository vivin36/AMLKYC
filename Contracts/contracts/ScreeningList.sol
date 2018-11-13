pragma solidity ^0.4.23;
/**
* @title ScreeningList
*/
contract ScreeningList {

    struct Customer {
        bytes12 identificationNumber;
        bytes32 name;
    }

    address[] private blackListedCustomerAddresses;
    address[] private whiteListedCustomerAddresses;
    
    mapping(address => Customer) customerMap;
    
    address private owner = msg.sender;

    modifier onlyOwner(address _account) {
        require(msg.sender == _account, "Unauthorized Access!");
        _;
    }

    /**
     * @dev Adds a blacklisted customer
     * @param _accountAddress Customer Account Address
     * @param _identificationNumber Customer Identification Number
     * @param _name Customer Name
     */
    function addBlackListedCustomer(address _accountAddress, bytes12 _identificationNumber, bytes32 _name) onlyOwner(owner) external {
        Customer memory customer;
        customer.identificationNumber = _identificationNumber;
        customer.name = _name;
        
        blackListedCustomerAddresses.push(_accountAddress);
        customerMap[_accountAddress] = customer;
    }
    
    /**
     * @dev Removes a blacklisted customer
     * @param _accountAddress Customer Account Address
     */
    function removeBlackListedCustomer(address _accountAddress) onlyOwner(owner) external {
        for(uint index = 0; index < blackListedCustomerAddresses.length; index++) {
            if(blackListedCustomerAddresses[index] == _accountAddress) {
                delete blackListedCustomerAddresses[index];
                break;
            }
        }
    }

    /**
     * @dev Retrieves a black listed company based on id
     */ 
    function getBlackListedCustomers() external view returns (address[50] _addresses, bytes12[50] _identificationNumbers, bytes32[50] _names) {
        for(uint index = 0; index < blackListedCustomerAddresses.length; index++) {            
            _addresses[index] = blackListedCustomerAddresses[index];
            _identificationNumbers[index] = customerMap[_addresses[index]].identificationNumber;
            _names[index] = customerMap[_addresses[index]].name;            
        }
    }
    
    /**
     * @dev Checks whether a company is black listed or not
     * @param _accountAddress Customer Account address
     * @return bool
     */
    function checkIsBlackListed(address _accountAddress) external view returns (bool) {
        for(uint index = 0; index < blackListedCustomerAddresses.length; index++) {
            if(blackListedCustomerAddresses[index] == _accountAddress) {
                return true;
            }
        }
    }
    
    /**
     * @dev Adds a whitelisted customer
     * @param _accountAddress Customer Account Address
     * @param _identificationNumber Customer Identification Number
     * @param _name Customer Name
     */
    function addWhiteListedCustomer(address _accountAddress, bytes12 _identificationNumber, bytes32 _name) onlyOwner(owner) external {
        Customer memory customer;
        customer.identificationNumber  = _identificationNumber;
        customer.name = _name;

        whiteListedCustomerAddresses.push(_accountAddress);
        customerMap[_accountAddress] = customer;
    }
    
    /**
     * @dev Removes a whitelisted customer
     * @param _accountAddress Customer Account Number
     */
    function removeWhiteListedCustomer(address _accountAddress) onlyOwner(owner) external {
        for(uint index = 0; index < whiteListedCustomerAddresses.length; index++) {
            if(whiteListedCustomerAddresses[index] == _accountAddress) {
                delete whiteListedCustomerAddresses[index];
                break;
            }
        }
    }

    /**
     * @dev Retrieves a white listed company based on id
     */ 
    function getWhiteListedCustomers() external view returns (address[50] _addresses, bytes12[50] _identificationNumbers, bytes32[50] _names) {
        for(uint index = 0; index < whiteListedCustomerAddresses.length; index++) {
            if(whiteListedCustomerAddresses[index] != 0x0) {
                _addresses[index] = whiteListedCustomerAddresses[index];
                _identificationNumbers[index] = customerMap[_addresses[index]].identificationNumber;
                _names[index] = customerMap[_addresses[index]].name;
            }
        }
    }
    
    /**
     * @dev Checks whether a company is white listed or not
     * @param _accountAddress Customer Account Address
     * @return bool
     */
    function checkIsWhiteListed(address _accountAddress) external view returns (bool) {
        for(uint index = 0; index < whiteListedCustomerAddresses.length; index++) {
            if(whiteListedCustomerAddresses[index] == _accountAddress) {
                return true;
            }
        }
    }
    
    /**
     * @dev Create details of multiple blacklisted customers
     * @param _addresses Customer account addresses
     * @param _identificationNumbers identification numbers
     * @param _names Customer names
     */
    function addBlackListedCustomersBatch(address[] _addresses, bytes12[] _identificationNumbers, bytes32[] _names) onlyOwner(owner) external {
        for(uint index = 0; index < _addresses.length; index++) {
            customerMap[_addresses[index]].identificationNumber = _identificationNumbers[index];
            customerMap[_addresses[index]].name = _names[index];
            blackListedCustomerAddresses.push(_addresses[index]);
        }
    }
    
    /**
     * @dev Create details of multiple whitelisted customers
     * @param _addresses Customer account addresses
     * @param _identificationNumbers identification numbers
     * @param _names Customer names
     */
    function addWhiteListedCustomersBatch(address[] _addresses, bytes12[] _identificationNumbers, bytes32[] _names) onlyOwner(owner) external {
        for(uint index = 0; index < _addresses.length; index++) {
            customerMap[_addresses[index]].identificationNumber = _identificationNumbers[index];
            customerMap[_addresses[index]].name = _names[index];
            whiteListedCustomerAddresses.push(_addresses[index]);
        }
    }
}