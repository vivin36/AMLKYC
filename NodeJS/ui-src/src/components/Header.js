import React from 'react';

class Header extends React.Component {
  render() {
    return (
      <nav className="navbar navbar-dark bg-dark">
        <a className="navbar-brand" href="#">
          AML KYC
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarText"
          aria-controls="navbarText"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon" />
        </button>
        <div className="collapse navbar-collapse" id="navbarText">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item active">
              <a className="nav-link" href="/KYC">
                Create KYC <span className="sr-only">(current)</span>
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/fetchKYC">
                Get KYC details By ID
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/redeempayments">
                Redeem payments List
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/transferpayments">
                Transfer payments List
              </a>
            </li>
          </ul>
        </div>
      </nav>
    );
  }
}

export default Header;
