import express from 'express';
const  { 
  createCustomerDetails,
  updateCustomerDetails,
  getAllCustomerDetails,
  getCustomerDetails,
 } = require('../business/customer.service');

const router = express.Router();

router.post('/', async (req, res) => {
  const { accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate } = req.body;
  try {
    await createCustomerDetails(accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.put('/:accountNumber', async (req, res) => {
  const { accountNumber } = req.params;
  const { name, customerType, kycStatus, isParentCustomer, validationEndDate } = req.body;
  try {
    await updateCustomerDetails(accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate);
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
