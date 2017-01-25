package com.ayursinfotech.agent.dao.impl;

import org.springframework.stereotype.Repository;

import com.ayursinfotech.agent.dao.AgentDAO;

@Repository
public class AgentDAOImpl implements AgentDAO {

	@Override
	public Boolean ping() throws Exception {
		Boolean result = true;
		return result;
	}

}
