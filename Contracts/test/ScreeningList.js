const ScreeningList = artifacts.require('ScreeningList');

contract('ScreeningList', () => {
    let screeningList;

    before(async () => {
        screeningList = await ScreeningList.new();
    });

    it('Should be able to add a black listed customer', async() => {
        await screeningList.addBlackListedCustomer(
            '0x83caa84d4d5a34d217c4aba7d7ae938c95a86451',
            'TY678721',
            'Arunima'
        );
    });

    it('Should be able to add another black listed customer', async() => {
        await screeningList.addBlackListedCustomer(
            '0xb26ba10df8fa266f6bfef127b0c11b1ff458fe25',
            'JP4567875',
            'Arjun'
        );
    });

    it('Should be able to check whether a customer is black listed or not', async() => {
        let response = await screeningList.checkIsBlackListed('0x83caa84d4d5a34d217c4aba7d7ae938c95a86451');
        assert.equal(response, true, "Error in checking a black listed customer");
    });

    it('Should be able to retrieve all black listed customers', async() => {
        let response = await screeningList.getBlackListedCustomers();
        let addressesList = response[0];
        let identificationsList = response[1];
        let namesList = response[2];

        assert.equal(addressesList[0], "0x83caa84d4d5a34d217c4aba7d7ae938c95a86451");
        assert.equal(addressesList[1], "0xb26ba10df8fa266f6bfef127b0c11b1ff458fe25");

        assert.equal(web3.toAscii(identificationsList[0]).replace(/\0/g, ''), "TY678721");
        assert.equal(web3.toAscii(identificationsList[1]).replace(/\0/g, ''), "JP4567875");

        assert.equal(web3.toAscii(namesList[0]).replace(/\0/g, ''), "Arunima");
        assert.equal(web3.toAscii(namesList[1]).replace(/\0/g, ''), "Arjun");
    });

    it('Should be able to remove a black listed customer', async() => {
        await screeningList.removeBlackListedCustomer("0x83caa84d4d5a34d217c4aba7d7ae938c95a86451");
    });

    it('Should be able to check the removed customer does not exist in black list anymore', async() => {
        let response = await screeningList.checkIsBlackListed('0x83caa84d4d5a34d217c4aba7d7ae938c95a86451');
        assert.equal(response, false, 'Error in checking a removed black list customer');
    });
    
    it('Should be able to add a white listed customer', async() => {
        await screeningList.addWhiteListedCustomer(
            '0x77c52ede53ceb63b0e9924f4c80ef47ee5049ac3',
            'CDE98756',
            'Dmitri'
        );
    });

    it('Should be able to add another white listed customer', async() => {
        await screeningList.addWhiteListedCustomer(
            '0x58c4ae5aea5c92b4f568712caae8e1731a2e7ab2',
            'RTY90076',
            'Sean'
        );
    });

    it('Should be able to check whether a customer is white listed or not', async() => {
        let response = await screeningList.checkIsWhiteListed('0x58c4ae5aea5c92b4f568712caae8e1731a2e7ab2');
        assert.equal(response, true, "Error in checking a white listed customer");
    });

    it('Should be able to retrieve all white listed customers', async() => {
        let response = await screeningList.getWhiteListedCustomer();
        let addressesList = response[0];
        let identificationsList = response[1];
        let namesList = response[2];

        assert.equal(addressesList[0], "0x77c52ede53ceb63b0e9924f4c80ef47ee5049ac3");
        assert.equal(addressesList[1], "0x58c4ae5aea5c92b4f568712caae8e1731a2e7ab2");

        assert.equal(web3.toAscii(identificationsList[0]).replace(/\0/g, ''), "CDE98756");
        assert.equal(web3.toAscii(identificationsList[1]).replace(/\0/g, ''), "RTY90076");

        assert.equal(web3.toAscii(namesList[0]).replace(/\0/g, ''), "Dmitri");
        assert.equal(web3.toAscii(namesList[1]).replace(/\0/g, ''), "Sean");
    });

    it('Should be able to remove a customer from white list', async() => {
        await screeningList.removeWhiteListedCustomer('0x77c52ede53ceb63b0e9924f4c80ef47ee5049ac3');
    });

    it('Should be able to check the removed customer does not exist in white list anymore', async() => {
        let response = await screeningList.checkIsWhiteListed('0x77c52ede53ceb63b0e9924f4c80ef47ee5049ac3');
        assert.equal(response, false, 'Error in checking a removed black list customer');
    });
});