import express from 'express';
const  { 
  createKYC,
  updateKYC,
  getKYCDetails,
 } = require('../business/kyc.service');

const router = express.Router();

router.post('/', async (req, res) => {
  const { accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate } = req.body;
  try {
    await createKYC(accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.put('/:accountNumber', async (req, res) => {
  const { accountNumber } = req.params;
  const { name, customerType, kycStatus, isParentCustomer, validationEndDate } = req.body;
  try {
    await updateKYC(accountNumber, name, customerType, kycStatus, isParentCustomer, validationEndDate);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.get('/:accountNumber', async (req, res) => {
  try {
    const { accountNumber } = req.params;    
    let details = await getKYCDetails(accountNumber);
    res.status(200).json(details);
  } catch (err) {
    res.status(500).json({ error: err.message});
  }  
});

export default router;
