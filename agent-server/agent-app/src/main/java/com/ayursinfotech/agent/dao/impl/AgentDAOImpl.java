package com.ayursinfotech.agent.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ayursinfotech.agent.dao.AgentDAO;

@Repository
public class AgentDAOImpl implements AgentDAO {

	private static final Logger LOGGER = Logger.getLogger(AgentDAOImpl.class);

	@Override
	public Boolean ping() throws Exception {
		LOGGER.info("start executing ping");
		Boolean result = true;
		LOGGER.info("end executing ping");
		return result;
	}

}
