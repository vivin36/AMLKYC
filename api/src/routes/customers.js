import express from 'express';
import multer from 'multer';
import path from 'path';

const  { 
  createCustomerDetails,
  updateCustomerDetails,
  getAllCustomerDetails,
  getCustomerDetails,
  validateAndParseFile
 } = require('../business/customer.service');

const router = express.Router();

let storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, 'uploads/')
  },
  filename: (req, file, cb) => {
    cb(null, file.originalname)
  }
});

let upload = multer({
  storage: storage,
  fileFilter: function(req, file, callback) {
    var ext = path.extname(file.originalname);    
    if(ext != '.csv') {
      return callback('Only .csv file is allowed', null);
    }    
    callback(null, true);
  }
});

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

router.post('/upload', upload.single('file'), async (req, res) => {
  try {
    let status = await validateAndParseFile(req.file.path);
    res.status(200).json({ 'message' : 'File uploaded successfully'});
  } catch (err) {
    res.status(500).json({ error: err.message });
  } 
});

export default router;
