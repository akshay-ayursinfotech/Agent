package com.ayursinfotech.agent.dao;

import com.ayursinfotech.agent.beans.dto.LoginDTO;
import com.ayursinfotech.agent.beans.entity.Agent;
import com.ayursinfotech.agent.exception.DuplicateRecordFoundException;
import com.ayursinfotech.agent.exception.InvalidStatusException;
import com.ayursinfotech.agent.exception.NoRecordFoundException;

public interface AgentDAO {

	boolean isRegisteredAgent(Agent agent)
			throws DuplicateRecordFoundException, Exception;

	Agent login(LoginDTO login) throws InvalidStatusException,
	NoRecordFoundException, Exception;

	Boolean ping() throws Exception;

	Agent registerAgent(Agent agent);
}
