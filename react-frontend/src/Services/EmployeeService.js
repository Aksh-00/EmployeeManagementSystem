import axios from 'axios'

//API endpoint 
const EMPLOYEE_BASE_REST_API_URL='http://localhost:8081/employees';

class EmployeeService{

    //Axios call to list all the employees
    getAllEmployees()
    {
         return axios.get(EMPLOYEE_BASE_REST_API_URL+"/showAll")
    }

    //Axios call to insert a new employee
    createEmployee(employee)
    {
        return axios.post(EMPLOYEE_BASE_REST_API_URL+"/insertEmployee",employee)
    }

    //Axios call to get an employee by Id
    getEmployeeById(id)
    {
        return axios.get(EMPLOYEE_BASE_REST_API_URL+"/findByID/"+id)
    }

    //Axios call to update employee details
    updateEmployee(id,employee)
    {
        return axios.put(EMPLOYEE_BASE_REST_API_URL+"/update/"+id,employee)
    }

    //Axios call to delete employee by ID
    deleteEmployee(id)
    {
        return axios.delete(EMPLOYEE_BASE_REST_API_URL+"/delete/"+id)
    }
}

export default new EmployeeService();