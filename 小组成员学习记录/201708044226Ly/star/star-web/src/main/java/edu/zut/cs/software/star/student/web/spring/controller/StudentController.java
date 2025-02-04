package edu.zut.cs.software.star.student.web.spring.controller;

import edu.zut.cs.software.star.admin.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.zut.cs.software.base.web.spring.controller.GenericController;
import edu.zut.cs.software.star.student.domain.Student;
import edu.zut.cs.software.star.student.service.StudentManager;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 
 * 修正中  2019-5-11 16:02:48
 * @author 研哥哥
 *
 */
@Controller
@RequestMapping("/stu")
public class StudentController extends GenericController<Student, Long, StudentManager> {

	StudentManager studentManager;

	/**
	 * 自动 注入/植入/绑定
	 * @param studentManager
	 */
	@Autowired
	public void setStudentManager(StudentManager studentManager) {
		this.studentManager = studentManager;
		this.manager = this.studentManager;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/main")
	public String index() {
		String result = "index";
//		通过视图解析器得到真正的视图 /html/index.html
		return result;
	}

	@ResponseBody//标识转换成JSON处理
	@GetMapping(value = "all",produces = "application/json;charset=utf-8")
	public List<Student> getAllStudent(){
		return studentManager.getAll();
	}
	
}
