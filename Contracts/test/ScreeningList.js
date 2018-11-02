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
        assert.equal(response, "Arjun", "Error in retrieving a black listed customer");
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

    it('Should be able to check the removed customer does not exist in blacklist anymore', async() => {
        let response = await screeningList.checkIsBlackListed('BX23456765');
        assert.equal(response, false, 'Error in checking a removed black list customer');
    });
});