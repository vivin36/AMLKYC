import React from 'react';
import PropTypes from 'prop-types';

import SideNav, { NavItem, NavIcon, NavText } from '@trendmicro/react-sidenav';
import '@trendmicro/react-sidenav/dist/react-sidenav.css';
import Select from 'react-select';
import { connect } from 'react-redux';

class NavBar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedOption: null,
    };

    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(selectedOption) {
    if (selectedOption) {
      this.setState({ selectedOption });
      this.props.setProject(selectedOption);
      this.props.getProjectDetails(selectedOption.value);
    }
  }

  componentDidMount() {}

  render() {
    const userlogo = '';
    const imagestyle = {
      borderRadius: '50%',
    };
    const { selectedProject } = '';
    return (
      <SideNav
        style={{ background: '#021324', color: '#FFF' }}
        onSelect={this.props.handleNavigation}
        className="navigationStyle"
        expanded
      >
        <SideNav.Toggle />
        <SideNav.Nav defaultSelected="home">
          <NavItem eventKey="home">
            <NavIcon>
              <i className="fa fa-fw fa-home" style={{ fontSize: '1.75em' }} />
            </NavIcon>
            <NavText>Home</NavText>
          </NavItem>
          <NavItem eventKey="charts">
            <NavIcon>
              <i
                className="fa fa-fw fa-line-chart"
                style={{ fontSize: '1.75em' }}
              />
            </NavIcon>
            <NavText>KYC</NavText>
            <NavItem eventKey="charts/linechart">
              <NavText>Create KYC</NavText>
            </NavItem>
            <NavItem eventKey="charts/barchart">
              <NavText>Get KYC details</NavText>
            </NavItem>
            <NavItem >
              <NavText>Payments</NavText>
            </NavItem>
          </NavItem>
        </SideNav.Nav>
      </SideNav>
    );
  }
}

NavBar.propTypes = {};
const mapStateToProps = (state) => {};

const mapDispatchToProps = dispatch => ({});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(NavBar);
