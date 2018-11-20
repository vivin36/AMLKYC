package com.blockchain.contracts;

import io.reactivex.Flowable;
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
import org.web3j.abi.datatypes.generated.Bytes12;
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
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class Customer extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a033316600160a060020a0319909116179055610bc28061003d6000396000f30060806040526004361061006c5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166305cbbece81146100715780630c60f83d146100af5780639f17450914610115578063fd6c6b7014610213578063ff53f23114610263575b600080fd5b34801561007d57600080fd5b506100ad600160a060020a0360043516600160a060020a03196024351660443560ff60643516608435151561029f565b005b3480156100bb57600080fd5b506100d0600160a060020a036004351661040b565b60408051600160a060020a031986168152602081018590529081018360028111156100f757fe5b60ff1681529115156020830152506040805191829003019350915050f35b34801561012157600080fd5b5061012a6104af565b604051808661064080838360005b83811015610150578181015183820152602001610138565b5050505090500185603260200280838360005b8381101561017b578181015183820152602001610163565b5050505090500184603260200280838360005b838110156101a657818101518382015260200161018e565b5050505090500183603260200280838360005b838110156101d15781810151838201526020016101b9565b5050505090500182603260200280838360005b838110156101fc5781810151838201526020016101e4565b505050509050019550505050505060405180910390f35b34801561021f57600080fd5b506100ad6024600480358281019290820135918135808301929082013591604435808301929082013591606435808301929082013591608435918201910135610705565b34801561026f57600080fd5b506100ad600160a060020a0360043516600160a060020a03196024351660443560ff606435166084351515610a25565b600054600160a060020a0390811690331681146102f4576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610b77833981519152604482015290519081900360640190fd5b600160a060020a038616600090815260026020819052604090912080546bffffffffffffffffffffffff191660a060020a8804178155600180820187905590820180548693919260ff1990911691849081111561034d57fe5b0217905550600160a060020a0386166000818152600260208181526040808420909201805461ff0019166101008815150217905560018054808201825593527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf69092018054600160a060020a031990811685179091558151938452881691830191909152818101869052517f30c09adc66ac3de121ffba961f95684e73cf5dd284b9bef607f6f214c988d034916060908290030190a1505050505050565b60008054819081908190600160a060020a039081169033168114610467576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610b77833981519152604482015290519081900360640190fd5b50505050600160a060020a0391909116600090815260026020819052604090912080546001820154919092015460a060020a9092029390925060ff8083169261010090041690565b6104b7610b56565b6104bf610b56565b6104c7610b56565b6104cf610b56565b6104d7610b56565b60008054600160a060020a03908116903316811461052d576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610b77833981519152604482015290519081900360640190fd5b600091505b6001548210156106fc57600180548390811061054a57fe5b600091825260209091200154600160a060020a031687836032811061056b57fe5b600160a060020a039092166020929092020152600180546002916000918590811061059257fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190205460a060020a028683603281106105c957fe5b600160a060020a0319909216602092909202015260018054600291600091859081106105f157fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206001015485836032811061062557fe5b6020020152600180546002916000918590811061063e57fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff1684836032811061067557fe5b6020020190600281111561068557fe5b9081600281111561069257fe5b81525050600260006001848154811015156106a957fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190206002015460ff610100909104168383603281106106e657fe5b9115156020929092020152600190910190610532565b50509091929394565b60008054600160a060020a03908116903316811461075b576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610b77833981519152604482015290519081900360640190fd5b600091505b8a821015610a175789898381811061077457fe5b90506020020135600160a060020a031916600260008e8e86818110151561079757fe5b60209081029290920135600160a060020a031683525081019190915260400160002080546bffffffffffffffffffffffff191660a060020a9092049190911790558787838181106107e457fe5b60200291909101359050600260008e8e868181106107fe57fe5b60209081029290920135600160a060020a03168352508101919091526040016000206001015585858381811061083057fe5b9050602002013560028111801561084657600080fd5b50600260008e8e8681811061085757fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060020160006101000a81548160ff021916908360028111156108a557fe5b02179055508383838181106108b657fe5b905060200201351515600260008e8e8681811015156108d157fe5b90506020020135600160a060020a0316600160a060020a0316600160a060020a0316815260200190815260200160002060020160016101000a81548160ff02191690831515021790555060018c8c84818110151561092b57fe5b83546001810185556000948552602094859020018054600160a060020a031916600160a060020a0395909202939093013593909316929092179055507f30c09adc66ac3de121ffba961f95684e73cf5dd284b9bef607f6f214c988d0348c8c8481811061099457fe5b90506020020135600160a060020a03168b8b8581811015156109b257fe5b90506020020135600160a060020a0319168a8a8681811015156109d157fe5b60408051600160a060020a039096168652600160a060020a03199094166020868101919091520291909101358383015250519081900360600190a1600190910190610760565b505050505050505050505050565b600054600160a060020a039081169033168114610a7a576040805160e560020a62461bcd0281526020600482015260146024820152600080516020610b77833981519152604482015290519081900360640190fd5b600160a060020a038616600090815260026020819052604090912080546bffffffffffffffffffffffff191660a060020a8804178155600180820187905590820180548693919260ff19909116918490811115610ad357fe5b0217905550600160a060020a038616600081815260026020818152604092839020909101805461ff001916610100871515021790558151928352600160a060020a0319881690830152818101869052517fdc29fffddf8ca7e1cd85ff98be23ab93999f87c42e63eaad86d31eeedfeee5bd916060908290030190a1505050505050565b6106406040519081016040528060329060208202803883395091929150505600556e617574686f72697a65642041636365737321000000000000000000000000a165627a7a723058200ef5b692a0e7e9063af9037243d389813cfaf75d79a92ed2369452bc236e56960029";

    public static final String FUNC_CREATECUSTOMERDETAILS = "createCustomerDetails";

    public static final String FUNC_GETCUSTOMERDETAILS = "getCustomerDetails";

    public static final String FUNC_GETALLCUSTOMERDETAILS = "getAllCustomerDetails";

    public static final String FUNC_CREATECUSTOMERDETAILSBATCH = "createCustomerDetailsBatch";

    public static final String FUNC_UPDATECUSTOMERDETAILS = "updateCustomerDetails";

    public static final Event CUSTOMERCREATED_EVENT = new Event("CustomerCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes12>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event UPDATEDCUSTOMERDETAILS_EVENT = new Event("UpdatedCustomerDetails", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes12>() {}, new TypeReference<Bytes32>() {}));
    ;

    @Deprecated
    protected Customer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Customer(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Customer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Customer(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> createCustomerDetails(String _accountAddress, byte[] _account, byte[] _name, BigInteger _customerType, Boolean _isParentCustomer) {
        final Function function = new Function(
                FUNC_CREATECUSTOMERDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress), 
                new org.web3j.abi.datatypes.generated.Bytes12(_account), 
                new org.web3j.abi.datatypes.generated.Bytes32(_name), 
                new org.web3j.abi.datatypes.generated.Uint8(_customerType), 
                new org.web3j.abi.datatypes.Bool(_isParentCustomer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<byte[], byte[], BigInteger, Boolean>> getCustomerDetails(String _accountAddress) {
        final Function function = new Function(FUNC_GETCUSTOMERDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes12>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bool>() {}));
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

    public RemoteCall<Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>>> getAllCustomerDetails() {
        final Function function = new Function(FUNC_GETALLCUSTOMERDETAILS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Address>>() {}, new TypeReference<StaticArray<Bytes12>>() {}, new TypeReference<StaticArray<Bytes32>>() {}, new TypeReference<StaticArray<Uint8>>() {}, new TypeReference<StaticArray<Bool>>() {}));
        return new RemoteCall<Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>>>(
                new Callable<Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>>>() {
                    @Override
                    public Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<List<String>, List<byte[]>, List<byte[]>, List<BigInteger>, List<Boolean>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Bytes12>) results.get(1).getValue()), 
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
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes12>(
                        org.web3j.abi.Utils.typeMap(_accounts, org.web3j.abi.datatypes.generated.Bytes12.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_names, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint8>(
                        org.web3j.abi.Utils.typeMap(_custTypes, org.web3j.abi.datatypes.generated.Uint8.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Bool>(
                        org.web3j.abi.Utils.typeMap(_isParent, org.web3j.abi.datatypes.Bool.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateCustomerDetails(String _accountAddress, byte[] _account, byte[] _name, BigInteger _customerType, Boolean _isParentCustomer) {
        final Function function = new Function(
                FUNC_UPDATECUSTOMERDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_accountAddress), 
                new org.web3j.abi.datatypes.generated.Bytes12(_account), 
                new org.web3j.abi.datatypes.generated.Bytes32(_name), 
                new org.web3j.abi.datatypes.generated.Uint8(_customerType), 
                new org.web3j.abi.datatypes.Bool(_isParentCustomer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public Flowable<CustomerCreatedEventResponse> customerCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, CustomerCreatedEventResponse>() {
            @Override
            public CustomerCreatedEventResponse apply(Log log) {
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

    public Flowable<CustomerCreatedEventResponse> customerCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CUSTOMERCREATED_EVENT));
        return customerCreatedEventFlowable(filter);
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

    public Flowable<UpdatedCustomerDetailsEventResponse> updatedCustomerDetailsEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, UpdatedCustomerDetailsEventResponse>() {
            @Override
            public UpdatedCustomerDetailsEventResponse apply(Log log) {
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

    public Flowable<UpdatedCustomerDetailsEventResponse> updatedCustomerDetailsEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPDATEDCUSTOMERDETAILS_EVENT));
        return updatedCustomerDetailsEventFlowable(filter);
    }

    @Deprecated
    public static Customer load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Customer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Customer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Customer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Customer load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Customer(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Customer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Customer(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Customer> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Customer.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Customer> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Customer.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Customer> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Customer.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Customer> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Customer.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
