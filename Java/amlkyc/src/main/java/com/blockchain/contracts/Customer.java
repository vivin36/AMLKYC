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
    private static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a03191633179055610b13806100326000396000f30060806040526004361061006c5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630c60f83d811461007157806383d985e8146100cd5780638ab66aa6146101015780639f17450914610133578063e598fe4a14610231575b600080fd5b34801561007d57600080fd5b50610092600160a060020a0360043516610281565b60408051858152602081018590529081018360028111156100af57fe5b60ff1681529115156020830152506040805191829003019350915050f35b3480156100d957600080fd5b506100ff600160a060020a036004351660243560443560ff60643516608435151561031a565b005b34801561010d57600080fd5b506100ff600160a060020a036004351660243560443560ff606435166084351515610471565b34801561013f57600080fd5b5061014861057c565b604051808661064080838360005b8381101561016e578181015183820152602001610156565b5050505090500185603260200280838360005b83811015610199578181015183820152602001610181565b5050505090500184603260200280838360005b838110156101c45781810151838201526020016101ac565b5050505090500183603260200280838360005b838110156101ef5781810151838201526020016101d7565b5050505090500182603260200280838360005b8381101561021a578181015183820152602001610202565b505050509050019550505050505060405180910390f35b34801561023d57600080fd5b506100ff60246004803582810192908201359181358083019290820135916044358083019290820135916064358083019290820135916084359182019101356107b9565b60008054819081908190600160a060020a03163381146102d9576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ac8833981519152604482015290519081900360640190fd5b50505050600160a060020a03919091166000908152600260208190526040909120805460018201549190920154919390925060ff8083169261010090041690565b600054600160a060020a031633811461036b576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ac8833981519152604482015290519081900360640190fd5b600160a060020a0386166000908152600260208190526040909120868155600180820187905590820180548693919260ff199091169184908111156103ac57fe5b0217905550600160a060020a0386166000818152600260208181526040808420909201805461ff0019166101008815150217905560018054808201825593527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf6909201805473ffffffffffffffffffffffffffffffffffffffff1916841790558051928352908201879052818101869052517f29a5379d626a6652057b616ea15148bec8f349c8afe646adc7f34d8821e0e5e5916060908290030190a1505050505050565b600054600160a060020a03163381146104c2576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ac8833981519152604482015290519081900360640190fd5b600160a060020a0386166000908152600260208190526040909120868155600180820187905590820180548693919260ff1990911691849081111561050357fe5b0217905550600160a060020a038616600081815260026020818152604092839020909101805461ff0019166101008715150217905581519283528201879052818101869052517f692fa864faaba7f46bd3c4a14b25c03f2a0a12954c4c062d0e58fba6f87b7d06916060908290030190a1505050505050565b610584610aa7565b61058c610aa7565b610594610aa7565b61059c610aa7565b6105a4610aa7565b60008054600160a060020a03163381146105f6576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ac8833981519152604482015290519081900360640190fd5b600091505b6001548210156107b057600180548390811061061357fe5b600091825260209091200154600160a060020a031687836032811061063457fe5b600160a060020a039092166020929092020152600180546002916000918590811061065b57fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190205486836032811061068c57fe5b602002015260018054600291600091859081106106a557fe5b6000918252602080832090910154600160a060020a031683528201929092526040019020600101548583603281106106d957fe5b602002015260018054600291600091859081106106f257fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff1684836032811061072957fe5b6020020190600281111561073957fe5b9081600281111561074657fe5b815250506002600060018481548110151561075d57fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff6101009091041683836032811061079a57fe5b91151560209290920201526001909101906105fb565b50509091929394565b60008054600160a060020a031633811461080b576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610ac8833981519152604482015290519081900360640190fd5b600091505b8a821015610a995789898381811061082457fe5b60200291909101359050600260008e8e8681811061083e57fe5b60209081029290920135600160a060020a03168352508101919091526040016000205587878381811061086d57fe5b60200291909101359050600260008e8e8681811061088757fe5b60209081029290920135600160a060020a0316835250810191909152604001600020600101558585838181106108b957fe5b905060200201356002811180156108cf57600080fd5b50600260008e8e868181106108e057fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060020160006101000a81548160ff0219169083600281111561092e57fe5b021790555083838381811061093f57fe5b905060200201351515600260008e8e86818110151561095a57fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060020160016101000a81548160ff02191690831515021790555060018c8c8481811015156109b457fe5b8354600181018555600094855260209485902001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0395909202939093013593909316929092179055507f29a5379d626a6652057b616ea15148bec8f349c8afe646adc7f34d8821e0e5e58c8c84818110610a2a57fe5b90506020020135600160a060020a03168b8b858181101515610a4857fe5b602002919091013590508a8a86818110610a5e57fe5b60408051600160a060020a039096168652602086810195909552930291909101358383015250519081900360600190a1600190910190610810565b505050505050505050505050565b6106406040519081016040528060329060208202803883395091929150505600556e617574686f72697a65642041636365737321000000000000000000000000a165627a7a72305820a844f2e75502c924cc9e04ebe3d65898ced7ea07861aad47f2eb1e76107bc7780029";

    public static final String FUNC_GETCUSTOMERDETAILS = "getCustomerDetails";

    public static final String FUNC_CREATECUSTOMERDETAILS = "createCustomerDetails";

    public static final String FUNC_UPDATECUSTOMERDETAILS = "updateCustomerDetails";

    public static final String FUNC_GETALLCUSTOMERDETAILS = "getAllCustomerDetails";

    public static final String FUNC_CREATECUSTOMERDETAILSBATCH = "createCustomerDetailsBatch";

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
