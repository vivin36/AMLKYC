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
    private static final String BINARY = "608060405260038054600160a060020a0319163317905534801561002257600080fd5b50610d96806100326000396000f3006080604052600436106100b95763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630fdbaaeb81146100be57806311d23036146100f85780631576d80a1461011957806318771902146101515780631e6934781461018657806320c69579146101ad57806322b301e9146101ce578063462ca0ed146102335780635ccce7c4146102d95780635f297e0d146102fa57806364ee32ed14610321578063d98b70f514610336575b600080fd5b3480156100ca57600080fd5b506100f6602460048035828101929082013591813580830192908201359160443591820191013561034b565b005b34801561010457600080fd5b506100f6600160a060020a03600435166104c3565b34801561012557600080fd5b506100f660246004803582810192908201359181358083019290820135916044359182019101356105a3565b34801561015d57600080fd5b50610172600160a060020a0360043516610711565b604080519115158252519081900360200190f35b34801561019257600080fd5b506100f6600160a060020a036004351660243560443561076a565b3480156101b957600080fd5b506100f6600160a060020a036004351661083d565b3480156101da57600080fd5b506101e36108ed565b60408051602080825283518183015283519192839290830191858101910280838360005b8381101561021f578181015183820152602001610207565b505050509050019250505060405180910390f35b34801561023f57600080fd5b5061024861094f565b604051808461064080838360005b8381101561026e578181015183820152602001610256565b5050505090500183603260200280838360005b83811015610299578181015183820152602001610281565b5050505090500182603260200280838360005b838110156102c45781810151838201526020016102ac565b50505050905001935050505060405180910390f35b3480156102e557600080fd5b50610172600160a060020a0360043516610a5c565b34801561030657600080fd5b506100f6600160a060020a0360043516602435604435610aaf565b34801561032d57600080fd5b506101e3610b83565b34801561034257600080fd5b50610248610be3565b600354600090600160a060020a031633811461039f576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610d4b833981519152604482015290519081900360640190fd5b600091505b868210156104b9578585838181106103b857fe5b60200291909101359050600260008a8a868181106103d257fe5b60209081029290920135600160a060020a03168352508101919091526040016000205583838381811061040157fe5b60200291909101359050600260008a8a8681811061041b57fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060010181600019169055506001888884818110151561046657fe5b83546001808201865560009586526020958690209091018054929095029390930135600160a060020a031673ffffffffffffffffffffffffffffffffffffffff19909116179092559290920191506103a4565b5050505050505050565b600354600090600160a060020a0316338114610517576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610d4b833981519152604482015290519081900360640190fd5b600091505b60015482101561059e5782600160a060020a031660018381548110151561053f57fe5b600091825260209091200154600160a060020a0316141561059357600180548390811061056857fe5b6000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff1916905561059e565b60019091019061051c565b505050565b600354600090600160a060020a03163381146105f7576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610d4b833981519152604482015290519081900360640190fd5b600091505b868210156104b95785858381811061061057fe5b60200291909101359050600260008a8a8681811061062a57fe5b60209081029290920135600160a060020a03168352508101919091526040016000205583838381811061065957fe5b60200291909101359050600260008a8a8681811061067357fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a031681526020019081526020016000206001018160001916905550600088888481811015156106be57fe5b83546001808201865560009586526020958690209091018054929095029390930135600160a060020a031673ffffffffffffffffffffffffffffffffffffffff19909116179092559290920191506105fc565b6000805b6000548110156107645782600160a060020a031660008281548110151561073857fe5b600091825260209091200154600160a060020a0316141561075c5760019150610764565b600101610715565b50919050565b610772610d13565b600354600160a060020a03163381146107c3576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610d4b833981519152604482015290519081900360640190fd5b5091825260208083019182526001805480820182557fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf6018054600160a060020a0390961673ffffffffffffffffffffffffffffffffffffffff19909616861790556000948552600290915260409093209151825551910155565b600354600090600160a060020a0316338114610891576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610d4b833981519152604482015290519081900360640190fd5b600091505b60005482101561059e5782600160a060020a03166000838154811015156108b957fe5b600091825260209091200154600160a060020a031614156108e257600080548390811061056857fe5b600190910190610896565b6060600080548060200260200160405190810160405280929190818152602001828054801561094557602002820191906000526020600020905b8154600160a060020a03168152600190910190602001808311610927575b5050505050905090565b610957610d2a565b61095f610d2a565b610967610d2a565b60005b600054811015610a5657600080548290811061098257fe5b600091825260209091200154600160a060020a03168482603281106109a357fe5b600160a060020a039092166020929092020152600260008583603281106109c657fe5b6020020151600160a060020a0316600160a060020a031681526020019081526020016000206000015483826032811015156109fd57fe5b602002015260026000858360328110610a1257fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600101548282603281101515610a4957fe5b602002015260010161096a565b50909192565b6000805b6001548110156107645782600160a060020a0316600182815481101515610a8357fe5b600091825260209091200154600160a060020a03161415610aa75760019150610764565b600101610a60565b610ab7610d13565b600354600160a060020a0316338114610b08576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610d4b833981519152604482015290519081900360640190fd5b50918252602080830191825260008054600181810183557f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e5639091018054600160a060020a0390971673ffffffffffffffffffffffffffffffffffffffff19909716871790559481526002909152604090209151825551910155565b6060600180548060200260200160405190810160405280929190818152602001828054801561094557602002820191906000526020600020908154600160a060020a03168152600190910190602001808311610927575050505050905090565b610beb610d2a565b610bf3610d2a565b610bfb610d2a565b60005b600154811015610a56576001805482908110610c1657fe5b600091825260209091200154600160a060020a031615610d0b576001805482908110610c3e57fe5b600091825260209091200154600160a060020a0316848260328110610c5f57fe5b600160a060020a03909216602092909202015260026000858360328110610c8257fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600001548382603281101515610cb957fe5b602002015260026000858360328110610cce57fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600101548282603281101515610d0557fe5b60200201525b600101610bfe565b604080518082019091526000808252602082015290565b6106406040519081016040528060329060208202803883395091929150505600556e617574686f72697a65642041636365737321000000000000000000000000a165627a7a72305820f76963907d5345fe382913aeebc42d79a58ad6b6f93fd710e94267543dca18c70029";

    public static final String FUNC_ADDWHITELISTEDCUSTOMERSBATCH = "addWhiteListedCustomersBatch";

    public static final String FUNC_REMOVEWHITELISTEDCUSTOMER = "removeWhiteListedCustomer";

    public static final String FUNC_ADDBLACKLISTEDCUSTOMERSBATCH = "addBlackListedCustomersBatch";

    public static final String FUNC_CHECKISBLACKLISTED = "checkIsBlackListed";

    public static final String FUNC_ADDWHITELISTEDCUSTOMER = "addWhiteListedCustomer";

    public static final String FUNC_REMOVEBLACKLISTEDCUSTOMER = "removeBlackListedCustomer";

    public static final String FUNC_GETALLBLACKLISTEDCUSTOMERADDRESS = "getAllBlackListedCustomerAddress";

    public static final String FUNC_GETBLACKLISTEDCUSTOMERS = "getBlackListedCustomers";

    public static final String FUNC_CHECKISWHITELISTED = "checkIsWhiteListed";

    public static final String FUNC_ADDBLACKLISTEDCUSTOMER = "addBlackListedCustomer";

    public static final String FUNC_GETALLWHITELISTEDCUSTOMERADDRESS = "getAllWhiteListedCustomerAddress";

    public static final String FUNC_GETWHITELISTEDCUSTOMERS = "getWhiteListedCustomers";

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
