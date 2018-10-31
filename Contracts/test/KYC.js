const KYC = artifacts.require("KYC");

contract('KYC contract test', async (accounts) => {
    let KYCContract;

    before(async () => {
        KYCContract = await KYC.new({privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]});
    }); 

    it("Create a KYC document in Blockchain and verifying it", async () => {
        await KYCContract.createKYC("0x12345","vivin","North Down Street","03/06/1994","M",1572432745,  {privateFor: ["oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="]});
        let response = await instance.getDetailsByID("0x12345");
        assert.equal(response[0], "Name");
    })

    it("Get validity for a KYC document by id", async () => {
        let response = await KYCContract.getValidity("0x12345");
        assert.equal(response, 1572432745);
    })

})