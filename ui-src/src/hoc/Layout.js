import React, { Component } from 'react';
import Header from '../components/Header';
class Layout extends Component {
  state={}

  render() {
    return (
      <div>
         <Header/>
        {/* {!isHomePage
          && isLoggedIn && (
            <NavBar
              handleNavigation={this.handleNavigation.bind(this)}
              user={token}
            />
        )}
        <div className={isLoggedIn ? '' : 'container-fluid'}>
          <ToastContainer
            position="top-right"
            autoClose={2000}
            hideProgressBar={true}
            newestOnTop={false}
            closeOnClick
            pauseOnVisibilityChange
            draggable
            pauseOnHover
          />

          {!isRequestFulfilled && (
            <div
              className="progress progress-override"
              style={{ position: 'relative' }}
            >
              <div className="progress-bar progress-bar-striped indeterminate" />
            </div>
          )} */}
          <div>
          {this.props.children}
        </div>
      </div>
    );
  }
}

export default Layout;
