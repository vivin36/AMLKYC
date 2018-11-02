const KYC = artifacts.require("KYC");
const ScreeningList = artifacts.require('ScreeningList');

contract('KYC', async (accounts) => {
    let KYCContract;
    let screeningList;

    before(async () => {
        screeningList = await ScreeningList.new();
        KYCContract = await KYC.new(screeningList.contract.address);
    }); 

    it("Create a KYC document in Blockchain and verifying it", async () => {
        await KYCContract.createKYC("0x12345","vivin",0,0,true,1572432745);
        let response = await KYCContract.getDetailsByID("0x12345");
        assert.equal(response[0], "vivin");
    })

    it("Get validity for a KYC document by account Number", async () => {
        let response = await KYCContract.getValidity("0x12345");
        assert.equal(response, 1572432745);
    })

    it("Update an existing KYC document in Blockchain and verifying it", async() => {
        await KYCContract.updateKYC("0x12345","Prasannaa",1,1,true,1572432745);
        let response = await KYCContract.getDetailsByID("0x12345");
        assert.equal(response[0], "Prasannaa", "Failed to updated KYC document");
    })
})