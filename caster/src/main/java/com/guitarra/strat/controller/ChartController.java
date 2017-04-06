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

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChartController {

    private static final Logger logger = LoggerFactory.getLogger(ChartController.class);
    private static final String GUITARRA_RESPONSE = "{ \"response\": \"Guitarra strat Chart!\" }";
    private static final String GUITARRA_V1_RESPONSE =
        "{ \"response\": \"Guitarra strat Chart! version 1 !!\" }";

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String index(HttpServletRequest request) {
        logger.debug("Request from " + request.getRemoteAddr());

        return GUITARRA_RESPONSE;
    }

    @RequestMapping(value = "/v1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String indexV1(HttpServletRequest request) {
        logger.debug("Request from " + request.getRemoteAddr());

        return GUITARRA_V1_RESPONSE;
    }

}
