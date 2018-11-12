pragma solidity ^0.4.23;
contract Customer {
    
    address private owner;
    
    constructor() public {
      owner = msg.sender;
    }
    
    enum CustomerType { Banking,Retail,Corporate }
    
    struct CustomerDetails {
        bytes12 account;
		bytes32 name;
		CustomerType customerType;
		bool isParentCustomer;
    }
    
    address[] private addressesList;
	mapping(address => CustomerDetails) private customerDetailsMap;
	
	modifier onlyOwner(address _account) {
        require(msg.sender == _account, "Unauthorized Access!");
        _;
    }
    
    /**
    @dev Creates details of customers
    @param _accountAddress - Etheruem account address of customer
    @param _account - Account of customer
    @param _name - Name of the customer
	@param _customerType - Type of customer
	@param _isParentCustomer - Parent or subsidiary customer
    */
    function createCustomerDetails(address _accountAddress, bytes12 _account, bytes32 _name, CustomerType _customerType, bool _isParentCustomer) onlyOwner(owner) external {
        customerDetailsMap[_accountAddress].account = _account;
        customerDetailsMap[_accountAddress].name = _name;
        customerDetailsMap[_accountAddress].customerType = _customerType;
        customerDetailsMap[_accountAddress].isParentCustomer = _isParentCustomer;
        addressesList.push(_accountAddress);
    }
    
    /**
	 @dev Updates customer details
	 @param _accountAddress - Ethreum account address of customer
	 @param _account - Account of customer
	 @param _name - Name of customer
	 @param  _customerType - Type of customer
	 @param _isParentCustomer - Parent or subsidiary customer
	 */
    function updateCustomerDetails(address _accountAddress, bytes12 _account, bytes32 _name, CustomerType _customerType, bool _isParentCustomer) onlyOwner(owner) external {
	    customerDetailsMap[_accountAddress].account = _account;
        customerDetailsMap[_accountAddress].name = _name;
        customerDetailsMap[_accountAddress].customerType = _customerType;
        customerDetailsMap[_accountAddress].isParentCustomer = _isParentCustomer;
	}
	
    /**
	@dev Retrieves the customer details of a particular customer
	@param _accountAddress - Ethereum account address of customer
	@return bytes12 - Account of customer
	@return bytes32 - Name of customer
	@return CustomerType - Type of Customer
	@return bool - Parent or Subsidiary customer
	*/
    function getCustomerDetails(address _accountAddress) onlyOwner(owner) view external returns(bytes12, bytes32, CustomerType, bool) {
        return(customerDetailsMap[_accountAddress].account, 
               customerDetailsMap[_accountAddress].name,
               customerDetailsMap[_accountAddress].customerType,
               customerDetailsMap[_accountAddress].isParentCustomer);
    }
    
    /**
	@dev Retrieves the details of all the customers
	@return address[50] - Ethereum account addresses of customer
	@return bytes12[50] - Account of customer
	@return bytes32[50] - Names of customer
	@return CustomerType[50] - Types of customers
	@return bool[50] - Parent or Subsidiary
	*/
    function getAllCustomerDetails() onlyOwner(owner) view external returns(address[50] addresses, bytes12[50] accounts, bytes32[50] names, CustomerType[50] custTypes, bool[50] isParent) {
        for(uint index = 0; index < addressesList.length; index++) {
            addresses[index] = addressesList[index];
            accounts[index] = customerDetailsMap[addressesList[index]].account;
            names[index] = customerDetailsMap[addressesList[index]].name;
            custTypes[index] = customerDetailsMap[addressesList[index]].customerType;
            isParent[index] = customerDetailsMap[addressesList[index]].isParentCustomer;
        }
    }
    
    /**
     @dev Creates details of multiple customers 
     @param _addresses Ethereum account addresses of customers
     @param _accounts Accounts of customers
     @param _names Names of customers
     @param _custTypes Customer Types
     @param _isParent Parent or Subsidiary
     */
    function createCustomerDetailsBatch(address[] _addresses, bytes12[] _accounts, bytes32[] _names, CustomerType[] _custTypes, bool[] _isParent) onlyOwner(owner) external {
        for(uint index = 0; index < _addresses.length; index++) {
            customerDetailsMap[_addresses[index]].account = _accounts[index];
            customerDetailsMap[_addresses[index]].name = _names[index];
            customerDetailsMap[_addresses[index]].customerType = _custTypes[index];
            customerDetailsMap[_addresses[index]].isParentCustomer = _isParent[index];
            addressesList.push(_addresses[index]);
        }
    }
}