package com.ayursinfotech.agent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayursinfotech.agent.dao.AgentDAO;
import com.ayursinfotech.agent.response.BaseResponse;
import com.ayursinfotech.agent.service.AgentService;
import com.ayursinfotech.agent.util.AgentConstants;

@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentDAO virtualccDAO;

	@Override
	public BaseResponse ping() {
		BaseResponse response = new BaseResponse();
		try {
			if (virtualccDAO.ping()) {
				response.setStatus(AgentConstants.STATUS_SUCCESS);
			} else {
				response.setStatus(AgentConstants.STATUS_FAILURE);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
