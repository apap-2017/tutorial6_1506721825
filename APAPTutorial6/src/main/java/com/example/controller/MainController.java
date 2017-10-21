package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class MainController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
    	StudentModel std = new StudentModel();
    	std.setTitle("Home");
    	model.addAttribute("student", std);
        
        return "index2";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    	StudentModel std = new StudentModel();
    	std.setTitle("Tambah Mahasiswa");
    	model.addAttribute("student", std);
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null, null);
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);
        
        if (student != null) {
            model.addAttribute ("student", student);
            student.setTitle("view");
            return "view";
            
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }

    
    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
    	if(npm.contains("CS")) {
    		//CourseModel course = studentDAO.select
    	}
        StudentModel student = studentDAO.selectStudent (npm);
       
        if (student != null) {
            model.addAttribute ("student", student);
            student.setTitle("view");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        StudentModel student = new StudentModel();
        student.setTitle("Daftar Mahasiswa");
        model.addAttribute("student", student);
        return "viewall";
    }

    
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm) {
    	StudentModel std = studentDAO.selectStudent(npm);
    	if (std == null) {
    		return "not-found";
    	}
    	model.addAttribute("student", std);
    	std.setTitle("Ubah Mahasiswa");
        
    	return "form-update";
    }
    
    
    @PostMapping(value = "student/update/submit")
    public String updateSubmit(@ModelAttribute StudentModel student) {
    	
    	studentDAO.updateStudent(student);
    	
    	return "success-update";
    }

    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent (npm);
    	
        if (student == null) {
    		return "not-found";
    	}
        
        studentDAO.deleteStudent (npm);

        return "delete";
    }

}
