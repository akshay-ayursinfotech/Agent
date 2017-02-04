package com.ayursinfotech.agent.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayursinfotech.agent.beans.dto.AgentDTO;
import com.ayursinfotech.agent.beans.dto.LoginDTO;
import com.ayursinfotech.agent.beans.entity.Agent;
import com.ayursinfotech.agent.dao.AgentDAO;
import com.ayursinfotech.agent.exception.DuplicateRecordFoundException;
import com.ayursinfotech.agent.exception.IllegalParameterException;
import com.ayursinfotech.agent.exception.InvalidStatusException;
import com.ayursinfotech.agent.exception.NoRecordFoundException;
import com.ayursinfotech.agent.response.BaseResponse;
import com.ayursinfotech.agent.service.AgentService;
import com.ayursinfotech.agent.util.AgentConstants;
import com.ayursinfotech.agent.util.CustomEncrypt;
import com.ayursinfotech.agent.util.RandomGenerator;

@Service
public class AgentServiceImpl implements AgentService {

	private static final Logger LOGGER = Logger
			.getLogger(AgentServiceImpl.class);

	@Autowired
	private AgentDAO agentDAO;

	@Override
	public BaseResponse editAgentProfile(AgentDTO agentDTO) {
		LOGGER.info("start executing editCustomerProfile");
		ModelMapper mapper = new ModelMapper();
		BaseResponse response = new BaseResponse();
		try {

			if (agentDTO.getMobileNo() != null || agentDTO.getEmail() != null) {
				throw new IllegalParameterException(
						"Non Updatable field email or mobile number");
			}
			Agent agentDetails = agentDAO.getAgent(agentDTO.getId());
			if (agentDetails != null) {
				// add fields to be updated
				agentDetails = getMergedAgent(agentDetails, agentDTO);

				Agent updatedAgent = agentDAO.editAgentProfile(agentDetails);
				response.setBaseDTO(mapper.map(updatedAgent, AgentDTO.class));
				response.setMessage("Agent Profile editted Successfully");
				response.setStatus(AgentConstants.STATUS_SUCCESS);
			}
		} catch (IllegalParameterException e) {
			LOGGER.error(e);
			response.setErrorMessage(e.getMessage());
			response.setStatus(AgentConstants.STATUS_SUCCESS);
		} catch (NoRecordFoundException e) {
			LOGGER.error(e);
			response.setErrorMessage(e.getMessage());
			response.setStatus(AgentConstants.STATUS_SUCCESS);
		} catch (Exception e) {
			LOGGER.error(e);
			response.setErrorMessage(e.getMessage());
			response.setStatus(AgentConstants.STATUS_FAILURE);
		}
		LOGGER.info("end executing editAgentProfile");
		return response;
	}

	private Agent getMergedAgent(Agent agentDetails, AgentDTO agentDTO) {
		if (agentDTO.getGender() != null) {
			agentDetails.setGender(agentDTO.getGender());
		}
		if (agentDTO.getTitle() != null) {
			agentDetails.setTitle(agentDTO.getTitle());
		}
		agentDetails.setUpdatedDate(new Date());

		if (agentDTO.getName() != null) {
			agentDetails.setName(agentDTO.getName());
		}
		if (agentDTO.getUserName() != null) {
			agentDetails.setUserName(agentDTO.getUserName());
		}
		return agentDetails;
	}

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

	@Override
	public BaseResponse changePassword(LoginDTO login) {
		LOGGER.info("start executing changePassword");
		ModelMapper mapper = new ModelMapper();
		BaseResponse response = new BaseResponse();
		try {
			if (agentDAO.validateCredentials(login.getMobileNo(),
					login.getPassword())) {
				agentDAO.changePassword(login);
				response.setMessage("Password Updated Successfuly");
				response.setStatus(AgentConstants.STATUS_SUCCESS);
			}

		} catch (InvalidStatusException e) {
			response.setStatus(AgentConstants.STATUS_SUCCESS);
			response.setMessage(e.getMessage());
			LOGGER.error(e);
		} catch (NoRecordFoundException e) {
			response.setStatus(AgentConstants.STATUS_SUCCESS);
			response.setMessage(e.getMessage());
			LOGGER.error(e);
		} catch (Exception e) {
			response.setStatus(AgentConstants.STATUS_FAILURE);
			response.setErrorMessage(e.getMessage());
			LOGGER.error(e);
		}
		LOGGER.info("end executing changePassword");
		return response;
	}

	@Override
	public BaseResponse forgotPassword(LoginDTO login) {
		LOGGER.info("start executing forgotPassword");
		ModelMapper mp = new ModelMapper();
		BaseResponse response = new BaseResponse();
		try {
			Agent agent = agentDAO.getAgentByMobileNo(login.getMobileNo());
			if (agent != null) {
				String encrMobile = CustomEncrypt.encrypt(login.getMobileNo());
				String encrTime = CustomEncrypt.encrypt(Long.toString(System
						.currentTimeMillis()));
				// TODO send reset password link to mail/mobie
				response.setStatus(AgentConstants.STATUS_SUCCESS);
				response.setMessage("password reset link sent successfully");
			}

		} catch (NoRecordFoundException e) {
			response.setMessage(e.getMessage());
			response.setStatus(AgentConstants.STATUS_SUCCESS);
			LOGGER.error(e);
		} catch (Exception e) {
			response.setStatus(AgentConstants.STATUS_FAILURE);
			response.setMessage(e.getMessage());
			LOGGER.error(e);
		}
		LOGGER.info("end executing forgotPassword");
		return response;
	}
}
