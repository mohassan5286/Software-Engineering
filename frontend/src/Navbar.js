import {Link} from 'react-router-dom'

const Navbar = () => {
    return (
        <nav className="navbar">
            <div className="navbar-brand"><Link className = "navbar-brand" to = '/'>Path Port</Link></div>
        </nav>  
    );
};

export default Navbar;
