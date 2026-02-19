package com.Auditing_Tutorial.demo.client.impl;

import com.Auditing_Tutorial.demo.advice.ApiResponse;
import com.Auditing_Tutorial.demo.client.EmployeeClient;
import com.Auditing_Tutorial.demo.dto.EmployeeDTO;
import com.Auditing_Tutorial.demo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {



    @Qualifier("employeeRestClient")
    private final RestClient restClient;

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.info(">>> Trying  to retrieve all the employee in getAllEmplooyee");


        try {
            ApiResponse<List<EmployeeDTO>> response = restClient.get()
                    .uri("/employees")   // FIXED
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            (req,res)-> {
                        log.error("Client side error occured");
                        throw new RuntimeException("Client error occurred"); })
                    .onStatus(HttpStatusCode::is5xxServerError,
                            (req,res)-> {
                                log.error(">>>>>>Server side error occured");throw new RuntimeException("Server error occurred"); })
                    .body(new ParameterizedTypeReference<>() {});
      log.debug("successfully retrieved the employees");
      log.trace("Retrieved employees list in this getAllEmployees: {}, {}, {}", response.getData(),"hello", 5);

            return response.getData();

        } catch (Exception e) {
            log.error("Error while calling getAllEmployee", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
     log.trace("trying to get the emp with id :{}",employeeId);
        try {
            ApiResponse<EmployeeDTO> response = restClient.get()
                    .uri("/employees/{employeeId}", employeeId)   // FIXED
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            return response.getData();

        } catch (Exception e) {
            log.error("Error fetching employee {}", employeeId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace(">>> trying to get the emp with information :{}",employeeDTO);

        try {
            ApiResponse<EmployeeDTO> response = restClient.post()
                    .uri("/employees")   // FIXED
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req,res)-> {
                        log.debug("error http 4xx occured while creating the employee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create employee");
                    })
                    .body(new ParameterizedTypeReference<>() {});

            return response.getData();

        } catch (Exception e) {
            log.error(">>>>>>>>Error creating employee", e);
            throw new RuntimeException(e);
        }
    }
}
