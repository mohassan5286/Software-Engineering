import React, { useState } from 'react';
import "./Login.css" ;  
import validation from "../utils/ValidateLogin.js";
import { Link } from 'react-router-dom';
const Signup = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [error, setError] = useState({
        password : ""
        ,email :""
    });

    const handleSubmit = (e) => {
        
            e.preventDefault();
        
            const validationErrors = validation({ email, password });
            
            setError(validationErrors);
        
            if (validationErrors.email === "" && validationErrors.password === "") {
                console.log('signing up in with:', { email, password });
            }
        
    };

    return (
        <div className="logger">
        <div className="login-container">
            <h2>Sign up</h2>
           
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
                    <label>name:</label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        className="input"
                        placeholder="Enter your name"
                    />
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
                    sign up
                </button>
            </form>
            <Link to="/login" style={{color :'purple' }}>you already an account!</Link>

        </div>
        </div >
    );
};

export default Signup;
