import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Moment from 'react-moment';
import KYCActions from '../actions/kyc.actions';
import CreateKYCForm from '../components/forms/createKYCForm';

class GetKYCDetails extends React.Component {
  constructor(props) {
    super(props);
    this.state = { nationalID: '' };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
  }

  showResults(KYCDetails) {
    console.log(KYCDetails);
    this.props.createKYC(KYCDetails);
  }

  handleSubmit(event) {
      event.preventDefault();
      this.props.fetchKYCDetails(this.state.nationalID);
      
  }
  handleChange(event) {
    this.setState({nationalID: event.target.value});
  }

  render() {
    return (
        <div className="container-fluid">
           <div className="col-md-4 mt-5">
                <div className="row">            
                <form  onSubmit={this.handleSubmit}>
                        <label>
                                National ID:
                                <input type="text" className="ml-5" name="name" onChange={this.handleChange} />
                            </label>
                            <input type="submit" className="ml-5 btn-primary" value="Submit" />
                    </form>               
                </div>
            </div>
        <div className="row">
        <div className="col-md-8 mt-5">
        {this.props.KYCReducer?
       <div> <h1>KYC details</h1>
        <table className="table table-bordered"> 
        <thead ><th>
                 Name
               </th>
               <th>
               Address
               </th>
               <th>
                Date of birth
               </th>
               <th>
               Gender
               </th>
               <th>
               validated Date
               </th>
               <th>
               validation End Date
               </th>
               </thead>
              <tr>
                 <td>{this.props.KYCReducer.name}</td>
                 <td>{this.props.KYCReducer.userAddress}</td>
                 <td>{this.props.KYCReducer.dob}</td>
                 <td>{this.props.KYCReducer.gender}</td>
                 <td>{this.props.KYCReducer.validatedDate}</td> 
                 <td>{this.props.KYCReducer.validationEndDate}</td>                  
               </tr>
             </table></div> :''}
        </div>
         
       
        </div>
    </div>
    );
  }
}

GetKYCDetails.propTypes = {
  fetchKYCDetails: PropTypes.func.isRequired,
  KYCReducer: PropTypes.any,
};

const mapDispatchToProps = dispatch => ({
    fetchKYCDetails: (nationalID) => {
    KYCActions.getKYCDetails(nationalID)(dispatch);
  },
  
});

const mapStateToProps = (state) => {
  let {
    KYCReducer
  } = state;
  KYCReducer = KYCReducer? KYCReducer.KYCDetails.data:{};
  console.log('state', KYCReducer);
  return {
    KYCReducer
  };
 
};
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(GetKYCDetails);
