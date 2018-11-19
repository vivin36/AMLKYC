import navbarConstants from '../constants/navbarConstants';

const setProject = selectedProjects => (dispatch) => {
  dispatch({
    type: navbarConstants.SELECT_PROJECT_SUCCESS,
    payload: selectedProjects,
  });
};

export default {
  setProject,
};
