const ScreeningList = artifacts.require('ScreeningList');

contract('ScreeningList', () => {
    let screeningList;

    before(async () => {
        screeningList = await ScreeningList.new();
    });

    it('Should be able to add a white listed company', async() => {
        await screeningList.addWhiteListedCompany(
            'CPY6789A',
            'Microsoft',
            'GHJKS8976A'
        );
    });

    it('Should be able to add another white listed company', async() => {
        await screeningList.addWhiteListedCompany(
            'KPL1212F',
            'DC',
            'KLHNB6754A'
        );
    });

    it('Should be able to add a black listed company', async() => {
        await screeningList.addBlackListedCompany(
            'MLU5612Q',
            'TMC',
            'BNVCF4562Q'
        );
    });

    it('Should be able to blacklist a company', async() => {
        await screeningList.blacklistCompany(
            'KPL1212F'            
        );
    });

    it('Should be able to retrieve a white listed company', async() => {
        let response = await screeningList.getWhiteListedCompany('CPY6789A');
        assert.equal(response[0], "Microsoft", "Error in retrieving white listed company");
    });

    it('Should be able to retrieve a black listed company', async() => {
        let response = await screeningList.getBlackListedCompany('MLU5612Q');
        assert.equal(response[0], "TMC", "Error in retrieving black listed company");
    });

    it('Should be able to retrieve the company post black listing', async() => {
        let response = await screeningList.getBlackListedCompany('KPL1212F');
        assert.equal(response[0], "DC", "Error in retrieving black listed company");
    });

    it('Should not be able to retrieve the company from white list post black listing', async() => {
        let response = await screeningList.getWhiteListedCompany('KPL1212F');
        assert.equal(response[0], "", "Company still exists in white list");
    });
});