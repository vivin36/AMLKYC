pragma solidity ^ 0.4 .23;
import './ScreeningList.sol';

contract KYC {
    
    ScreeningList screeningList;
    address private owner;
    
    constructor(address _address) public {
      screeningList = ScreeningList(_address);
      owner = msg.sender;
    }
        
	enum Status { Initiated,Pending,Approved,Rejected }
	enum CustomerType { Banking,Retail,Corporate }

	struct KYCValidation {
		uint256 validatedDate;
		uint256 validationEndDate;
	}

	struct user {
		bytes32 accountNumber;
		string name;
		Status KYCStatus;
		CustomerType customerType;
		bool isParentCustomer;
	}
	
	mapping(bytes32 => user) private userList;
	mapping(bytes32 => KYCValidation) private validationDetails;

	modifier onlyOwner(address _account) {
        require(msg.sender == _account, "Unauthorized Access!");
        _;
    }

	/**
    @dev Stores userDetails , KYC creation and validity dates
    @param _accountNumber - the account Number  of user
    @param _name - name of the user
	@param _customerType - Customer Type of user based on CustomerType enum
	@param _KYCStaus - KYC status of KYC form
	@param _isParentCustomer - checks 	Parent or Subsidiary Customer
	@param _validationEndDate - Validation End date for KYC details
    */
	function createKYC(bytes32 _accountNumber, string _name, CustomerType _customerType,Status _KYCStaus,bool _isParentCustomer,uint256 _validationEndDate) onlyOwner(owner) public {
	    require(screeningList.checkIsBlackListed(_accountNumber) == false, "Customer is blacklisted");
		userList[_accountNumber].accountNumber = _accountNumber;
		userList[_accountNumber].name = _name;
		userList[_accountNumber].customerType = _customerType;
		userList[_accountNumber].KYCStatus = _KYCStaus;
		userList[_accountNumber].isParentCustomer = _isParentCustomer;
		validationDetails[_accountNumber].validatedDate = now;
		validationDetails[_accountNumber].validationEndDate = _validationEndDate;
		
	}
	
    
     /**
	 @dev Updates user details, KYC validity dates
	 @param _accountNumber - User unique account Number
	 @param _name - name of the user
	 @param  _customerType - Customer Type of user based on CustomerType enum
	 @param _KYCStaus - KYC status of KYC form
	 @param _isParentCustomer - checks 	Parent or Subsidiary Customer
	 @param _validationEndDate - Validation End date for KYC details
	 */
	function updateKYC(bytes32 _accountNumber, string _name,CustomerType _customerType,Status _KYCStaus,bool _isParentCustomer,uint256 _validationEndDate) onlyOwner(owner) external {
	    require(userList[_accountNumber].accountNumber != 0, "User doesn't exist!");
	    userList[_accountNumber].accountNumber = _accountNumber;
		userList[_accountNumber].name = _name;
		userList[_accountNumber].customerType = _customerType;
		userList[_accountNumber].KYCStatus = _KYCStaus;
		userList[_accountNumber].isParentCustomer = _isParentCustomer;
		validationDetails[_accountNumber].validationEndDate = _validationEndDate;
	}
	
	/**
	@dev Retrieves the user Details and KYC validated Infromation from blockchain
    @param _accountNumber - User unique accountNumber
	*/
	function getDetailsByID(bytes32 _accountNumber) view public returns(string, CustomerType, Status, bool, uint256, uint256) {
		return (userList[_accountNumber].name, userList[_accountNumber].customerType, userList[_accountNumber].KYCStatus, userList[_accountNumber].isParentCustomer, validationDetails[_accountNumber].validatedDate, validationDetails[_accountNumber].validationEndDate);
	}
	
	/**
	@dev Retrieves the validty of the user by _ccount Number
    @param _accountNumber - User unique account Number
	*/
	function getValidity(bytes32 _accountNumber) view public returns(uint256) {
		return validationDetails[_accountNumber].validationEndDate;
	}
	
}