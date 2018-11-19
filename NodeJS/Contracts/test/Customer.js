const Customer = artifacts.require("Customer");

contract('Customer', () => {
    let KYCContract;

    before(async () => {
        customerContract = await Customer.new();
    }); 

    it("Create details of a customer in Blockchain and verifying it", async () => {
        await customerContract.createCustomerDetails(
            "0xaa33b047c50b1051c8ae24c01d78430ee0d4d035", "ZXC78343", "John", 1, true)
        let response = await customerContract.getCustomerDetails("0xaa33b047c50b1051c8ae24c01d78430ee0d4d035");        
        assert.equal(web3.toAscii(response[0]).replace(/\0/g, ''), "ZXC78343");
        assert.equal(web3.toAscii(response[1]).replace(/\0/g, ''), "John");
        assert.equal(response[2], 1);
        assert.equal(response[3], true);
    });

    it("Update an existing customer detail in Blockchain and verifying it", async() => {
        await customerContract.updateCustomerDetails(
            "0xaa33b047c50b1051c8ae24c01d78430ee0d4d035","ZXC78343","Jora",1,1);
        let response = await customerContract.getCustomerDetails("0xaa33b047c50b1051c8ae24c01d78430ee0d4d035");
        assert.equal(web3.toAscii(response[1]).replace(/\0/g, ''), "Jora", "Failed to update customer details");
    });

    it("Update details of multiple customers in Blockchain", async() => {
        let addressesList = new Array();
        addressesList.push('0x1d82269cea0a74829a7f727016dd608f12c34fac');
        addressesList.push('0x8cb7af8f38616b84a8ecc0b0a327d04e02b11bea');

        let accountNamesList = new Array();
        accountNamesList.push('NMJ789569');
        accountNamesList.push('JP4567875');

        let custNamesList = new Array();
        custNamesList.push('Chuck');
        custNamesList.push('Harvey');

        let custTypesList = new Array();
        custTypesList.push(1);
        custTypesList.push(2);

        let isParentList = new Array();
        isParentList.push(false);
        isParentList.push(true);

        await customerContract.createCustomerDetailsBatch(addressesList, accountNamesList, custNamesList, custTypesList, isParentList);
    });

    it('Retrieve details of all the customers', async() => {
        let response = await customerContract.getAllCustomerDetails();
        let ethAddressesList = response[0];
        let accountNamesList = response[1];
        let custNamesList = response[2];
        let custTypesList = response[3];
        let isParentList = response[4];

        assert.equal(ethAddressesList[0],"0xaa33b047c50b1051c8ae24c01d78430ee0d4d035");
        assert.equal(ethAddressesList[1],"0x1d82269cea0a74829a7f727016dd608f12c34fac");
        assert.equal(ethAddressesList[2],"0x8cb7af8f38616b84a8ecc0b0a327d04e02b11bea");

        assert.equal(web3.toAscii(accountNamesList[0]).replace(/\0/g, ''),"ZXC78343");
        assert.equal(web3.toAscii(accountNamesList[1]).replace(/\0/g, ''),"NMJ789569");
        assert.equal(web3.toAscii(accountNamesList[2]).replace(/\0/g, ''),"JP4567875");

        assert.equal(web3.toAscii(custNamesList[0]).replace(/\0/g, ''),"Jora");
        assert.equal(web3.toAscii(custNamesList[1]).replace(/\0/g, ''),"Chuck");
        assert.equal(web3.toAscii(custNamesList[2]).replace(/\0/g, ''),"Harvey");

        assert.equal(custTypesList[0], 1);
        assert.equal(custTypesList[1], 1);
        assert.equal(custTypesList[2], 2);

        assert.equal(isParentList[0], true);
        assert.equal(isParentList[1], false);
        assert.equal(isParentList[2], true);
    });
})