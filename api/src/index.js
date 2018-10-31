import express from 'express';
import bodyParser from 'body-parser';
import cors from 'cors';

import kyc from './routes/kyc';

const app = express();

app.use(cors({}));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.use('/kyc', kyc);
app.get('/healthcheck', async (req, res) => res.sendStatus(200));

const main = async () => {
  try {
      app.listen(8080);
      console.log("Application started on port 8080");
  } catch (err) {    
    process.exit(1);
  }
};

main().catch(console.error);
