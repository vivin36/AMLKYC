pragma solidity ^0.4.23;
import "./Customer.sol";

contract CustomerRepo {
    
    mapping(bytes32 => Customer) private customerMap;
    address[100] private customerAddresses;
    uint private count = 0;
    
    /**
     * @dev Creates a new customer
     * @param _accountNumber Customer Account Number
     * @return address Customer Contract Address
     */
    function createCustomer(bytes32 _accountNumber) external returns (address) {
        Customer customer = new Customer();
        customerMap[_accountNumber] = customer;
        customerAddresses[count++] = customer;
        return customer;
    }
    
    /**
     * @dev Retrieves the customer address based on account number
     * @param _accountNumber Customer Account Number
     * @return address Customer Contract Address
     */
    function getCustomerAddress(bytes32 _accountNumber) external view returns (address) {
        return customerMap[_accountNumber];
    }
    
    /**
     * @dev Retrieves all the customer contract addresses
     * @return address[] 
     */
    function getAllAddresses() external view returns(address[100]) {
        return customerAddresses;
    }
}