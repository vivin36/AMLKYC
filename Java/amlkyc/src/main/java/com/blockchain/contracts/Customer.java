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
    private static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a03191633179055610e6f806100326000396000f3006080604052600436106100a35763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630c60f83d81146100a857806383d985e81461010457806385a06d72146101385780638ab66aa6146101865780639516a104146101b85780639f1745091461021d578063b38a888c1461031b578063e598fe4a14610330578063f955f9a414610380578063fb825e5f14610395575b600080fd5b3480156100b457600080fd5b506100c9600160a060020a03600435166103aa565b60408051858152602081018590529081018360028111156100e657fe5b60ff1681529115156020830152506040805191829003019350915050f35b34801561011057600080fd5b50610136600160a060020a036004351660243560443560ff606435166084351515610443565b005b34801561014457600080fd5b5061014d61059a565b604051808261064080838360005b8381101561017357818101518382015260200161015b565b5050505090500191505060405180910390f35b34801561019257600080fd5b50610136600160a060020a036004351660243560443560ff606435166084351515610615565b3480156101c457600080fd5b506101cd610720565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156102095781810151838201526020016101f1565b505050509050019250505060405180910390f35b34801561022957600080fd5b50610232610782565b604051808661064080838360005b83811015610258578181015183820152602001610240565b5050505090500185603260200280838360005b8381101561028357818101518382015260200161026b565b5050505090500184603260200280838360005b838110156102ae578181015183820152602001610296565b5050505090500183603260200280838360005b838110156102d95781810151838201526020016102c1565b5050505090500182603260200280838360005b838110156103045781810151838201526020016102ec565b505050509050019550505050505060405180910390f35b34801561032757600080fd5b5061014d6109bf565b34801561033c57600080fd5b506101366024600480358281019290820135918135808301929082013591604435808301929082013591606435808301929082013591608435918201910135610a25565b34801561038c57600080fd5b5061014d610d13565b3480156103a157600080fd5b5061014d610d9a565b60008054819081908190600160a060020a0316338114610402576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610e24833981519152604482015290519081900360640190fd5b50505050600160a060020a03919091166000908152600260208190526040909120805460018201549190920154919390925060ff8083169261010090041690565b600054600160a060020a0316338114610494576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610e24833981519152604482015290519081900360640190fd5b600160a060020a0386166000908152600260208190526040909120868155600180820187905590820180548693919260ff199091169184908111156104d557fe5b0217905550600160a060020a0386166000818152600260208181526040808420909201805461ff0019166101008815150217905560018054808201825593527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf6909201805473ffffffffffffffffffffffffffffffffffffffff1916841790558051928352908201879052818101869052517f29a5379d626a6652057b616ea15148bec8f349c8afe646adc7f34d8821e0e5e5916060908290030190a1505050505050565b6105a2610e03565b60005b60015481101561061157600260006001838154811015156105c257fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff610100909104168282603281106105ff57fe5b911515602090920201526001016105a5565b5090565b600054600160a060020a0316338114610666576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610e24833981519152604482015290519081900360640190fd5b600160a060020a0386166000908152600260208190526040909120868155600180820187905590820180548693919260ff199091169184908111156106a757fe5b0217905550600160a060020a038616600081815260026020818152604092839020909101805461ff0019166101008715150217905581519283528201879052818101869052517f692fa864faaba7f46bd3c4a14b25c03f2a0a12954c4c062d0e58fba6f87b7d06916060908290030190a1505050505050565b6060600180548060200260200160405190810160405280929190818152602001828054801561077857602002820191906000526020600020905b8154600160a060020a0316815260019091019060200180831161075a575b5050505050905090565b61078a610e03565b610792610e03565b61079a610e03565b6107a2610e03565b6107aa610e03565b60008054600160a060020a03163381146107fc576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610e24833981519152604482015290519081900360640190fd5b600091505b6001548210156109b657600180548390811061081957fe5b600091825260209091200154600160a060020a031687836032811061083a57fe5b600160a060020a039092166020929092020152600180546002916000918590811061086157fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190205486836032811061089257fe5b602002015260018054600291600091859081106108ab57fe5b6000918252602080832090910154600160a060020a031683528201929092526040019020600101548583603281106108df57fe5b602002015260018054600291600091859081106108f857fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff1684836032811061092f57fe5b6020020190600281111561093f57fe5b9081600281111561094c57fe5b815250506002600060018481548110151561096357fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff610100909104168383603281106109a057fe5b9115156020929092020152600190910190610801565b50509091929394565b6109c7610e03565b60005b60015481101561061157600260006001838154811015156109e757fe5b6000918252602080832090910154600160a060020a03168352820192909252604001902054828260328110610a1857fe5b60200201526001016109ca565b60008054600160a060020a0316338114610a77576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610e24833981519152604482015290519081900360640190fd5b600091505b8a821015610d0557898983818110610a9057fe5b60200291909101359050600260008e8e86818110610aaa57fe5b60209081029290920135600160a060020a031683525081019190915260400160002055878783818110610ad957fe5b60200291909101359050600260008e8e86818110610af357fe5b60209081029290920135600160a060020a031683525081019190915260400160002060010155858583818110610b2557fe5b90506020020135600281118015610b3b57600080fd5b50600260008e8e86818110610b4c57fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060020160006101000a81548160ff02191690836002811115610b9a57fe5b0217905550838383818110610bab57fe5b905060200201351515600260008e8e868181101515610bc657fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060020160016101000a81548160ff02191690831515021790555060018c8c848181101515610c2057fe5b8354600181018555600094855260209485902001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0395909202939093013593909316929092179055507f29a5379d626a6652057b616ea15148bec8f349c8afe646adc7f34d8821e0e5e58c8c84818110610c9657fe5b90506020020135600160a060020a03168b8b858181101515610cb457fe5b602002919091013590508a8a86818110610cca57fe5b60408051600160a060020a039096168652602086810195909552930291909101358383015250519081900360600190a1600190910190610a7c565b505050505050505050505050565b610d1b610e03565b60005b6001548110156106115760026000600183815481101515610d3b57fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff16828260328110610d7257fe5b60200201906002811115610d8257fe5b90816002811115610d8f57fe5b905250600101610d1e565b610da2610e03565b60005b6001548110156106115760026000600183815481101515610dc257fe5b6000918252602080832090910154600160a060020a03168352820192909252604001902060010154828260328110610df657fe5b6020020152600101610da5565b6106406040519081016040528060329060208202803883395091929150505600556e617574686f72697a65642041636365737321000000000000000000000000a165627a7a72305820db2de210ac0fdd3e3b83e28e288658fbc21de6947def7e85811d7ba5388e1f790029";

    public static final String FUNC_GETCUSTOMERDETAILS = "getCustomerDetails";

    public static final String FUNC_CREATECUSTOMERDETAILS = "createCustomerDetails";

    public static final String FUNC_GETALLISPARENTSTATUS = "getAllIsParentStatus";

    public static final String FUNC_UPDATECUSTOMERDETAILS = "updateCustomerDetails";

    public static final String FUNC_GETALLADDRESSES = "getAllAddresses";

    public static final String FUNC_GETALLCUSTOMERDETAILS = "getAllCustomerDetails";

    public static final String FUNC_GETALLACCOUNTNAMES = "getAllAccountNames";

    public static final String FUNC_CREATECUSTOMERDETAILSBATCH = "createCustomerDetailsBatch";

    public static final String FUNC_GETALLCUSTOMERTYPES = "getAllCustomerTypes";

    public static final String FUNC_GETALLNAMES = "getAllNames";

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

    public RemoteCall<List> getAllIsParentStatus() {
        final Function function = new Function(FUNC_GETALLISPARENTSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Bool>>() {}));
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

    public RemoteCall<List> getAllAccountNames() {
        final Function function = new Function(FUNC_GETALLACCOUNTNAMES, 
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

    public RemoteCall<List> getAllCustomerTypes() {
        final Function function = new Function(FUNC_GETALLCUSTOMERTYPES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Uint8>>() {}));
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

    public RemoteCall<List> getAllNames() {
        final Function function = new Function(FUNC_GETALLNAMES, 
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
