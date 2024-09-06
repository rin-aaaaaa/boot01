package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot01ApplicationTests {
	@Autowired // 필드 주입
	private EmpMapper empMapper;
	
	//@Test
	void contextLoads() {
		// 전제조회 : 입력X -> 출력 1건이상
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty());
	}
	
	// @Test
	void emlInfo() {
		// 단건조회 : 입력, 사원번호(100) -> 출력, 사원이름(King)
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");
	}
	
	// @Test
	void empInsert() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("HA");
		empVO.setEmail("kdHong@gmail.com");
		empVO.setJobId("SA_REP");
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	// @Test
	void empInsertFull() throws ParseException {
		// 사원등록 : 입력(사원이름, 이메일, 입사일자, 업무, 급여)
		//		   -> 출력 = 정수1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("HA");
		empVO.setEmail("kdHong11@gmail.com");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = sdf.parse("2024-08-15");
		empVO.setHireDate(today);
		
		empVO.setJobId("SA_REP");
		empVO.setSalary(15000.0);
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	// @Test
	void empDelete() {
		int result = empMapper.deleteEmpInfo(207);
		assertEquals(result, 1);
		
		result = empMapper.deleteEmpInfo(208);
		assertEquals(result, 1);
	}
	
	// @Test
	void empUpdate() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(207);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		findVO.setEmail("kdHong12@gmail.com");
		
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		
		assertEquals(result, 1);
	}
	
	@Test
	void empUpdateDynamic() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(208);
		empVO.setLastName("kim");
		empVO.setJobId("MK_REP");
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		assertEquals(result, 1);
	}
}
