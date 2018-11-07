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

	struct KYCValidation {
		uint256 validatedDate;
		uint256 validationEndDate;
	}

	struct CustomerDetails {
		string name;
		Status KYCStatus;
		CustomerType customerType;
		bool isParentCustomer;
	}
	
	CustomerDetails private customerDetails;
	KYCValidation private validationDetails;

	modifier onlyOwner(address _account) {
        require(msg.sender == _account, "Unauthorized Access!");
        _;
    }

	/**
    @dev Stores userDetails , KYC creation and validity dates
    @param _name - name of the user
	@param _customerType - Customer Type of user based on CustomerType enum
	@param _KYCStaus - KYC status of KYC form
	@param _isParentCustomer - checks Parent or Subsidiary Customer
	@param _validationEndDate - Validation End date for KYC details
    */
	function createCustomerDetails(string _name, CustomerType _customerType,Status _KYCStaus,bool _isParentCustomer,uint256 _validationEndDate) external {
	    //require(screeningList.checkIsBlackListed(_accountNumber) == false, "Customer is blacklisted");
		customerDetails.name = _name;
		customerDetails.customerType = _customerType;
		customerDetails.KYCStatus = _KYCStaus;
		customerDetails.isParentCustomer = _isParentCustomer;
		validationDetails.validatedDate = now;
		validationDetails.validationEndDate = _validationEndDate;
		
	}
	
    
     /**
	 @dev Updates user details, KYC validity dates
	 @param _name - name of the user
	 @param  _customerType - Customer Type of user based on CustomerType enum
	 @param _KYCStatus - KYC status of KYC form
	 @param _isParentCustomer - checks 	Parent or Subsidiary Customer
	 @param _validationEndDate - Validation End date for KYC details
	 */
	function updateCustomerDetails(string _name,CustomerType _customerType,Status _KYCStatus,bool _isParentCustomer,uint256 _validationEndDate) onlyOwner(owner) external {
	   // require(customerList[_accountNumber].accountNumber != 0, "User doesn't exist!");
		customerDetails.name = _name;
		customerDetails.customerType = _customerType;
		customerDetails.KYCStatus = _KYCStatus;
		customerDetails.isParentCustomer = _isParentCustomer;
		validationDetails.validationEndDate = _validationEndDate;
	}
	
	/**
	@dev Retrieves the user Details and KYC validated Infromation from blockchain
	*/
	function getCustomerDetails() view external returns(string, CustomerType, Status, bool, uint256, uint256) {
		return (customerDetails.name, customerDetails.customerType, customerDetails.KYCStatus, customerDetails.isParentCustomer, validationDetails.validatedDate, validationDetails.validationEndDate);
	}
	
	/**
	@dev Retrieves the validty of the user by _ccount Number
	*/
	function getValidity() view external returns(uint256) {
		return validationDetails.validationEndDate;
	}
	
}