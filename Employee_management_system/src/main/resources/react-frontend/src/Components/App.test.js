import { render, screen, cleanup, fireEvent } from "@testing-library/react";
// Importing the jest testing library

import '@testing-library/jest-dom'; 

import HeaderComponent from "./HeaderComponent";
// afterEach function runs after each test suite is executed

afterEach(() => {
    cleanup(); // Resets the DOM after each test suite
})

describe("Add or update employees",()=>{
    const title=jest.fn(); //mock jest function
    render(
                         <form data-testid="form" onSubmit={title}>
                             <div >
                                 <label data-testid="name">Name</label>
                                 <input
                                    data-testid="nameIP"
                                     type = "text"
                                     placeholder = "Enter name"
                                     name = "name"
                                     className = "form-control"
                                     onChange = {title}  
                                 >
                                 </input>
                             </div>
                             <div >
                                 <label data-testid="date">DOJ</label>
                             </div>
                             <div >
                                 <label data-testid="id">BasicPay</label>
                                 <input
                                 data-testid="basicIP"
                                     type = "text"
                                     placeholder = "Enter basic pay per year"
                                     name = "basicPay"
                                     className = "form-control" 
                                     onChange = {title}  
                                 >
                                 </input>
                             </div>
                             <div className='text-center'>
                             <button data-testid="buttonForAddUpdate" onClick={title}>Update</button>
                             </div>
                             
                         </form>
                     
                ); 
    const button = screen.getByTestId("buttonForAddUpdate"); 
    const name=screen.getByTestId("name");
    const bp=screen.getByTestId("id");
    const date=screen.getByTestId("date");
    const form=screen.getByTestId("form");
    const ip1=screen.getByPlaceholderText("Enter name");
    const ip2=screen.getByPlaceholderText("Enter basic pay per year");

    // Test 1
    test("Rendering", () => {
        expect(button).toBeInTheDocument(); 
        expect(name).toBeInTheDocument();
        expect(bp).toBeInTheDocument();
        expect(date).toBeInTheDocument();
        expect(form).toBeInTheDocument();
        expect(ip1).toBeDefined();
        expect(ip2).toBeDefined();
    })
    
    // Test 2
    test("Text content", () => {
        expect(button).toHaveTextContent('Update');
        expect(name).toHaveTextContent('Name');
        expect(bp).toHaveTextContent('BasicPay');
        expect(date).toHaveTextContent('DOJ');
    });

    // Test 3
    test("Click and submit",()=>{
        fireEvent.click(button);
        fireEvent.submit(form);
        fireEvent.change(ip1);
        fireEvent.change(ip2);
    })
})

describe("Header Component",()=>{
    render(<HeaderComponent/>)
    const heading=screen.findByRole('h4');
    // Test 5
    test("Check Header Component",()=>{
        expect(heading).toBeDefined();
    })

})

describe("List employees",()=>{
    const title=jest.fn;
    render(
        <div >
            <h2 > List Employees </h2>
                <div >
                    <input placeholder="Search Employee by ID, Name or DateOfJoining"
                            id='search'
                            
                    /> 
                </div>
                <table data-testid="table">
                    <thead >
                    <th > Employee Id </th>
                    <th > Name</th>
                    <th > Date of Joining</th>
                    <th > Basic Pay</th>
                    <th > DA </th>
                    <th > HRA</th>
                    <th > Gross Salary</th>
                    <th > Tax</th>
                    <th > Net Salary</th>
                    <th >Action</th>
                    </thead>
                    <tbody>
                    <tr > 
                        <td> employee1_id </td>
                        <td> employee1_name</td>
                        <td> employee1_doj</td>
                        <td> employee1_bp</td>
                        <td> employee1_da</td>
                        <td> employee1_hra</td>
                        <td> employee1_gross</td>
                        <td> employee1_tax</td>
                        <td> employee1_net</td>
                        <td>
                        <button data-testid="button" onClick={title}>Cancel</button>
                        </td>
                    </tr>
        </tbody>
    </table>
</div>
    )
    const heading=screen.findByRole('h2');
    const ip1=screen.getByPlaceholderText("Search Employee by ID, Name or DateOfJoining");

    // Test 6
    test("Rendering",()=>{
        expect(ip1).toBeDefined();
        expect(heading).toBeDefined();
    })
})