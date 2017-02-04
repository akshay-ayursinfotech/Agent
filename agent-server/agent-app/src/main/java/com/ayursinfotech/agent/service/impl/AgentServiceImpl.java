package com.ayursinfotech.agent.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayursinfotech.agent.beans.dto.AgentDTO;
import com.ayursinfotech.agent.beans.dto.LoginDTO;
import com.ayursinfotech.agent.beans.entity.Agent;
import com.ayursinfotech.agent.dao.AgentDAO;
import com.ayursinfotech.agent.exception.DuplicateRecordFoundException;
import com.ayursinfotech.agent.exception.InvalidStatusException;
import com.ayursinfotech.agent.exception.NoRecordFoundException;
import com.ayursinfotech.agent.response.BaseResponse;
import com.ayursinfotech.agent.service.AgentService;
import com.ayursinfotech.agent.util.AgentConstants;
import com.ayursinfotech.agent.util.RandomGenerator;

@Service
public class AgentServiceImpl implements AgentService {

	private static final Logger LOGGER = Logger
			.getLogger(AgentServiceImpl.class);

	@Autowired
	private AgentDAO agentDAO;

	@Override
	public BaseResponse login(LoginDTO login) {
		LOGGER.info("start executing login");
		BaseResponse response = new BaseResponse();
		ModelMapper mapper = new ModelMapper();

		try {
			Agent agent = agentDAO.login(login);
			if (agent != null) {
				response.setBaseDTO(mapper.map(agent, AgentDTO.class));
				response.setStatus(AgentConstants.STATUS_SUCCESS);
			}
		} catch (InvalidStatusException e) {
			LOGGER.error(e);
			response.setStatus(AgentConstants.STATUS_SUCCESS);
			response.setErrorMessage(e.getMessage());
		} catch (NoRecordFoundException e) {
			LOGGER.error(e);
			response.setStatus(AgentConstants.STATUS_SUCCESS);
			response.setErrorMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(AgentConstants.STATUS_FAILURE);
			response.setErrorMessage(e.getMessage());
		}
		LOGGER.info("end executing login");
		return response;
	}

	@Override
	public BaseResponse ping() {
		LOGGER.info("start executing ping");
		BaseResponse response = new BaseResponse();
		try {
			if (agentDAO.ping()) {
				response.setStatus(AgentConstants.STATUS_SUCCESS);
			} else {
				response.setStatus(AgentConstants.STATUS_FAILURE);
			}
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(AgentConstants.STATUS_FAILURE);
			response.setErrorMessage(e.getMessage());
		}
		LOGGER.info("end executing ping");
		return response;
	}

	@Override
	public BaseResponse registerAgent(AgentDTO agentDTO) {
		LOGGER.info("start executing registerAgent");
		ModelMapper mp = new ModelMapper();
		BaseResponse response = new BaseResponse();

		Agent agent = null;
		try {
			if (agentDAO.isRegisteredAgent(mp.map(agentDTO, Agent.class))) {
				response.setMessage("Agent Already Exist");
				response.setStatus(AgentConstants.STATUS_SUCCESS);
				return response;
			}
			agentDTO.setStatus(AgentConstants.UNVERIFIED);
			agentDTO.setStatusChangeReason("New Customer Not Verified Yet");
			agentDTO.setVerificationCode(RandomGenerator.randomNumber(6));
			agentDTO.setPassword(agent.getPassword());

			agent = agentDAO.registerAgent(mp.map(agentDTO, Agent.class));
			response.setBaseDTO(mp.map(agent, AgentDTO.class));

			response.setMessage("Registered Agent Successfully");
			response.setStatus(AgentConstants.STATUS_SUCCESS);
			// TODO send confirmation mail to agent for successfull registration
			// TODO send sms to agent with verification code

		} catch (DuplicateRecordFoundException e) {
			LOGGER.error(e);
			response.setErrorMessage(e.getMessage());
			response.setMessage("unabe to register");
			response.setStatus(AgentConstants.STATUS_SUCCESS);
		} catch (Exception e) {
			LOGGER.error(e);
			response.setErrorMessage(e.getMessage());
			response.setStatus(AgentConstants.STATUS_FAILURE);
		}
		LOGGER.info("end executing registerAgent");
		return response;
	}
}
