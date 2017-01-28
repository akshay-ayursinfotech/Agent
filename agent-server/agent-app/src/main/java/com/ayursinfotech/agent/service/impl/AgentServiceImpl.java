package com.ayursinfotech.agent.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayursinfotech.agent.dao.AgentDAO;
import com.ayursinfotech.agent.response.BaseResponse;
import com.ayursinfotech.agent.service.AgentService;
import com.ayursinfotech.agent.util.AgentConstants;

@Service
public class AgentServiceImpl implements AgentService {

	private static final Logger LOGGER = Logger
			.getLogger(AgentServiceImpl.class);

	@Autowired
	private AgentDAO agentDAO;

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

}
