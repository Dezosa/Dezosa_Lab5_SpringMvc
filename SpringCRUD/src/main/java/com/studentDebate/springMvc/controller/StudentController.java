package com.studentDebate.springMvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentDebate.springMvc.entity.Student;

import com.studentDebate.springMvc.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// add mapping for "/list"

	@RequestMapping("/list")
	public String listStudent(Model theModel) {

		// get Student from db
		List<Student> theStudent = studentService.findAll();

		// add to the spring model
		theModel.addAttribute("Student", theStudent);

		return "list-Student";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Student theStudent = new Student();

		theModel.addAttribute("Student", theStudent);

		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("StudentId") int theId, Model theModel) {
	
		Student theStudent = studentService.findById(theId);

		theModel.addAttribute("Student", theStudent);

		// send over to our form
		return "Student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setName(name);
			theStudent.setdepartment(department);
			theStudent.setcountry(country);
		} else
			theStudent = new Student(name, department, country);
		
		studentService.save(theStudent);

		// use a redirect to prevent duplicate submissions
		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {


		StudentService.deleteById(theId);

		
		return "redirect:/student/list";

	}

	@RequestMapping("/search")
	public String search(@RequestParam("name") String name, @RequestParam("country") String author, Model theModel) {

		
		if (name.trim().isEmpty() && author.trim().isEmpty()) {
			return "redirect:/student/list";
		} else {
			// else, search by first name and last name
			List<Student> theStudent = StudentService.searchBy(name, country);

			
			theModel.addAttribute("Student", theStudent);

			
			return "list-Student";
		}

	}
}
