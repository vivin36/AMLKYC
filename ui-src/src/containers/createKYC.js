import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Moment from 'react-moment';
import documentsActions from '../actions/kyc.actions';
import CreateKYCForm from '../components/forms/createKYCForm';

Moment.globalFormat = 'DD MMM YYYY';
class CreateKYC extends React.Component {
  constructor(props) {
    super(props);
    this.state = { transaction: {} };
  }

  componentDidMount() {
    this.props.getData();
  }

  showResults(key) {
    console.log(key);
  }

  render() {
    return (
      <div>
        <div className="row">
          <div className="mt-5 block-position ml-5">
            <h2>Create KYC</h2>
            <CreateKYCForm onSubmit={this.showResults.bind(this)} />
          </div>
        </div>
      </div>
    );
  }
}

CreateKYC.propTypes = {
  getData: PropTypes.func.isRequired,
};

const mapDispatchToProps = dispatch => ({
  getData: () => {
    documentsActions.getDocumentsList()(dispatch);
  },
});

const mapStateToProps = (state, ownProps) => {
  const id = ownProps.match.params.block_id;
  return {
    id,
  };
};
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(CreateKYC);
