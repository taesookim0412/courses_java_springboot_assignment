package com.taesookim.project.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taesookim.project.models.Course;
import com.taesookim.project.models.User;
import com.taesookim.project.services.CourseService;
import com.taesookim.project.services.UserService;
import com.taesookim.project.validator.UserValidator;

@Controller
public class CourseController {

	private final UserService userService;
	private final CourseService courseService;
	private final UserValidator userValidator;
	public CourseController(UserService userService,
			CourseService courseService,
			UserValidator userValidator) {
		this.userService = userService;
		this.courseService = courseService;
		this.userValidator = userValidator;
	}
	
	@RequestMapping({"", "/"})
	public String loginReg(@ModelAttribute("register") User register) {
		return "loginreg.jsp";
	}
	
	@RequestMapping("/courses")
	public String allCourses(Model model, HttpSession session) {
		if (session.getAttribute("logged") == null)
		{
			return "redirect:/";				
		}
		model.addAttribute("user", userService.findById((Long) session.getAttribute("logged")));
		model.addAttribute("courses", courseService.findAll());
		return "courses.jsp";
	}
	
	@RequestMapping("/courses/new")
	public String newCourseForm(@ModelAttribute("course") Course course, HttpSession session) {
		if (session.getAttribute("logged") == null)
		{
			return "redirect:/";				
		}
		return "newcourse.jsp";
	}
	
	@RequestMapping("/courses/{id}")
	public String getOneCourse(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("logged") == null)
		{
			return "redirect:/";				
		}
		model.addAttribute("course", courseService.findById(id));
		model.addAttribute("myId", (Long) session.getAttribute("logged"));
		return "onecourse.jsp";
	}
	
	@RequestMapping("/courses/{id}/asc")
	public String getOneCourseAsc(@PathVariable("id") Long id, Model model) {
		model.addAttribute("course", courseService.findById(id));
		return "onecourse.jsp";
	}
	
	@RequestMapping("/courses/{id}/desc")
	public String getOneCourseDesc(@PathVariable("id") Long id, Model model, @ModelAttribute("coursedelete") Course course) {
		model.addAttribute("course", courseService.findById(id));
		return "onecourse.jsp";
	}
	
	@RequestMapping("/courses/edit/{id}")
	public String editCourse(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("logged") == null)
		{
			return "redirect:/";				
		}
		model.addAttribute("course", courseService.findById(id));
		Course course = courseService.findById(id);
		return "editcourse.jsp";
	}
	
}
