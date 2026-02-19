package com.Auditing_Tutorial.demo;

import com.Auditing_Tutorial.demo.client.EmployeeClient;
import com.Auditing_Tutorial.demo.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;



	@Test
	void getAllEmployees()
	{
		List<EmployeeDTO> employeeDTOList=employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}
	@Test
	void getEmployeeById()
	{
		EmployeeDTO employeeDTO=employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	void createNewEmployeeTest()
	{

		EmployeeDTO employeeDTO=new EmployeeDTO(null, "Mayank","mayank@gmail.com",20,
				LocalDate.of(2020,12,12), true,"USER",11,5000.0);
		EmployeeDTO savedEmployeeDTO=employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}

}
