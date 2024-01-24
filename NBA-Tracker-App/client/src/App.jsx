import { Home } from './Home';
import {BrowserRouter as Router,Route, Routes} from 'react-router-dom';
import { LoginPage } from './Pages/loginPage';
import { RegisterPage } from './Pages/registerPage';
import './App.css';
import { Layout } from './layout';

function App() {
  return (
    <Router>
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path='/login' element={<LoginPage/>}/>
        <Route path='/register' element={<RegisterPage/>}/>
      </Route>
      </Routes>
  </Router>
  )
}

export default App
