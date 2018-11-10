const ScreeningList = artifacts.require('ScreeningList');

contract('ScreeningList', () => {
    let screeningList;

    before(async () => {
        screeningList = await ScreeningList.new();
    });

    it('Should be able to add a black listed customer', async() => {
        await screeningList.addBlackListedCustomer(
            'BX23456765',
            'Arjun'
        );
    });

    it('Should be able to retrieve a black listed customer', async() => {
        let response = await screeningList.getBlackListedCustomer('BX23456765');
        assert.equal(web3.toAscii(response).replace(/\0/g, ''), "Arjun", "Error in retrieving a black listed customer");
    });

    it('Should be able to check whether a customer is black listed or not', async() => {
        let response = await screeningList.checkIsBlackListed('BX23456765');
        assert.equal(response, true, "Error in checking a black listed customer");
    });

    it('Should be able to add another black listed customer', async() => {
        await screeningList.addBlackListedCustomer(
            'TY56743498',
            'Paul'
        );
    });

    it('Should be able to remove a customer from black list', async() => {
        await screeningList.removeBlackListedCustomer('BX23456765');
    });

    it('Should be able to check the removed customer does not exist in black list anymore', async() => {
        let response = await screeningList.checkIsBlackListed('BX23456765');
        assert.equal(response, false, 'Error in checking a removed black list customer');
    });
    
    it('Should be able to add a white listed customer', async() => {
        await screeningList.addWhiteListedCustomer(
            'JKL907653',
            'Dmitri'
        );
    });

    it('Should be able to retrieve a white listed customer', async() => {
        let response = await screeningList.getWhiteListedCustomer('JKL907653');
        assert.equal(web3.toAscii(response).replace(/\0/g, ''), "Dmitri", "Error in retrieving a white listed customer");
    });

    it('Should be able to check whether a customer is white listed or not', async() => {
        let response = await screeningList.checkIsWhiteListed('JKL907653');
        assert.equal(response, true, "Error in checking a white listed customer");
    });

    it('Should be able to add another white listed customer', async() => {
        await screeningList.addWhiteListedCustomer(
            'DFG567421',
            'Sean'
        );
    });

    it('Should be able to remove a customer from white list', async() => {
        await screeningList.removeBlackListedCustomer('JKL907653');
    });

    it('Should be able to check the removed customer does not exist in white list anymore', async() => {
        let response = await screeningList.checkIsBlackListed('JKL907653');
        assert.equal(response, false, 'Error in checking a removed black list customer');
    });
});