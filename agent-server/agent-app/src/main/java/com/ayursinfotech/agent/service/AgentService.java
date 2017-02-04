package com.ayursinfotech.agent.service;

import com.ayursinfotech.agent.beans.dto.LoginDTO;
import com.ayursinfotech.agent.response.BaseResponse;

public interface AgentService {

	BaseResponse login(LoginDTO login);

	BaseResponse ping();

}
