import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css'
import Header from './components/Header.jsx'
import Home  from './pages/Home.jsx';
import Login from './pages/Login.jsx'
import Nopage from './pages/Nopage.jsx';
import Cart from './pages/Cart.jsx';
import Signup from "./pages/Signup.jsx"

function App() {
  

  return (
    <>
     <BrowserRouter>
     <Header/>
    
    <Routes>
    
        <Route path="/home" element={<Home/>}/>
        <Route path="/" element={<Home/>}/>
        <Route path="/login" element={<Login />} />
        <Route path="/Sold" element={<Login />} />
        <Route path="/orders" element={<Login />} />
        <Route path="/Sell" element={<Login />} />
        <Route path="/Cart" element={<Cart />} />
        <Route path="/orders" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/*" element={<Nopage />} />
      
    </Routes>
  </BrowserRouter>
     
    </>
  )
}

export default App
