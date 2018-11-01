pragma solidity ^0.4.23;
/**
 * @title ScreeningList
 */
contract ScreeningList {

    struct Company {
        bytes8 id;
        string name;
        bytes10 tanNumber;
    }

    Company[] private whiteListedCompanies;
    Company[] private blackListedCompanies;
    
    address private owner = msg.sender;

    modifier onlyOwner(address _account) {
        require(msg.sender == _account, "Unauthorized Access!");
        _;
    }

    /**
     * @dev Adds a whitelisted company
     * @param _id Company ID
     * @param _name Company name
     * @param _tanNumber Company TAN
     */
    function addWhiteListedCompany(bytes8 _id, string _name, bytes10 _tanNumber) onlyOwner(owner) external {
        Company memory company;
        company.id = _id;
        company.name = _name;
        company.tanNumber = _tanNumber;

        whiteListedCompanies.push(company);
    }

    /**
     * @dev Adds a blacklisted company
     * @param _id Company ID
     * @param _name Company Name
     * @param _tanNumber Company TAN
     */
    function addBlackListedCompany(bytes8 _id, string _name, bytes10 _tanNumber) onlyOwner(owner) external {
        Company memory company;
        company.id = _id;
        company.name = _name;
        company.tanNumber = _tanNumber;

        blackListedCompanies.push(company);
    }

    /**
     * @dev Blacklists a company
     * @param _id Company ID
     */
    function blacklistCompany(bytes8 _id) onlyOwner(owner) external {        
        Company memory company;
        for(uint index = 0; index < whiteListedCompanies.length; index++) {
            if(whiteListedCompanies[index].id == _id) {
                company = whiteListedCompanies[index];
                delete whiteListedCompanies[index];
                blackListedCompanies.push(company);
                break;
            }
        }        
    }

    /**
     * @dev Performs a business operation
     * @param _id Company ID
     * @return Returns 0 if successful, else 1
     */
    function performOperation(bytes8 _id) onlyOwner(owner) external returns (uint) {
        //Perform business opeartion
        return 0;
    }
    
    /**
     * @dev Retrieves a white listed company based on id
     * @param _id Company ID
     * @return string, bytes10
     */ 
    
    function getWhiteListedCompany(bytes8 _id) external view returns (string, bytes10) {
        for(uint index = 0; index < whiteListedCompanies.length; index++) {
            if(whiteListedCompanies[index].id == _id) {
                return (whiteListedCompanies[index].name, whiteListedCompanies[index].tanNumber);
            }
        }
    }
    
    /**
     * @dev Retrieves a black listed company based on id
     * @param _id Company ID
     * @return string, bytes10
     */ 
    
    function getBlackListedCompany(bytes8 _id) external view returns (string, bytes10) {
        for(uint index = 0; index < blackListedCompanies.length; index++) {
            if(blackListedCompanies[index].id == _id) {
                return (blackListedCompanies[index].name, blackListedCompanies[index].tanNumber);
            }
        }
    }
}