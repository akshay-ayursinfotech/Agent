package com.ayursinfotech.agent.dao;

import java.math.BigInteger;

import com.ayursinfotech.agent.beans.dto.LoginDTO;
import com.ayursinfotech.agent.beans.entity.Agent;
import com.ayursinfotech.agent.exception.DuplicateRecordFoundException;
import com.ayursinfotech.agent.exception.InvalidStatusException;
import com.ayursinfotech.agent.exception.NoRecordFoundException;

public interface AgentDAO {

	Agent editAgentProfile(Agent agent);

	Agent getAgent(BigInteger agentId) throws NoRecordFoundException, Exception;

	boolean isRegisteredAgent(Agent agent)
			throws DuplicateRecordFoundException, Exception;

	Agent login(LoginDTO login) throws InvalidStatusException,
	NoRecordFoundException, Exception;

	Boolean ping() throws Exception;

	Agent registerAgent(Agent agent);

	boolean validateCredentials(String mobileNo, String password)
			throws NoRecordFoundException, InvalidStatusException, Exception;

	void changePassword(LoginDTO login);

	Agent getAgentByMobileNo(String mobileNo) throws NoRecordFoundException,
			Exception;

	void resetPassword(Agent agent);
}
