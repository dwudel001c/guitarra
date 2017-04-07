/* 
 * ===========================================================================
 * This file is the intellectual property of Comcast Corp.  It
 * may not be copied in whole or in part without the express written
 * permission of Comcast or its designees.
 * ===========================================================================
 * Copyright (c) 2017 Comcast Corp. All rights reserved.
 * ===========================================================================
 */

package com.guitarra.strat.controller;

import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guitarra.strat.app.ChartSpringApp;

import org.easymock.EasyMock;

import org.json.JSONObject;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@PropertySource("classpath:application.yml")
@RunWith(SpringRunner.class)
@SpringBootConfiguration
@SpringBootTest(classes = ChartSpringApp.class)
public class ChartApiTestDocumentation {

    // private static final int MAX_ATTEMPTS = 5;

    private static String jwtClient;
    private static String jwtStb;
    private static String jwtFinal;
    private static String pairingCode;

    private static String FQDN = "localhost:8080/caster";
    private static String HTTP_PROTOCOL = "http";
    private static String PATH_VAR_DELIMITER = "/";



    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    public MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;


    @Before
    public void setUp() {

        mockMvc =
            MockMvcBuilders.webAppContextSetup(this.context).apply(
                               documentationConfiguration(this.restDocumentation).uris().withHost(FQDN).withScheme(
                                   HTTP_PROTOCOL)).build();

    }

    @Test
    public void test01Home() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(document(
                                                                                                               "home"));
    }



    @Test
    public void test10UC1GetPairingCodePostMethodOK() throws Exception {

        String jsonRequestBody =
            createJsonClientDeviceRequest("Yesterday");

        MvcResult uc1 =
            this.mockMvc.perform(post( "/v1/chart" ).accept(MediaType.APPLICATION_JSON).contentType(
                                     MediaType.APPLICATION_JSON).content(jsonRequestBody)).andExpect(status().isOk()).andDo(
                            document("price-200")).andReturn();


    }

//    @Test
//    public void test13UC1GetPairingCodeGuestAccessType() throws Exception {
//
//        String jsonRequestBody =
//            createJsonClientDeviceRequest(clientDeviceId, clientDeviceName, clientDeviceType, validGuestAccessType);
//
//        this.mockMvc.perform(post(PairingConstants.GET_PAIRING_CODE).accept(MediaType.APPLICATION_JSON).contentType(
//                                 MediaType.APPLICATION_JSON).content(jsonRequestBody)).andExpect(status().is(
//                                                                                                     HttpStatus.OK
//                                                                                                     .value())).andDo(
//                        document("uc1-get-pairing-code-guest-200"));
//    }

    // -------------------------------------
    // UC1 Exceptions
    // ------------------------------------

  

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Helper Methods
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Create a json as the client would send it.
     *
     * @return
     */
    private String createJsonClientDeviceRequest(String song) {

        ObjectNode requestJson = objectMapper.createObjectNode();
       

        requestJson.put("song", song);

        return requestJson.toString();
    }


}
