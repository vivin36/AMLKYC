import express from 'express';
const {
  blackListCustomer,getAllBlackListedCustomers,whiteListCustomer,getAllwhiteListCustomers,checkIsWhiteListed
,removeFromBlackListedCustomer,removeFromWhiteListedCustomer} = require('../business/screeningList.service');

const router = express.Router();

router.post('/blacklists', async (req, res) => {
  const { accountAddress, name,identificationNumber } = req.body;
  try {
    await blackListCustomer(accountAddress, name,identificationNumber);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});


router.get('/blacklists', async (req, res) => {
  try {
    let response = await getAllBlackListedCustomers();
    res.status(200).json(response);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.post('/whiteLists', async (req, res) => {


 try {
    const { accountAddress, name,identificationNumber } = req.body;
    await whiteListCustomer(accountAddress, name,identificationNumber);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.get('/whiteLists', async (req, res) => {
  try {
    let response = await getAllwhiteListCustomers();
    res.status(200).json(response);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.get('/whiteLists/:accountAddress', async (req, res) => {
  try {
    let accountAddress = req.params.accountAddress;
    let response = await checkIsWhiteListed(accountAddress);
    res.status(200).json(response);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.delete('/whitelists/:accountAddress', async (req, res) => {
  try {
    let accountAddress = req.params.accountAddress;
    await removeFromWhiteListedCustomer(accountAddress);
    res.status(200).json(req.params.accountAddress);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.delete('/blacklists/:accountAddress', async (req, res) => {
  try {
    let accountAddress = req.params.accountAddress;
    await removeFromBlackListedCustomer(accountAddress);
    res.status(200).json(req.params.accountAddress);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

export default router;