import axios from 'axios'

const EMPLOYEE_BASE_REST_API_URL='http://localhost:8081/employees';

class EmployeeService{

    getAllEmployees()
    {
         return axios.get(EMPLOYEE_BASE_REST_API_URL+"/showAll")
    }

    createEmployee(employee)
    {
        return axios.post(EMPLOYEE_BASE_REST_API_URL+"/insertEmployee",employee)
    }

    getEmployeeById(id)
    {
        return axios.get(EMPLOYEE_BASE_REST_API_URL+"/findByID/"+id)
    }

    updateEmployee(id,employee)
    {
        return axios.put(EMPLOYEE_BASE_REST_API_URL+"/update/"+id,employee)
    }
    deleteEmployee(id)
    {
        return axios.delete(EMPLOYEE_BASE_REST_API_URL+"/delete/"+id)
    }
}

export default new EmployeeService();