import express from 'express';
const  { 
  createCustomerDetails,
  updateCustomerDetails,
  getAllCustomerDetails,
  getCustomerDetails,
 } = require('../business/customer.service');

const router = express.Router();

router.post('/', async (req, res) => {
  const { account, name, customerType, kycStatus, isParentCustomer } = req.body;
  try {
    let customerDetails = await createCustomerDetails(account, name, customerType, kycStatus, isParentCustomer);
    res.status(200).json(customerDetails);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.put('/:address', async (req, res) => {
  const { address } = req.params;
  const { account, name, customerType, kycStatus, isParentCustomer } = req.body;
  try {
    await updateCustomerDetails(address, account, name, customerType, kycStatus, isParentCustomer);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.get('/:accountNumber', async (req, res) => {
  try {
    const { accountNumber } = req.params;    
    let customerDetails = await getCustomerDetails(accountNumber);
    res.status(200).json(customerDetails);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }  
});

router.get('/', async (req, res) => {
  try {
    let customerDetailsList = await getAllCustomerDetails();
    res.status(200).json(customerDetailsList);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

export default router;
