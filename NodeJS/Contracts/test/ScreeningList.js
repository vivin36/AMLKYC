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

    it('Should be able to remove a customer from white list', async() => {
        await screeningList.removeWhiteListedCustomer('0x77c52ede53ceb63b0e9924f4c80ef47ee5049ac3');
    });

    it('Should be able to check the removed customer does not exist in white list anymore', async() => {
        let response = await screeningList.checkIsWhiteListed('0x77c52ede53ceb63b0e9924f4c80ef47ee5049ac3');
        assert.equal(response, false, 'Error in checking a removed black list customer');
    });

    it('Should be able to add multiple black listed customers in blockchain', async() => {
        let addressesList = new Array();
        addressesList.push('0x0ea8606d8bf6fe03e3ed95123fda97adeef204ab');
        addressesList.push('0x7adbd3106a594bbb9381cd2011576683dc441621');

        let identificationNamesList = new Array();
        identificationNamesList.push('NMJ789569');
        identificationNamesList.push('YUIK89056');

        let custNamesList = new Array();
        custNamesList.push('Chuck');
        custNamesList.push('Harvey');

        await screeningList.addBlackListedCustomersBatch(addressesList, identificationNamesList, custNamesList);
    });

    it('Should be able to add multiple white listed customers in blockchain', async() => {
        let addressesList = new Array();
        addressesList.push('0x41ff96807f552cc11895b1612e61638d0acc3bc2');
        addressesList.push('0xad09011c258361ce01671b1b74f916417f2c2c3c');

        let identificationNamesList = new Array();
        identificationNamesList.push('HJK89756');
        identificationNamesList.push('SER19345');

        let custNamesList = new Array();
        custNamesList.push('Jonas');
        custNamesList.push('Bill');

        await screeningList.addWhiteListedCustomersBatch(addressesList, identificationNamesList, custNamesList);
    });

    it('Should be able to retrieve all black listed customers', async() => {
        let response = await screeningList.getBlackListedCustomers();
        let addressesList = response[0].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        let identificationsList = response[1].filter(elem => elem != '0x000000000000000000000000');
        let namesList = response[2].filter(elem => elem != '0x0000000000000000000000000000000000000000000000000000000000000000');

        assert.equal(addressesList[0], "0xb26ba10df8fa266f6bfef127b0c11b1ff458fe25");
        assert.equal(addressesList[1], "0x0ea8606d8bf6fe03e3ed95123fda97adeef204ab");
        assert.equal(addressesList[2], "0x7adbd3106a594bbb9381cd2011576683dc441621");

        assert.equal(web3.toAscii(identificationsList[0]).replace(/\0/g, ''), "JP4567875");
        assert.equal(web3.toAscii(identificationsList[1]).replace(/\0/g, ''), "NMJ789569");
        assert.equal(web3.toAscii(identificationsList[2]).replace(/\0/g, ''), "YUIK89056");

        assert.equal(web3.toAscii(namesList[0]).replace(/\0/g, ''), "Arjun");
        assert.equal(web3.toAscii(namesList[1]).replace(/\0/g, ''), "Chuck");
        assert.equal(web3.toAscii(namesList[2]).replace(/\0/g, ''), "Harvey");
    });

    it('Should be able to retrieve all white listed customers', async() => {
        let response = await screeningList.getWhiteListedCustomers();
        let addressesList = response[0].filter(elem => elem != '0x0000000000000000000000000000000000000000');
        let identificationsList = response[1].filter(elem => elem != '0x000000000000000000000000');
        let namesList = response[2].filter(elem => elem != '0x0000000000000000000000000000000000000000000000000000000000000000');

        assert.equal(addressesList[0], "0x58c4ae5aea5c92b4f568712caae8e1731a2e7ab2");
        assert.equal(addressesList[1], "0x41ff96807f552cc11895b1612e61638d0acc3bc2");
        assert.equal(addressesList[2], "0xad09011c258361ce01671b1b74f916417f2c2c3c");

        assert.equal(web3.toAscii(identificationsList[0]).replace(/\0/g, ''), "RTY90076");
        assert.equal(web3.toAscii(identificationsList[1]).replace(/\0/g, ''), "HJK89756");
        assert.equal(web3.toAscii(identificationsList[2]).replace(/\0/g, ''), "SER19345");

        assert.equal(web3.toAscii(namesList[0]).replace(/\0/g, ''), "Sean");
        assert.equal(web3.toAscii(namesList[1]).replace(/\0/g, ''), "Jonas");
        assert.equal(web3.toAscii(namesList[2]).replace(/\0/g, ''), "Bill");
    });

});