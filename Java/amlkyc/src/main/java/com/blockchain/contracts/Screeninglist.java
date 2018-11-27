package com.blockchain.contracts;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Screeninglist extends Contract {
    private static final String BINARY = "608060405260038054600160a060020a0319163317905534801561002257600080fd5b50610fef806100326000396000f3006080604052600436106100e55763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630fdbaaeb81146100ea57806311d230361461012457806313fcd5f3146101455780631576d80a1461019357806318771902146101cb5780631e6934781461020057806320c695791461022757806322b301e914610248578063462ca0ed146102ad5780635ccce7c4146103535780635f297e0d1461037457806364ee32ed1461039b578063a5fc4b9d146103b0578063d98b70f5146103c5578063dce574a0146103da578063f78ffaa2146103ef575b600080fd5b3480156100f657600080fd5b506101226024600480358281019290820135918135808301929082013591604435918201910135610404565b005b34801561013057600080fd5b50610122600160a060020a036004351661057c565b34801561015157600080fd5b5061015a61065c565b604051808261064080838360005b83811015610180578181015183820152602001610168565b5050505090500191505060405180910390f35b34801561019f57600080fd5b5061012260246004803582810192908201359181358083019290820135916044359182019101356106c6565b3480156101d757600080fd5b506101ec600160a060020a0360043516610834565b604080519115158252519081900360200190f35b34801561020c57600080fd5b50610122600160a060020a036004351660243560443561088d565b34801561023357600080fd5b50610122600160a060020a0360043516610960565b34801561025457600080fd5b5061025d610a10565b60408051602080825283518183015283519192839290830191858101910280838360005b83811015610299578181015183820152602001610281565b505050509050019250505060405180910390f35b3480156102b957600080fd5b506102c2610a72565b604051808461064080838360005b838110156102e85781810151838201526020016102d0565b5050505090500183603260200280838360005b838110156103135781810151838201526020016102fb565b5050505090500182603260200280838360005b8381101561033e578181015183820152602001610326565b50505050905001935050505060405180910390f35b34801561035f57600080fd5b506101ec600160a060020a0360043516610b7f565b34801561038057600080fd5b50610122600160a060020a0360043516602435604435610bd2565b3480156103a757600080fd5b5061025d610ca6565b3480156103bc57600080fd5b5061015a610d06565b3480156103d157600080fd5b506102c2610d6b565b3480156103e657600080fd5b5061015a610e9b565b3480156103fb57600080fd5b5061015a610f03565b600354600090600160a060020a0316338114610458576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610fa4833981519152604482015290519081900360640190fd5b600091505b868210156105725785858381811061047157fe5b60200291909101359050600260008a8a8681811061048b57fe5b60209081029290920135600160a060020a0316835250810191909152604001600020558383838181106104ba57fe5b60200291909101359050600260008a8a868181106104d457fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060010181600019169055506001888884818110151561051f57fe5b83546001808201865560009586526020958690209091018054929095029390930135600160a060020a031673ffffffffffffffffffffffffffffffffffffffff199091161790925592909201915061045d565b5050505050505050565b600354600090600160a060020a03163381146105d0576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610fa4833981519152604482015290519081900360640190fd5b600091505b6001548210156106575782600160a060020a03166001838154811015156105f857fe5b600091825260209091200154600160a060020a0316141561064c57600180548390811061062157fe5b6000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff19169055610657565b6001909101906105d5565b505050565b610664610f6c565b60005b6001548110156106c2576002600060018381548110151561068457fe5b6000918252602080832090910154600160a060020a031683528201929092526040019020548282603281106106b557fe5b6020020152600101610667565b5090565b600354600090600160a060020a031633811461071a576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610fa4833981519152604482015290519081900360640190fd5b600091505b868210156105725785858381811061073357fe5b60200291909101359050600260008a8a8681811061074d57fe5b60209081029290920135600160a060020a03168352508101919091526040016000205583838381811061077c57fe5b60200291909101359050600260008a8a8681811061079657fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a031681526020019081526020016000206001018160001916905550600088888481811015156107e157fe5b83546001808201865560009586526020958690209091018054929095029390930135600160a060020a031673ffffffffffffffffffffffffffffffffffffffff199091161790925592909201915061071f565b6000805b6000548110156108875782600160a060020a031660008281548110151561085b57fe5b600091825260209091200154600160a060020a0316141561087f5760019150610887565b600101610838565b50919050565b610895610f8c565b600354600160a060020a03163381146108e6576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610fa4833981519152604482015290519081900360640190fd5b5091825260208083019182526001805480820182557fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf6018054600160a060020a0390961673ffffffffffffffffffffffffffffffffffffffff19909616861790556000948552600290915260409093209151825551910155565b600354600090600160a060020a03163381146109b4576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610fa4833981519152604482015290519081900360640190fd5b600091505b6000548210156106575782600160a060020a03166000838154811015156109dc57fe5b600091825260209091200154600160a060020a03161415610a0557600080548390811061062157fe5b6001909101906109b9565b60606000805480602002602001604051908101604052809291908181526020018280548015610a6857602002820191906000526020600020905b8154600160a060020a03168152600190910190602001808311610a4a575b5050505050905090565b610a7a610f6c565b610a82610f6c565b610a8a610f6c565b60005b600054811015610b79576000805482908110610aa557fe5b600091825260209091200154600160a060020a0316848260328110610ac657fe5b600160a060020a03909216602092909202015260026000858360328110610ae957fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600001548382603281101515610b2057fe5b602002015260026000858360328110610b3557fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600101548282603281101515610b6c57fe5b6020020152600101610a8d565b50909192565b6000805b6001548110156108875782600160a060020a0316600182815481101515610ba657fe5b600091825260209091200154600160a060020a03161415610bca5760019150610887565b600101610b83565b610bda610f8c565b600354600160a060020a0316338114610c2b576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610fa4833981519152604482015290519081900360640190fd5b50918252602080830191825260008054600181810183557f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e5639091018054600160a060020a0390971673ffffffffffffffffffffffffffffffffffffffff19909716871790559481526002909152604090209151825551910155565b60606001805480602002602001604051908101604052809291908181526020018280548015610a6857602002820191906000526020600020908154600160a060020a03168152600190910190602001808311610a4a575050505050905090565b610d0e610f6c565b60005b6000548110156106c257600260008083815481101515610d2d57fe5b6000918252602080832090910154600160a060020a03168352820192909252604001902054828260328110610d5e57fe5b6020020152600101610d11565b610d73610f6c565b610d7b610f6c565b610d83610f6c565b60005b600154811015610b79576001805482908110610d9e57fe5b600091825260209091200154600160a060020a031615610e93576001805482908110610dc657fe5b600091825260209091200154600160a060020a0316848260328110610de757fe5b600160a060020a03909216602092909202015260026000858360328110610e0a57fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600001548382603281101515610e4157fe5b602002015260026000858360328110610e5657fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600101548282603281101515610e8d57fe5b60200201525b600101610d86565b610ea3610f6c565b60005b6000548110156106c257600260008083815481101515610ec257fe5b6000918252602080832090910154600160a060020a03168352820192909252604001902060010154828260328110610ef657fe5b6020020152600101610ea6565b610f0b610f6c565b60005b6001548110156106c25760026000600183815481101515610f2b57fe5b6000918252602080832090910154600160a060020a03168352820192909252604001902060010154828260328110610f5f57fe5b6020020152600101610f0e565b610640604051908101604052806032906020820280388339509192915050565b6040805180820190915260008082526020820152905600556e617574686f72697a65642041636365737321000000000000000000000000a165627a7a72305820073d04c4dbbd60563e680eeedc446139cd2a9a1fcac0e0098561d73252be95450029";

    public static final String FUNC_ADDWHITELISTEDCUSTOMERSBATCH = "addWhiteListedCustomersBatch";

    public static final String FUNC_REMOVEWHITELISTEDCUSTOMER = "removeWhiteListedCustomer";

    public static final String FUNC_GETALLWHITELISTEDCUSTOMERIDENTIFICATIONNUMBER = "getAllWhiteListedCustomerIdentificationNumber";

    public static final String FUNC_ADDBLACKLISTEDCUSTOMERSBATCH = "addBlackListedCustomersBatch";

    public static final String FUNC_CHECKISBLACKLISTED = "checkIsBlackListed";

    public static final String FUNC_ADDWHITELISTEDCUSTOMER = "addWhiteListedCustomer";

    public static final String FUNC_REMOVEBLACKLISTEDCUSTOMER = "removeBlackListedCustomer";

    public static final String FUNC_GETALLBLACKLISTEDCUSTOMERADDRESS = "getAllBlackListedCustomerAddress";

    public static final String FUNC_GETBLACKLISTEDCUSTOMERS = "getBlackListedCustomers";

    public static final String FUNC_CHECKISWHITELISTED = "checkIsWhiteListed";

    public static final String FUNC_ADDBLACKLISTEDCUSTOMER = "addBlackListedCustomer";

    public static final String FUNC_GETALLWHITELISTEDCUSTOMERADDRESS = "getAllWhiteListedCustomerAddress";

    public static final String FUNC_GETALLBLACKLISTEDCUSTOMERIDENTIFICATIONNUMBER = "getAllBlackListedCustomerIdentificationNumber";

    public static final String FUNC_GETWHITELISTEDCUSTOMERS = "getWhiteListedCustomers";

    public static final String FUNC_GETALLBLACKLISTEDCUSTOMERNAMES = "getAllBlackListedCustomerNames";

    public static final String FUNC_GETALLWHITELISTEDCUSTOMERNAMES = "getAllWhiteListedCustomerNames";

    protected Screeninglist(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Screeninglist(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> addWhiteListedCustomersBatch(List<String> _addresses, List<byte[]> _identificationNumbers, List<byte[]> _names) {
        final Function function = new Function(
                FUNC_ADDWHITELISTEDCUSTOMERSBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(_addresses, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_identificationNumbers, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_names, org.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeWhiteListedCustomer(String _accountAddress) {
        final Function function = new Function(
                FUNC_REMOVEWHITELISTEDCUSTOMER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getAllWhiteListedCustomerIdentificationNumber() {
        final Function function = new Function(FUNC_GETALLWHITELISTEDCUSTOMERIDENTIFICATIONNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Bytes32>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addBlackListedCustomersBatch(List<String> _addresses, List<byte[]> _identificationNumbers, List<byte[]> _names) {
        final Function function = new Function(
                FUNC_ADDBLACKLISTEDCUSTOMERSBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(_addresses, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_identificationNumbers, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_names, org.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> checkIsBlackListed(String _accountAddress) {
        final Function function = new Function(FUNC_CHECKISBLACKLISTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> addWhiteListedCustomer(String _accountAddress, byte[] _identificationNumber, byte[] _name) {
        final Function function = new Function(
                FUNC_ADDWHITELISTEDCUSTOMER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress), 
                new org.web3j.abi.datatypes.generated.Bytes32(_identificationNumber), 
                new org.web3j.abi.datatypes.generated.Bytes32(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeBlackListedCustomer(String _accountAddress) {
        final Function function = new Function(
                FUNC_REMOVEBLACKLISTEDCUSTOMER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getAllBlackListedCustomerAddress() {
        final Function function = new Function(FUNC_GETALLBLACKLISTEDCUSTOMERADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<Tuple3<List<String>, List<byte[]>, List<byte[]>>> getBlackListedCustomers() {
        final Function function = new Function(FUNC_GETBLACKLISTEDCUSTOMERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Address>>() {}, new TypeReference<StaticArray<Bytes32>>() {}, new TypeReference<StaticArray<Bytes32>>() {}));
        return new RemoteCall<Tuple3<List<String>, List<byte[]>, List<byte[]>>>(
                new Callable<Tuple3<List<String>, List<byte[]>, List<byte[]>>>() {
                    @Override
                    public Tuple3<List<String>, List<byte[]>, List<byte[]>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<List<String>, List<byte[]>, List<byte[]>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                                convertToNative((List<Bytes32>) results.get(2).getValue()));
                    }
                });
    }

    public RemoteCall<Boolean> checkIsWhiteListed(String _accountAddress) {
        final Function function = new Function(FUNC_CHECKISWHITELISTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> addBlackListedCustomer(String _accountAddress, byte[] _identificationNumber, byte[] _name) {
        final Function function = new Function(
                FUNC_ADDBLACKLISTEDCUSTOMER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress), 
                new org.web3j.abi.datatypes.generated.Bytes32(_identificationNumber), 
                new org.web3j.abi.datatypes.generated.Bytes32(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getAllWhiteListedCustomerAddress() {
        final Function function = new Function(FUNC_GETALLWHITELISTEDCUSTOMERADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getAllBlackListedCustomerIdentificationNumber() {
        final Function function = new Function(FUNC_GETALLBLACKLISTEDCUSTOMERIDENTIFICATIONNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Bytes32>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<Tuple3<List<String>, List<byte[]>, List<byte[]>>> getWhiteListedCustomers() {
        final Function function = new Function(FUNC_GETWHITELISTEDCUSTOMERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Address>>() {}, new TypeReference<StaticArray<Bytes32>>() {}, new TypeReference<StaticArray<Bytes32>>() {}));
        return new RemoteCall<Tuple3<List<String>, List<byte[]>, List<byte[]>>>(
                new Callable<Tuple3<List<String>, List<byte[]>, List<byte[]>>>() {
                    @Override
                    public Tuple3<List<String>, List<byte[]>, List<byte[]>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<List<String>, List<byte[]>, List<byte[]>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                                convertToNative((List<Bytes32>) results.get(2).getValue()));
                    }
                });
    }

    public RemoteCall<List> getAllBlackListedCustomerNames() {
        final Function function = new Function(FUNC_GETALLBLACKLISTEDCUSTOMERNAMES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Bytes32>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getAllWhiteListedCustomerNames() {
        final Function function = new Function(FUNC_GETALLWHITELISTEDCUSTOMERNAMES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Bytes32>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public static RemoteCall<Screeninglist> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Screeninglist.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Screeninglist> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Screeninglist.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Screeninglist load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Screeninglist(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Screeninglist load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Screeninglist(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
