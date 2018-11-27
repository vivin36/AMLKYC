package com.blockchain.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Customer extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a03191633179055610d31806100326000396000f30060806040526004361061008d5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630378784e81146100925780630c60f83d146100e057806383d985e81461013c5780638ab66aa6146101705780639516a104146101a25780639f17450914610207578063e598fe4a14610305578063f2db8b3b14610355575b600080fd5b34801561009e57600080fd5b506100a761036a565b604051808261064080838360005b838110156100cd5781810151838201526020016100b5565b5050505090500191505060405180910390f35b3480156100ec57600080fd5b50610101600160a060020a03600435166103d7565b604080518581526020810185905290810183600281111561011e57fe5b60ff1681529115156020830152506040805191829003019350915050f35b34801561014857600080fd5b5061016e600160a060020a036004351660243560443560ff606435166084351515610470565b005b34801561017c57600080fd5b5061016e600160a060020a036004351660243560443560ff6064351660843515156105c7565b3480156101ae57600080fd5b506101b76106d2565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156101f35781810151838201526020016101db565b505050509050019250505060405180910390f35b34801561021357600080fd5b5061021c610734565b604051808661064080838360005b8381101561024257818101518382015260200161022a565b5050505090500185603260200280838360005b8381101561026d578181015183820152602001610255565b5050505090500184603260200280838360005b83811015610298578181015183820152602001610280565b5050505090500183603260200280838360005b838110156102c35781810151838201526020016102ab565b5050505090500182603260200280838360005b838110156102ee5781810151838201526020016102d6565b505050509050019550505050505060405180910390f35b34801561031157600080fd5b5061016e6024600480358281019290820135918135808301929082013591604435808301929082013591606435808301929082013591608435918201910135610971565b34801561036157600080fd5b506100a7610c5f565b610372610cc5565b60005b6001548110156103d3576002600060018381548110151561039257fe5b6000918252602080832090910154600160a060020a031683528201929092526040019020600101548282603281106103c657fe5b6020020152600101610375565b5090565b60008054819081908190600160a060020a031633811461042f576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ce6833981519152604482015290519081900360640190fd5b50505050600160a060020a03919091166000908152600260208190526040909120805460018201549190920154919390925060ff8083169261010090041690565b600054600160a060020a03163381146104c1576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ce6833981519152604482015290519081900360640190fd5b600160a060020a0386166000908152600260208190526040909120868155600180820187905590820180548693919260ff1990911691849081111561050257fe5b0217905550600160a060020a0386166000818152600260208181526040808420909201805461ff0019166101008815150217905560018054808201825593527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf6909201805473ffffffffffffffffffffffffffffffffffffffff1916841790558051928352908201879052818101869052517f29a5379d626a6652057b616ea15148bec8f349c8afe646adc7f34d8821e0e5e5916060908290030190a1505050505050565b600054600160a060020a0316338114610618576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ce6833981519152604482015290519081900360640190fd5b600160a060020a0386166000908152600260208190526040909120868155600180820187905590820180548693919260ff1990911691849081111561065957fe5b0217905550600160a060020a038616600081815260026020818152604092839020909101805461ff0019166101008715150217905581519283528201879052818101869052517f692fa864faaba7f46bd3c4a14b25c03f2a0a12954c4c062d0e58fba6f87b7d06916060908290030190a1505050505050565b6060600180548060200260200160405190810160405280929190818152602001828054801561072a57602002820191906000526020600020905b8154600160a060020a0316815260019091019060200180831161070c575b5050505050905090565b61073c610cc5565b610744610cc5565b61074c610cc5565b610754610cc5565b61075c610cc5565b60008054600160a060020a03163381146107ae576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ce6833981519152604482015290519081900360640190fd5b600091505b6001548210156109685760018054839081106107cb57fe5b600091825260209091200154600160a060020a03168783603281106107ec57fe5b600160a060020a039092166020929092020152600180546002916000918590811061081357fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190205486836032811061084457fe5b6020020152600180546002916000918590811061085d57fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206001015485836032811061089157fe5b602002015260018054600291600091859081106108aa57fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff168483603281106108e157fe5b602002019060028111156108f157fe5b908160028111156108fe57fe5b815250506002600060018481548110151561091557fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff6101009091041683836032811061095257fe5b91151560209290920201526001909101906107b3565b50509091929394565b60008054600160a060020a03163381146109c3576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ce6833981519152604482015290519081900360640190fd5b600091505b8a821015610c51578989838181106109dc57fe5b60200291909101359050600260008e8e868181106109f657fe5b60209081029290920135600160a060020a031683525081019190915260400160002055878783818110610a2557fe5b60200291909101359050600260008e8e86818110610a3f57fe5b60209081029290920135600160a060020a031683525081019190915260400160002060010155858583818110610a7157fe5b90506020020135600281118015610a8757600080fd5b50600260008e8e86818110610a9857fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060020160006101000a81548160ff02191690836002811115610ae657fe5b0217905550838383818110610af757fe5b905060200201351515600260008e8e868181101515610b1257fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060020160016101000a81548160ff02191690831515021790555060018c8c848181101515610b6c57fe5b8354600181018555600094855260209485902001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0395909202939093013593909316929092179055507f29a5379d626a6652057b616ea15148bec8f349c8afe646adc7f34d8821e0e5e58c8c84818110610be257fe5b90506020020135600160a060020a03168b8b858181101515610c0057fe5b602002919091013590508a8a86818110610c1657fe5b60408051600160a060020a039096168652602086810195909552930291909101358383015250519081900360600190a16001909101906109c8565b505050505050505050505050565b610c67610cc5565b60005b6001548110156103d35760026000600183815481101515610c8757fe5b6000918252602080832090910154600160a060020a03168352820192909252604001902054828260328110610cb857fe5b6020020152600101610c6a565b6106406040519081016040528060329060208202803883395091929150505600556e617574686f72697a65642041636365737321000000000000000000000000a165627a7a72305820ed6ebd956aa6c95d9abb2eec754ecf19a00842e26f657d3c3cb9a5c04c0bdf720029";

    public static final String FUNC_GETALLCUSTOMERNAMES = "getAllCustomerNames";

    public static final String FUNC_GETCUSTOMERDETAILS = "getCustomerDetails";

    public static final String FUNC_CREATECUSTOMERDETAILS = "createCustomerDetails";

    public static final String FUNC_UPDATECUSTOMERDETAILS = "updateCustomerDetails";

    public static final String FUNC_GETALLADDRESSES = "getAllAddresses";

    public static final String FUNC_GETALLCUSTOMERDETAILS = "getAllCustomerDetails";

    public static final String FUNC_CREATECUSTOMERDETAILSBATCH = "createCustomerDetailsBatch";

    public static final String FUNC_GETALLCUSTOMERACCOUNTS = "getAllCustomerAccounts";

    public static final Event CUSTOMERCREATED_EVENT = new Event("CustomerCreated", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event UPDATEDCUSTOMERDETAILS_EVENT = new Event("UpdatedCustomerDetails", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    protected Customer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Customer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<List> getAllCustomerNames() {
        final Function function = new Function(FUNC_GETALLCUSTOMERNAMES, 
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

    public RemoteCall<Tuple4<byte[], byte[], BigInteger, Boolean>> getCustomerDetails(String _accountAddress) {
        final Function function = new Function(FUNC_GETCUSTOMERDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple4<byte[], byte[], BigInteger, Boolean>>(
                new Callable<Tuple4<byte[], byte[], BigInteger, Boolean>>() {
                    @Override
                    public Tuple4<byte[], byte[], BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<byte[], byte[], BigInteger, Boolean>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> createCustomerDetails(String _accountAddress, byte[] _account, byte[] _name, BigInteger _customerType, Boolean _isParentCustomer) {
        final Function function = new Function(
                FUNC_CREATECUSTOMERDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress), 
                new org.web3j.abi.datatypes.generated.Bytes32(_account), 
                new org.web3j.abi.datatypes.generated.Bytes32(_name), 
                new org.web3j.abi.datatypes.generated.Uint8(_customerType), 
                new org.web3j.abi.datatypes.Bool(_isParentCustomer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateCustomerDetails(String _accountAddress, byte[] _account, byte[] _name, BigInteger _customerType, Boolean _isParentCustomer) {
        final Function function = new Function(
                FUNC_UPDATECUSTOMERDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress), 
                new org.web3j.abi.datatypes.generated.Bytes32(_account), 
                new org.web3j.abi.datatypes.generated.Bytes32(_name), 
                new org.web3j.abi.datatypes.generated.Uint8(_customerType), 
                new org.web3j.abi.datatypes.Bool(_isParentCustomer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getAllAddresses() {
        final Function function = new Function(FUNC_GETALLADDRESSES, 
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

    public RemoteCall<Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>>> getAllCustomerDetails() {
        final Function function = new Function(FUNC_GETALLCUSTOMERDETAILS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Address>>() {}, new TypeReference<StaticArray<Bytes32>>() {}, new TypeReference<StaticArray<Bytes32>>() {}, new TypeReference<StaticArray<Uint8>>() {}, new TypeReference<StaticArray<Bool>>() {}));
        return new RemoteCall<Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>>>(
                new Callable<Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>>>() {
                    @Override
                    public Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                                convertToNative((List<Bytes32>) results.get(2).getValue()), 
                                convertToNative((List<Uint8>) results.get(3).getValue()), 
                                convertToNative((List<Bool>) results.get(4).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> createCustomerDetailsBatch(List<String> _addresses, List<byte[]> _accounts, List<byte[]> _names, List<BigInteger> _custTypes, List<Boolean> _isParent) {
        final Function function = new Function(
                FUNC_CREATECUSTOMERDETAILSBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(_addresses, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_accounts, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_names, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint8>(
                        org.web3j.abi.Utils.typeMap(_custTypes, org.web3j.abi.datatypes.generated.Uint8.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Bool>(
                        org.web3j.abi.Utils.typeMap(_isParent, org.web3j.abi.datatypes.Bool.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getAllCustomerAccounts() {
        final Function function = new Function(FUNC_GETALLCUSTOMERACCOUNTS, 
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

    public static RemoteCall<Customer> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Customer.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Customer> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Customer.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<CustomerCreatedEventResponse> getCustomerCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CUSTOMERCREATED_EVENT, transactionReceipt);
        ArrayList<CustomerCreatedEventResponse> responses = new ArrayList<CustomerCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CustomerCreatedEventResponse typedResponse = new CustomerCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._accountAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._customerType = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._name = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CustomerCreatedEventResponse> customerCreatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CustomerCreatedEventResponse>() {
            @Override
            public CustomerCreatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CUSTOMERCREATED_EVENT, log);
                CustomerCreatedEventResponse typedResponse = new CustomerCreatedEventResponse();
                typedResponse.log = log;
                typedResponse._accountAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._customerType = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._name = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CustomerCreatedEventResponse> customerCreatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CUSTOMERCREATED_EVENT));
        return customerCreatedEventObservable(filter);
    }

    public List<UpdatedCustomerDetailsEventResponse> getUpdatedCustomerDetailsEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UPDATEDCUSTOMERDETAILS_EVENT, transactionReceipt);
        ArrayList<UpdatedCustomerDetailsEventResponse> responses = new ArrayList<UpdatedCustomerDetailsEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UpdatedCustomerDetailsEventResponse typedResponse = new UpdatedCustomerDetailsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._accountAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._account = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._name = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UpdatedCustomerDetailsEventResponse> updatedCustomerDetailsEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, UpdatedCustomerDetailsEventResponse>() {
            @Override
            public UpdatedCustomerDetailsEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UPDATEDCUSTOMERDETAILS_EVENT, log);
                UpdatedCustomerDetailsEventResponse typedResponse = new UpdatedCustomerDetailsEventResponse();
                typedResponse.log = log;
                typedResponse._accountAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._account = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._name = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<UpdatedCustomerDetailsEventResponse> updatedCustomerDetailsEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPDATEDCUSTOMERDETAILS_EVENT));
        return updatedCustomerDetailsEventObservable(filter);
    }

    public static Customer load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Customer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Customer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Customer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class CustomerCreatedEventResponse {
        public Log log;

        public String _accountAddress;

        public byte[] _customerType;

        public byte[] _name;
    }

    public static class UpdatedCustomerDetailsEventResponse {
        public Log log;

        public String _accountAddress;

        public byte[] _account;

        public byte[] _name;
    }
}
