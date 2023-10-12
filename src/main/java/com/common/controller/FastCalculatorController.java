package com.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.common.RequestController;
import com.common.dto.ResponseDTO;
import com.common.model.Business.MiniStamm;
import com.common.service.FastCalculatorService;

@RestController
@RequestMapping("/api")
public class FastCalculatorController {
	
	@Autowired
	private FastCalculatorService fastCalculatorService;
	
	@GetMapping("/calcSide")
	public ResponseEntity<ResponseDTO> calcAddonWithPaginated(@RequestParam("page") int page,
			@RequestParam("size") int size){
		
		List<MiniStamm> ministammList = fastCalculatorService.getCalcSideData();
		
		int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, ministammList.size());
        List<MiniStamm> pageContent = ministammList.subList(fromIndex, toIndex);
        
        PageImpl<MiniStamm> pageImpl = new PageImpl<>(pageContent, PageRequest.of(page, size), ministammList.size());

		return  RequestController.getBaseResponse(200, "List fetch successfully",
				HttpStatus.OK,pageImpl);
	}
	


}
