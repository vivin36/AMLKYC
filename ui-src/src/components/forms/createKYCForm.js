import React from 'react';
import { Field, reduxForm } from 'redux-form';

const CreateKYCForm = (props) => {
  const {
    handleSubmit, pristine, reset, submitting,
  } = props;
  return (
    <form className="form-group" onSubmit={handleSubmit}>
      <div>
        <label>Account Number</label>
        <div>
          <Field
            name="accountNumber"
            component="input"
            type="text"
            placeholder="Account Number"
          />
        </div>
      </div>
      <div>
        <label>Name</label>
        <div>
          <Field
            name="name"
            component="input"
            type="text"
            placeholder="Name"
          />
        </div>
      </div>
      <div>
      <label>Customer Type - I</label>
        <div>
          <label>
            <Field name="customerType" component="input" type="radio" value="0" /> Banking
          </label>
          <label>
            <Field name="customerType" component="input" type="radio" value="1" /> Retail
          </label>
          <label>
            <Field name="customerType" component="input" type="radio" value="2" /> Corporate
          </label>
        </div>
      </div>
      <div>
        <label>Customer Type - II</label>
        <div>
          <label>
            <Field name="isParent" component="input" type="radio" value="true"/> Parent
          </label>
          <label>
            <Field name="isParent" component="input" type="radio" value="false"/> Subsidiary
          </label>
        </div>
      </div> 
      <div>
        <button
          type="submit"
          className="mr-2 btn-primary"
          disabled={pristine || submitting}
        >
          Submit
        </button>
        <button
          type="button"
          className="btn-danger"
          disabled={pristine || submitting}
          onClick={reset}
        >
          Clear Values
        </button>
      </div>
    </form>
  );
};

export default reduxForm({
  form: 'CreateKYCForm', // a unique identifier for this form
})(CreateKYCForm);
