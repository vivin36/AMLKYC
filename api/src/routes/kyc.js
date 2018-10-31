import express from 'express';
const  { createKYC } = require('../business/kyc.service');

const router = express.Router();

router.post('/', async (req, res) => {
  const { id, name, userAddress, age, gender, validationEndDate } = req.body;
  try {
    await createKYC(id, name, userAddress, age, gender, validationEndDate);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.put('/:id', async (req, res) => {

});

router.get('/:id', async (req, res) => {
  const { id } = req.params.id;
});

export default router;
