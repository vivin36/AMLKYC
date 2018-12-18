import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Moment from 'react-moment';
import KYCActions from '../actions/kyc.actions';
import CreateKYCForm from '../components/forms/createKYCForm';

class GetKYCDetails extends React.Component {
  constructor(props) {
    super(props);
    this.state = { accountNumber: '' };
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
      this.props.fetchKYCDetails(this.state.accountNumber);
      
  }
  handleChange(event) {
    this.setState({accountNumber: event.target.value});
  }

  render() {
    const getFormattedDate = (dateTime) => {
      dateTime = new Date(dateTime).toString()
      dateTime = dateTime.split(' ', 4).join(' ');
      return dateTime;
    }
    return (
        <div className="container-fluid">
           <div className="col-md-4 mt-5 ml-5">
                <div className="row">            
                <form  onSubmit={this.handleSubmit}>
                        <label>
                                Account Number:
                                <input type="text" className="ml-5" name="name" onChange={this.handleChange} />
                            </label>
                            <input type="submit" className="ml-5 btn-primary" value="Submit" />
                    </form>               
                </div>
            </div>
        <div className="row">
        <div className="col-md-8 mt-5">
        {this.props.KYCReducer?
       <div className="ml-5"> <h1>KYC details</h1>
        <table className="table table-bordered"> 
        <thead>
          <tr>
            <th>
                  Name
                </th>               
                <th>
                  Customer Type - I
                </th>
                <th>
                  Customer Type - II
                </th>               
             {/*    <th>
                IsParent
                </th>    */}
                </tr>             
                </thead>
                <tbody>          
            <tr>
                 <td>{(this.props.KYCReducer.name)}</td>                 
                 <td>{this.props.KYCReducer.customerType == 0 ? 'Banking' : this.props.KYCReducer.customerType == 1 ? 'Retail' : 'Corporate'}</td>
                 <td>{this.props.KYCReducer.isParent == true ? 'Parent' : 'Subsidary'}</td>                 
     {/*             <td>{(this.props.KYCReducer.isParentCustomer)}</td>  */}               
               </tr></tbody>  
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
