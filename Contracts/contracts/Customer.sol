pragma solidity ^ 0.4.23;
import './ScreeningList.sol';

contract Customer {
    
    ScreeningList screeningList;
    address private owner;
    
    constructor() public {
      //screeningList = ScreeningList(_address);
      owner = msg.sender;
    }
        
	enum Status { Initiated,Pending,Approved,Rejected }
	enum CustomerType { Banking,Retail,Corporate }

	struct CustomerDetails {
	    address accountAddress;
	    bytes12 account;
		bytes32 name;
		Status kycStatus;
		CustomerType customerType;
		bool isParentCustomer;
	}
	
	CustomerDetails private customerDetails;
	
	address[] private addressesList;
	mapping(address => CustomerDetails) private customerDetailsMap;
	
	modifier onlyOwner(address _account) {
        require(msg.sender == _account, "Unauthorized Access!");
        _;
    }

	/**
    @dev Stores userDetails , KYC creation and validity dates
    @param _accountAddress - Customer Account Address
    @param _account - Customer Account Number
    @param _name - Name of the user
	@param _customerType - Customer Type of user based on CustomerType enum
	@param _kycStaus - KYC status of KYC form
	@param _isParentCustomer - checks Parent or Subsidiary Customer
    */
	function createCustomerDetails(address _accountAddress, bytes12 _account, bytes32 _name, CustomerType _customerType,Status _kycStaus,bool _isParentCustomer) external {
	    //require(screeningList.checkIsBlackListed(_accountNumber) == false, "Customer is blacklisted");
		customerDetails.accountAddress  = _accountAddress;
		customerDetails.account = _account;
		customerDetails.name = _name;
		customerDetails.customerType = _customerType;
		customerDetails.kycStatus = _kycStaus;
		customerDetails.isParentCustomer = _isParentCustomer;
		customerDetailsMap[_accountAddress] = customerDetails;
		addressesList.push(_accountAddress);
	}
	
    
     /**
	 @dev Updates user details, KYC validity dates
	 @param _name - name of the user
	 @param  _customerType - Customer Type of user based on CustomerType enum
	 @param _kycStatus - KYC status of KYC form
	 @param _isParentCustomer - checks 	Parent or Subsidiary Customer
	 */
	function updateCustomerDetails(address _accountAddress, bytes12 _account, bytes32 _name, CustomerType _customerType, Status _kycStatus, bool _isParentCustomer) onlyOwner(owner) external {
	   // require(customerList[_accountNumber].accountNumber != 0, "User doesn't exist!");
		customerDetails.accountAddress = _accountAddress;
		customerDetails.account = _account;
		customerDetails.name = _name;
		customerDetails.customerType = _customerType;
		customerDetails.kycStatus = _kycStatus;
		customerDetails.isParentCustomer = _isParentCustomer;
		customerDetailsMap[_accountAddress] = customerDetails;
	}
	
	/**
	@dev Retrieves the customer Details from blockchain
	@param _accountAddress Account address of customer
	@return bytes12 Account of customer
	@return bytes32 Name of customer
	@return CustomerType Type of Customer
	@return Status KYC Status of customer
	@return bool Parent or Subsidiary customer
	*/
	function getCustomerDetails(address _accountAddress) view external returns(bytes12, bytes32, CustomerType, Status, bool) {
		return (customerDetailsMap[_accountAddress].account, customerDetailsMap[_accountAddress].name, customerDetailsMap[_accountAddress].customerType, customerDetailsMap[_accountAddress].kycStatus, customerDetailsMap[_accountAddress].isParentCustomer);
	}
	
	/**
	@dev Retrieves the details of all customerDetailsMap
	@return address[100] Account addresses of customerDetailsMap
	@return bytes12[100] Accounts of customerDetailsMap
	@return bytes32[100] Names of customers
	@return custType[100] Types of customers
	@return status[100] Statuses of customers
	@return bool[100] Parent or Subsidiary
	*/
	function getAllCustomerDetails() view external returns(address[100] custAddress, bytes12[100] account, bytes32[100] name, CustomerType[100] custType, Status[100] status, bool[100] isParent) {
	    for(uint count = 0; count < addressesList.length; count++) {
	        custAddress[count] = addressesList[count];
	        account[count] = customerDetailsMap[addressesList[count]].account;
	        name[count] = customerDetailsMap[addressesList[count]].name;
	        custType[count] = customerDetailsMap[addressesList[count]].customerType;
	        status[count] = customerDetailsMap[addressesList[count]].kycStatus;
	        isParent[count] = customerDetailsMap[addressesList[count]].isParentCustomer;
	        return (custAddress, account, name, custType, status, isParent);
	    }
	}	
}