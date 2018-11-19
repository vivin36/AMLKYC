import React from 'react';

const date = new Date();

const Footer = () => (
    <footer>
      <div className="footer footer-wrapper bg-dark">
        <div className="footer-copyright">copyright-{date.getFullYear()}</div>
      </div>
    </footer>
);

export default Footer;
