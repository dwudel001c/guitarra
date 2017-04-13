package com.guitarra.strat.controller;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ChartServiceController {

	public static final java.lang.String MUSIC_ONE = "/v1/chart";

	private static final Logger LOGGER = LoggerFactory.getLogger(ChartServiceController.class);

	@RequestMapping(
			value = MUSIC_ONE, method = RequestMethod.POST, consumes = {
					MediaType.APPLICATION_JSON_VALUE
			}
			)
	public Map<String, Object> requestPairingCode(@RequestBody
			@Valid
			ChartRequest request) throws ChartException {



		return createResponse( request);
	}

	private Map<String, Object> createResponse(ChartRequest request) throws ChartException {
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		responseMap.put("info", request.getSong());

		return responseMap;

	}

}
