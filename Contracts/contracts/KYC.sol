pragma solidity ^ 0.4 .23;

contract KYC {

	struct KYCValidation {
		uint256 validatedDate;
		uint256 validationEndDate;
	}
	    
    address private owner = msg.sender;

	struct user {
		bytes32 id;
		string name;
		string userAddress;
		bytes32 dob;
		bytes32 gender;
	}
	mapping(bytes32 => user) public userList;
	mapping(bytes32 => KYCValidation) public validationDetails;

	 modifier onlyOwner(address _account) {
        require(msg.sender == _account, "Unauthorized Access!");
        _;
    }

	/**
    @dev Stores userDetails , KYC creation and validity dates
    @param _id - the userName of user
    @param _name - name of the user
	@param _userAddress - Physical address of the user
	@param _dob - DOB of the user
	@param _gender - gender  of the user
    */
	function createKYC(bytes32 _id, string _name, string _userAddress, bytes32 _dob, bytes32 _gender,uint256 _validationEndDate) onlyOwner(owner) public {
		userList[_id].id = _id;
		userList[_id].name = _name;
		userList[_id].userAddress = _userAddress;
		userList[_id].dob = _dob;
		userList[_id].gender = _gender;
		validationDetails[_id].validatedDate = now;
		validationDetails[_id].validationEndDate = _validationEndDate;

	}

	/**
	@dev Retrieves the user Details and KYC validated Infromation from blockchain
    @param _id - User ID
	*/
	function getDetailsByID(bytes32 _id) view public returns(string, string, bytes32, bytes32, uint256, uint256) {
		return (userList[_id].name, userList[_id].userAddress, userList[_id].dob, userList[_id].gender, validationDetails[_id].validatedDate, validationDetails[_id].validationEndDate);
	}

	/**
	@dev Retrieves the validty of the user by ID
    @param _id - User ID
	*/
	function getValidity(bytes32 _id) view public returns(uint256) {
		return validationDetails[_id].validationEndDate;
	}

}