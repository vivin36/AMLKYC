const KYC = artifacts.require("KYC");

contract('KYC contract test', async (accounts) => {

    it("Create a KYC document in Blockchain and verifying it", async () => {
        let instance = await KYC.deployed();
        await instance.createKYC("0x12345","Name","Sample Address",12,web3.fromAscii('M'),1572432745);
        let response = await instance.getDetailsByID("0x12345");
        assert.equal(response[0], "Name");
    })

    it("Get validity for a KYC document by id", async () => {
        let instance = await KYC.deployed();
        let response = await instance.getValidity("0x12345");
        assert.equal(response, 1572432745);
    })

})