package com.example.drones;

import com.example.drones.controller.DispatchController;
import com.example.drones.model.Drone;
import com.example.drones.model.enums.Model;
import com.example.drones.model.enums.State;
import com.example.drones.repository.DroneRepo;
import com.example.drones.service.DispatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DispatchController.class)
public class DispatchControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DispatchService dispatchService;
    @MockBean
    DroneRepo droneRepo;
    Drone drone1 = new Drone(1l, "12345", Model.Heavyweight, 500,50, State.IDLE,null);
    Drone drone2 = new Drone(1l, "12346", Model.Heavyweight, 250,100, State.IDLE,null);
    Drone drone3 = new Drone(1l, "12347", Model.Heavyweight, 300,20, State.DELIVERED,null);

    @Test
    public void getAllDrones_success() throws Exception {
        List<Drone> records = new ArrayList<>(Arrays.asList(drone1, drone2, drone3));
        Mockito.when(droneRepo.findAll()).thenReturn(records);
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders
                        .get("/dispatchController/getAllDrones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
