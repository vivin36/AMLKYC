import express from 'express';
import multer from 'multer';
import path from 'path';

const {
  blackListCustomer,
  getAllBlackListedCustomers,
  whiteListCustomer,
  getAllwhiteListCustomers,
  checkIsWhiteListed,
  removeFromBlackListedCustomer,
  removeFromWhiteListedCustomer,
  uploadWhiteListFile,
  uploadBlackListFile
} = require('../business/screeningList.service');

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

router.post('/blacklists', async (req, res) => {
  const {
    accountAddress,
    name,
    identificationNumber
  } = req.body;
  try {
    await blackListCustomer(accountAddress, name, identificationNumber);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({
      error: err.message
    });
  }
});


router.get('/blacklists', async (req, res) => {
  try {
    let response = await getAllBlackListedCustomers();
    res.status(200).json(response);
  } catch (err) {
    res.status(500).json({
      error: err.message
    });
  }
});

router.post('/whiteLists', async (req, res) => {


  try {
    const {
      accountAddress,
      name,
      identificationNumber
    } = req.body;
    await whiteListCustomer(accountAddress, name, identificationNumber);
    res.status(200).json(req.body);
  } catch (err) {
    res.status(500).json({
      error: err.message
    });
  }
});

router.get('/whiteLists', async (req, res) => {
  try {
    let response = await getAllwhiteListCustomers();
    res.status(200).json(response);
  } catch (err) {
    res.status(500).json({
      error: err.message
    });
  }
});

router.get('/whiteLists/:accountAddress', async (req, res) => {
  try {
    let accountAddress = req.params.accountAddress;
    let response = await checkIsWhiteListed(accountAddress);
    res.status(200).json(response);
  } catch (err) {
    res.status(500).json({
      error: err.message
    });
  }
});

router.delete('/whitelists/:accountAddress', async (req, res) => {
  try {
    let accountAddress = req.params.accountAddress;
    await removeFromWhiteListedCustomer(accountAddress);
    res.status(200).json(req.params.accountAddress);
  } catch (err) {
    res.status(500).json({
      error: err.message
    });
  }
});

router.delete('/blacklists/:accountAddress', async (req, res) => {
  try {
    let accountAddress = req.params.accountAddress;
    await removeFromBlackListedCustomer(accountAddress);
    res.status(200).json(req.params.accountAddress);
  } catch (err) {
    res.status(500).json({
      error: err.message
    });
  }
});

router.post('/whitelists/upload', upload.single('file'), async (req, res) => {
  try {
    let result = await uploadWhiteListFile(req.file.path);
    res.status(200).json({ 'message' : 'File uploaded successfully'});
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

router.post('/blacklists/upload', upload.single('file'), async (req, res) => {
  try {
    let result = await uploadBlackListFile(req.file.path);
    res.status(200).json({ 'message' : 'File uploaded successfully'});
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

export default router;