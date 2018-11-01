const KYC = artifacts.require("KYC");

contract('KYC', async (accounts) => {
    let KYCContract;

    before(async () => {
        KYCContract = await KYC.new();
    }); 

    it("Create a KYC document in Blockchain and verifying it", async () => {
        await KYCContract.createKYC("0x12345","vivin","North Down Street","03/06/1994","M",1572432745);
        let response = await KYCContract.getDetailsByID("0x12345");
        assert.equal(response[0], "vivin");
    })

    it("Get validity for a KYC document by id", async () => {
        let response = await KYCContract.getValidity("0x12345");
        assert.equal(response, 1572432745);
    })

    it("Update an existing KYC document in Blockchain and verifying it", async() => {
        await KYCContract.updateKYC("0x12345","vivin","Middleton Street","03/06/1994","M",1572432745);
        let response = await KYCContract.getDetailsByID("0x12345");
        assert.equal(response[1], "Middleton Street", "Failed to updated KYC document");
    })
})