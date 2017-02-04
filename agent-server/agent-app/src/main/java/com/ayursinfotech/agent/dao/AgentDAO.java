package com.ayursinfotech.agent.dao;

import com.ayursinfotech.agent.beans.dto.LoginDTO;
import com.ayursinfotech.agent.beans.entity.Agent;
import com.ayursinfotech.agent.exception.InvalidStatusException;
import com.ayursinfotech.agent.exception.NoRecordFoundException;

public interface AgentDAO {

	Agent login(LoginDTO login) throws InvalidStatusException,
			NoRecordFoundException, Exception;

	Boolean ping() throws Exception;
}
