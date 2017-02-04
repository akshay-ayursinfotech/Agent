package com.ayursinfotech.agent.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ayursinfotech.agent.beans.dto.AgentDTO;
import com.ayursinfotech.agent.beans.dto.LoginDTO;
import com.ayursinfotech.agent.response.BaseResponse;
import com.ayursinfotech.agent.service.AgentService;

@Controller
public class AgentController {

	private static final Logger LOGGER = Logger
			.getLogger(AgentController.class);

	@Autowired
	private AgentService agentService;

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BaseResponse editAgentProfile(@RequestBody AgentDTO agentDTO) {
		LOGGER.info("inside editAgentProfile");
		return agentService.editAgentProfile(agentDTO);
	}

	// method for login validation
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BaseResponse login(@RequestBody LoginDTO login) {
		LOGGER.info("inside doLogin");
		return agentService.login(login);
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BaseResponse ping() {
		LOGGER.info("start executing ping");
		return agentService.ping();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BaseResponse registerAgent(@RequestBody AgentDTO agentDTO) {
		LOGGER.info("inside registerAgent");
		return agentService.registerAgent(agentDTO);
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BaseResponse changePassword(@RequestBody LoginDTO login) {
		LOGGER.info("inside ChangePassword");
		return agentService.changePassword(login);
	}
}
