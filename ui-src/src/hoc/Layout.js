import React, { Component } from "react";
import Header from "../components/Header";
import NavBar from "../components/navbar";
class Layout extends Component {
  state = {};

  render() {
    return (
      <div>
        <Header />

        {/*  <NavBar></NavBar> */}
        <div>{this.props.children}</div>
      </div>
    );
  }
}

export default Layout;
