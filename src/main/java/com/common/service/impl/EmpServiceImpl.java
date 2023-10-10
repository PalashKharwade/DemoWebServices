package com.common.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.common.model.Business.TestClass;
import com.common.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService{

	@Override
	public List<TestClass> getHardcodedEmpData() {
		
		        List<TestClass> empList = new ArrayList<>();

		        // Add some hardcoded data
		        String dat = "2023-10-22";
		        LocalDate localDate = LocalDate.parse(dat, DateTimeFormatter.ISO_LOCAL_DATE);
		        empList.add(new TestClass("John", localDate));
		        empList.add(new TestClass("Alice", localDate));
		        empList.add(new TestClass("Bob", localDate));

		        return empList;
		    }
	

}
