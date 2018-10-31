import React from 'react';
import { Field, reduxForm } from 'redux-form';

const CreateKYCForm = (props) => {
  const {
    handleSubmit, pristine, reset, submitting,
  } = props;
  return (
    <form className="form-group" onSubmit={handleSubmit}>
      <div>
        <label>First Name</label>
        <div>
          <Field
            name="firstName"
            component="input"
            type="text"
            placeholder="First Name"
          />
        </div>
      </div>
      <div>
        <label>Last Name</label>
        <div>
          <Field
            name="lastName"
            component="input"
            type="text"
            placeholder="Last Name"
          />
        </div>
      </div>
      <div>
        <label>DOB</label>
        <div>
          <Field
            name="DOB"
            component="input"
            type="text"
            placeholder="mm/dd/yyyy"
          />
        </div>
      </div>

      <div>
        <label>Sex</label>
        <div>
          <label>
            <Field name="sex" component="input" type="radio" value="M" /> Male
          </label>
          <label>
            <Field name="sex" component="input" type="radio" value="F" /> Female
          </label>
        </div>
      </div>

      <div>
        <label>Address</label>
        <div>
          <Field name="address" component="textarea" />
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
