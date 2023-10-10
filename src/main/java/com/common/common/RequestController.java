package com.common.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.common.dto.ResponseDTO;

public class RequestController {
	
	public static ResponseEntity<ResponseDTO> getBaseResponse(int code, String msg,  HttpStatus httpStatus, Object response) {
		
		ResponseDTO baseResponse = new ResponseDTO();
		
		baseResponse.setCode(code);
		baseResponse.setMsg(msg);
		baseResponse.setResponse(response);
		baseResponse.setHttpStatus(httpStatus);
		
		return new ResponseEntity<ResponseDTO>(baseResponse, httpStatus);
	}
}
