const express = require('express')
const router = express.Router()
const User = require('../models/user')
const bcrypt = require('bcrypt')
const app = express()



// const userSchema = new mongoose.Schema({
//   username: { type: String, unique: true, required: true },
//   password: { type: String, required: true },
// });

// const User = mongoose.model('User', userSchema);

router.get('/', async (req, res) => {
  try {
    const users = await User.find();
    res.json(users);
  } catch (err) {
    console.error(err); // Log the error to the console
    res.status(500).json({ message: 'Internal server error' });
  }
});


router.post('/register', async (req,res) => {  
  const { name, password } = req.body;

  try {
      // Check if the username already exists
      const existingUser = await User.findOne({ name });

      if (existingUser) {
          return res.status(400).json({ message: 'Username already exists' });
      }

      // If username is unique, proceed with registration
      const hashedPassword = await bcrypt.hash(password, 10);
      const user = new User({ name, password: hashedPassword });
      await user.save();

      res.status(201).json({ message: 'User registered successfully' });
   } catch (err) {
    res.status(400).json ({message: err.message})
   }
})

router.post('/login', async (req,res) => {  
  const { name, password } = req.body;

  try {
      // Check if the username already exists
      const existingUser = await User.findOne({ name });

      if (!existingUser) {
        console.log('user does not exist')
          return res.status(400).json({ message: 'User does not exist' });
      }

      const correctPassword = await bcrypt.compare(password, existingUser.password)
      
      if (!correctPassword){
        console.log("wrong password")
        return res.status(401).json({message: 'invalid credentials'})
      }

      console.log('user was found')
      return res.status(200).json({ success: true, message: 'Login successful', user: existingUser });

   } catch (err) {
    res.status(500).json ({message: err.message})
   }
})


// router.post('/', (req,res) =>{

// })

router.delete(':id', (req,res) => {

})


// app.post('/login', async (req, res) => {
//     console.log(req.body)
//     const { username, password } = req.body;
  
//     try {
//       const user = await User.findOne({ username });
  
//       if (!user) {
//         return res.status(401).json({ message: 'Invalid credentials' });
//       }
  
//       const isPasswordValid = await bcrypt.compare(password, user.password);
  
//       if (!isPasswordValid) {
//         return res.status(401).json({ message: 'Invalid credentials' });
//       }
  
//       res.json({ message: 'Login successful' });
//     } catch (error) {
//       console.error(error);
//       res.status(500).json({ message: 'Internal server error' });
//     }
//   });
  

// app.post('/register', async (req, res) => {
//     const { username, password } = req.body;
  
//     try {
//       const hashedPassword = await bcrypt.hash(password, 10);
//       const user = new User({ username, password: hashedPassword });
//       await user.save();
//       res.status(201).json({ message: 'User registered successfully' });
//     } catch (error) {
//       console.error(error);
//       res.status(500).json({ message: 'Internal server error' });
//     }
//   });

  module.exports = router