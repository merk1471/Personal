require ('dotenv').config()

const express = require("express")
const port = 3000
const bcrypt = require('bcrypt')
const cors = require('cors')
const bodyParser = require('body-parser')
const mongoose = require('mongoose')

const app = express();

app.use(bodyParser.json());
mongoose.connect(process.env.DATABASE_URL)
const db = mongoose.connection 
db.on('error', (error) => console.error(error))
db.once('open', () => console.log('Connected to Database'))

app.use(cors());
app.use(express.json());
app.set()

const userSchema = new mongoose.Schema({
    username: { type: String, unique: true, required: true },
    password: { type: String, required: true },
  });
  
  const User = mongoose.model('User', userSchema);

app.get('/', (req, res) => {
    res.send('Hello World!')
})

app.post('/login', async (req, res) => {
    console.log(req.body)
    const { username, password } = req.body;
  
    try {
      const user = await User.findOne({ username });
  
      if (!user) {
        return res.status(401).json({ message: 'Invalid credentials' });
      }
  
      const isPasswordValid = await bcrypt.compare(password, user.password);
  
      if (!isPasswordValid) {
        return res.status(401).json({ message: 'Invalid credentials' });
      }
  
      res.json({ message: 'Login successful' });
    } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'Internal server error' });
    }
  });
  

app.post('/register', async (req, res) => {
    const { username, password } = req.body;
  
    try {
      const hashedPassword = await bcrypt.hash(password, 10);
      const user = new User({ username, password: hashedPassword });
      await user.save();
      res.status(201).json({ message: 'User registered successfully' });
    } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'Internal server error' });
    }
  });



app.listen(port, () =>{
    console.log(`port connected on port ${port}`);
})
