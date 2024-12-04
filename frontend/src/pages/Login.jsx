import React, { useState } from 'react';
import "./Login.css" ;  
// import validation from "../utils/ValidateLogin.js";
import { Link } from "react-router-dom";
const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState({
        password : ""
        ,email :""
    });

    const handleSubmit = (e) => {
        
            e.preventDefault();
        
            // const validationErrors = validation({ email, password });
            
            // setError(validationErrors);
        
            // if (validationErrors.email === "" && validationErrors.password === "") {
            //     console.log('Logging in with:', { email, password });
            // }
        
        

        
    };

    return (
        <div className="logger">
        <div className="login-container">
            <h2>Login</h2>
           
            <form onSubmit={handleSubmit} className="login-form">
                <div className="input-container">
                    <label>Email:</label>
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="input"
                        placeholder="Enter your email"
                    />
                     {error.email && <p className="error">{error.email}</p>}
                </div>
                <div className="input-container">
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="input"
                        placeholder="Enter your password"
                    />
                     {error.password && <p className="error">{error.password}</p>}
                </div>
                <button type="submit" className="login-button">
                    Login
                </button>
            </form>
            <Link to="/signup" style={{color :'purple' }}>you don't an account!</Link>
            </div>
        </div >
    );
};

export default Login;
