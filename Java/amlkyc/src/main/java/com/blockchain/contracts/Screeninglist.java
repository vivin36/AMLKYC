package com.blockchain.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
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
    private static final String BINARY = "608060405260038054600160a060020a0319163317905534801561002257600080fd5b50610c44806100326000396000f3006080604052600436106100a35763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630fdbaaeb81146100a857806311d23036146100e25780631576d80a14610103578063187719021461013b5780631e6934781461017057806320c6957914610197578063462ca0ed146101b85780635ccce7c41461025e5780635f297e0d1461027f578063d98b70f5146102a6575b600080fd5b3480156100b457600080fd5b506100e060246004803582810192908201359181358083019290820135916044359182019101356102bb565b005b3480156100ee57600080fd5b506100e0600160a060020a0360043516610433565b34801561010f57600080fd5b506100e06024600480358281019290820135918135808301929082013591604435918201910135610513565b34801561014757600080fd5b5061015c600160a060020a0360043516610681565b604080519115158252519081900360200190f35b34801561017c57600080fd5b506100e0600160a060020a03600435166024356044356106da565b3480156101a357600080fd5b506100e0600160a060020a03600435166107ad565b3480156101c457600080fd5b506101cd61085d565b604051808461064080838360005b838110156101f35781810151838201526020016101db565b5050505090500183603260200280838360005b8381101561021e578181015183820152602001610206565b5050505090500182603260200280838360005b83811015610249578181015183820152602001610231565b50505050905001935050505060405180910390f35b34801561026a57600080fd5b5061015c600160a060020a036004351661096a565b34801561028b57600080fd5b506100e0600160a060020a03600435166024356044356109bd565b3480156102b257600080fd5b506101cd610a91565b600354600090600160a060020a031633811461030f576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610bf9833981519152604482015290519081900360640190fd5b600091505b868210156104295785858381811061032857fe5b60200291909101359050600260008a8a8681811061034257fe5b60209081029290920135600160a060020a03168352508101919091526040016000205583838381811061037157fe5b60200291909101359050600260008a8a8681811061038b57fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a031681526020019081526020016000206001018160001916905550600188888481811015156103d657fe5b83546001808201865560009586526020958690209091018054929095029390930135600160a060020a031673ffffffffffffffffffffffffffffffffffffffff1990911617909255929092019150610314565b5050505050505050565b600354600090600160a060020a0316338114610487576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610bf9833981519152604482015290519081900360640190fd5b600091505b60015482101561050e5782600160a060020a03166001838154811015156104af57fe5b600091825260209091200154600160a060020a031614156105035760018054839081106104d857fe5b6000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff1916905561050e565b60019091019061048c565b505050565b600354600090600160a060020a0316338114610567576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610bf9833981519152604482015290519081900360640190fd5b600091505b868210156104295785858381811061058057fe5b60200291909101359050600260008a8a8681811061059a57fe5b60209081029290920135600160a060020a0316835250810191909152604001600020558383838181106105c957fe5b60200291909101359050600260008a8a868181106105e357fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060010181600019169055506000888884818110151561062e57fe5b83546001808201865560009586526020958690209091018054929095029390930135600160a060020a031673ffffffffffffffffffffffffffffffffffffffff199091161790925592909201915061056c565b6000805b6000548110156106d45782600160a060020a03166000828154811015156106a857fe5b600091825260209091200154600160a060020a031614156106cc57600191506106d4565b600101610685565b50919050565b6106e2610bc1565b600354600160a060020a0316338114610733576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610bf9833981519152604482015290519081900360640190fd5b5091825260208083019182526001805480820182557fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf6018054600160a060020a0390961673ffffffffffffffffffffffffffffffffffffffff19909616861790556000948552600290915260409093209151825551910155565b600354600090600160a060020a0316338114610801576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610bf9833981519152604482015290519081900360640190fd5b600091505b60005482101561050e5782600160a060020a031660008381548110151561082957fe5b600091825260209091200154600160a060020a031614156108525760008054839081106104d857fe5b600190910190610806565b610865610bd8565b61086d610bd8565b610875610bd8565b60005b60005481101561096457600080548290811061089057fe5b600091825260209091200154600160a060020a03168482603281106108b157fe5b600160a060020a039092166020929092020152600260008583603281106108d457fe5b6020020151600160a060020a0316600160a060020a0316815260200190815260200160002060000154838260328110151561090b57fe5b60200201526002600085836032811061092057fe5b6020020151600160a060020a0316600160a060020a0316815260200190815260200160002060010154828260328110151561095757fe5b6020020152600101610878565b50909192565b6000805b6001548110156106d45782600160a060020a031660018281548110151561099157fe5b600091825260209091200154600160a060020a031614156109b557600191506106d4565b60010161096e565b6109c5610bc1565b600354600160a060020a0316338114610a16576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610bf9833981519152604482015290519081900360640190fd5b50918252602080830191825260008054600181810183557f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e5639091018054600160a060020a0390971673ffffffffffffffffffffffffffffffffffffffff19909716871790559481526002909152604090209151825551910155565b610a99610bd8565b610aa1610bd8565b610aa9610bd8565b60005b600154811015610964576001805482908110610ac457fe5b600091825260209091200154600160a060020a031615610bb9576001805482908110610aec57fe5b600091825260209091200154600160a060020a0316848260328110610b0d57fe5b600160a060020a03909216602092909202015260026000858360328110610b3057fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600001548382603281101515610b6757fe5b602002015260026000858360328110610b7c57fe5b6020020151600160a060020a0316600160a060020a03168152602001908152602001600020600101548282603281101515610bb357fe5b60200201525b600101610aac565b604080518082019091526000808252602082015290565b6106406040519081016040528060329060208202803883395091929150505600556e617574686f72697a65642041636365737321000000000000000000000000a165627a7a723058207cc6c0ad05439e308912f6869312f442ca7a740068ff4888defdd4a16c729b920029";

    public static final String FUNC_ADDWHITELISTEDCUSTOMERSBATCH = "addWhiteListedCustomersBatch";

    public static final String FUNC_REMOVEWHITELISTEDCUSTOMER = "removeWhiteListedCustomer";

    public static final String FUNC_ADDBLACKLISTEDCUSTOMERSBATCH = "addBlackListedCustomersBatch";

    public static final String FUNC_CHECKISBLACKLISTED = "checkIsBlackListed";

    public static final String FUNC_ADDWHITELISTEDCUSTOMER = "addWhiteListedCustomer";

    public static final String FUNC_REMOVEBLACKLISTEDCUSTOMER = "removeBlackListedCustomer";

    public static final String FUNC_GETBLACKLISTEDCUSTOMERS = "getBlackListedCustomers";

    public static final String FUNC_CHECKISWHITELISTED = "checkIsWhiteListed";

    public static final String FUNC_ADDBLACKLISTEDCUSTOMER = "addBlackListedCustomer";

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
