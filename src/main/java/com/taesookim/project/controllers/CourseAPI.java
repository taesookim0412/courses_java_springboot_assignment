package com.taesookim.project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taesookim.project.models.Course;
import com.taesookim.project.models.User;
import com.taesookim.project.services.CourseService;
import com.taesookim.project.services.UserService;
import com.taesookim.project.validator.UserValidator;

@Controller
public class CourseAPI {
	private final UserService userService;
	private final CourseService courseService;
	private final UserValidator userValidator;
	public CourseAPI(UserService userService,
			CourseService courseService,
			UserValidator userValidator) {
		this.userService = userService;
		this.courseService = courseService;
		this.userValidator = userValidator;
	}
	
	
		@RequestMapping(value="/user/new", method=RequestMethod.POST)
		public String newUser(@Valid @ModelAttribute("register") User register, BindingResult result, HttpSession session
				) {
			userValidator.validate(register, result);
			if (result.hasErrors()) {
				return "loginreg.jsp";
			}
			else {
				userService.save(register);
				session.setAttribute("logged", register.getId());
				return "redirect:/courses";
			}
		}
		
		@RequestMapping(value="/user", method=RequestMethod.POST)
		public String loginUser( @RequestParam("loginuser") String email, @RequestParam("loginpassword") String password,
				HttpSession session, RedirectAttributes redirectAttributes) {
			if(userService.authenticateUser(email, password)) {
				session.setAttribute("logged", userService.findEmail(email).getId());
				return "redirect:/courses";
			}
			else {
				redirectAttributes.addFlashAttribute("loginerror", "Incorrect login.");
				return "redirect:/";
			}
			
		}
		
		@RequestMapping(value="/coursesapi", method=RequestMethod.POST)
		public String createCourse(@Valid @ModelAttribute("course") Course course,
				BindingResult result) {
			if (result.hasErrors()) {
				return "newcourse.jsp";
			}	
			else {
				courseService.save(course);
				return "redirect:/courses";

			}
		}
		
		@RequestMapping(value="/coursesapi/users/{id}", method=RequestMethod.POST)
		public String joinCourse(HttpSession session, @PathVariable("id") Long cId) {
			Long uId = (long) session.getAttribute("logged");
			User user = userService.findById(uId);
			Course course = courseService.findById(cId);
			List<User> courseUsers = course.getUsers();
			courseUsers.add(user);
			course.setUsers(courseUsers);
			courseService.save(course);
			return "redirect:/courses";
			
		}
		
		@RequestMapping(value="/coursesapi", method=RequestMethod.PUT)
		public String editCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {
			if (result.hasErrors()) {
				model.addAttribute("course", courseService.findById(course.getId()));
				return "onecourse.jsp";
			}
			else {
				courseService.saveEdit(course);
				return "redirect:/courses/" + course.getId();
			}
			
		}
		
		@RequestMapping(value="/coursesapi/{id}", method=RequestMethod.DELETE)
		public String deleteCourse(@PathVariable("id") Long id) {
			courseService.deleteById(id);
			return "redirect:/courses";
		}
		
		@RequestMapping(value="/coursesapi/users/{cId}", method=RequestMethod.DELETE)
		public String removeSignUp(@PathVariable("cId") Long cId, HttpSession session) {
			Course course = courseService.findById(cId);
			User user = userService.findById((Long) session.getAttribute("logged"));
			List<User> cUsers = course.getUsers();
			cUsers.remove(user);
			course.setUsers(cUsers);
			courseService.save(course);
			return "redirect:/courses/" +  cId;

			
		}
}
		

