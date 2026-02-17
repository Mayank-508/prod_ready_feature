package com.Auditing_Tutorial.demo.client.impl;

import com.Auditing_Tutorial.demo.advice.ApiResponse;
import com.Auditing_Tutorial.demo.client.EmployeeClient;
import com.Auditing_Tutorial.demo.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient
{

    @Qualifier("employeeRestClient")
    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {

      try{
         ApiResponse< List<EmployeeDTO>> employeeDTOList= restClient.get()
                  .uri("employees")
                  .retrieve()
                 .onStatus(HttpStatusCode::is4xxClientError,(req,res)-> {
                     throw new RuntimeException("Client error occured");
                 })
                 .onStatus(HttpStatusCode::is5xxServerError,(req,res)-> {
                     throw new RuntimeException("Server Error Occured");
                 })
                  .body(new ParameterizedTypeReference<>() {
                  });
          return employeeDTOList.getData();
      }catch (Exception e){
          throw new RuntimeException(e);

      }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try{
            ApiResponse<EmployeeDTO> employeeDTOApiResponse= restClient.get()
                    .uri("employees/{employeeId}/",employeeId)
                    .retrieve()
                    .body(new  ParameterizedTypeReference<>(){});

            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            ApiResponse<EmployeeDTO> employeeDTOApiResponse=restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
