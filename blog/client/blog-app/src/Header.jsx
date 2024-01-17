import {Link} from "react-router-dom"

export const Header = () => {
    return(
        <header>
          <Link to={'/'} className="logo">Blogify </Link>
          <nav>
            <Link to={"/login"}>Login</Link>
            <Link to="/register">Register</Link>

          </nav>
        </header>
    )
}