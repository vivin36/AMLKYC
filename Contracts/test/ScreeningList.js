const ScreeningList = artifacts.require('ScreeningList');

contract('ScreeningList', () => {
    let screeningList;

    before(async () => {
        screeningList = await ScreeningList.new({privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]});
    });

    it('Should be able to add a white listed company', async() => {
        await screeningList.addWhiteListedCompany(
            'CPY6789A',
            'Microsoft',
            'GHJKS8976A',
            {privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]}
        );
    });

    it('Should be able to add another white listed company', async() => {
        await screeningList.addWhiteListedCompany(
            'KPL1212F',
            'DC',
            'GHJKS8976A',
            {privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]}
        );
    });

    it('Should be able to add a black listed company', async() => {
        await screeningList.addBlackListedCompany(
            'MLU5612Q',
            'TMC',
            'BNVCF4562Q',
            {privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]}
        );
    });

    it('Should be able to blacklist a company', async() => {
        await screeningList.blacklistCompany(
            'KPL1212F',
            true,
            {privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]}
        );
    });
});