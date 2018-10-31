import express from 'express';
const  { 
  createKYC,
  getKYCDetails
 } = require('../business/kyc.service');

const router = express.Router();

router.post('/', async (req, res) => {
  const { id, name, userAddress, dob, gender, validationEndDate } = req.body;
  try {
    await createKYC(id, name, userAddress, dob, gender, validationEndDate);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.put('/:id', async (req, res) => {

});

router.get('/:id', async (req, res) => {
  try {
    const { id } = req.params;    
    let details = await getKYCDetails(id);
    res.status(200).json(details);
  } catch (err) {
    res.status(500).json({ error: err.message});
  }  
});

export default router;
