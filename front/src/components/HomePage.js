import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "./HomePage.css";

function HomePage(props) {
  return (
    <div>
      <div class="sidenav">
        <a href="#">About</a>
        <a href="#">Services</a>
        <a href="#">Clients</a>
        <a href="#">Contact</a>
      </div>

      <div class="main">
        <h2>Sidenav Example</h2>
        <p>This sidenav is always shown.</p>
      </div>
    </div>
  );
}

export default HomePage;
