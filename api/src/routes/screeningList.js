import express from 'express';
const {
  blackListCustomer
} = require('../business/screeningList.service');

const router = express.Router();

router.post('/', async (req, res) => {
  const { accountNumber, name } = req.body;
  try {    
    await blackListCustomer(accountNumber, name);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

export default router;