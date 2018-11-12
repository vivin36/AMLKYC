import express from 'express';
const {
  blackListCustomer,getAllBlackListedCustomers,whiteListCustomer,getAllwhiteListCustomers
} = require('../business/screeningList.service');

const router = express.Router();

router.post('/blacklistedCustomers', async (req, res) => {
  const { accountAddress, name,identificationNumber } = req.body;
  try {
    console.log(accountAddress, name,identificationNumber);
    await blackListCustomer(accountAddress, name,identificationNumber);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});


router.get('/blacklistedCustomers', async (req, res) => {
  try {
    let response = await getAllBlackListedCustomers();
    console.log(response);
    res.status(200).json(response);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.post('/whiteListedCustomers', async (req, res) => {

  const { accountAddress, name,identificationNumber } = req.body;
  console.log('accountAddress, name,identificationNumber',accountAddress, name,identificationNumber);
  try {
    console.log(accountAddress, name,identificationNumber);
    await whiteListCustomer(accountAddress, name,identificationNumber);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.get('/whiteListedCustomers', async (req, res) => {
  try {
    let response = await getAllwhiteListCustomers();
    console.log(response);
    res.status(200).json(response);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});






export default router;