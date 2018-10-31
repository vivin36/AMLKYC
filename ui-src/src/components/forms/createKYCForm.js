import React from 'react';
import { Field, reduxForm } from 'redux-form';

const CreateKYCForm = (props) => {
  const {
    handleSubmit, pristine, reset, submitting,
  } = props;
  return (
    <form className="form-group" onSubmit={handleSubmit}>
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
        <label>DOB</label>
        <div>
          <Field
            name="dob"
            component="input"
            type="date"
            placeholder="mm/dd/yyyy"
          />
        </div>
      </div>
      <div>
        <label>National ID</label>
        <div>
          <Field
            name="id"
            component="input"
            type="text"
            placeholder="ID"
          />
        </div>
      </div>

      <div>
        <label>Gender</label>
        <div>
          <label>
            <Field name="gender" component="input" type="radio" value="M" /> Male
          </label>
          <label>
            <Field name="gender" component="input" type="radio" value="F" /> Female
          </label>
        </div>
      </div>

      <div>
        <label>Address</label>
        <div>
          <Field name="userAddress" component="textarea" />
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
