package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentMapper;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;


    @Results(value = {
    		@Result(property="npm", column="npm"),
    		@Result(property="name", column="name"),
    		@Result(property="gpa", column="gpa"),
    		@Result(property="courses", column="npm",
    		javaType = List.class,
    		many=@Many(select="selectCourses"))
    })
    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<StudentModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public void addStudent (StudentModel student)
    {
        studentMapper.addStudent (student);
    }


    @Override
    public void deleteStudent (String npm)
    {
    	log.info("student " + npm + "deleted");
    	studentMapper.deleteStudent(npm);
    }


	@Override
	public void updateStudent(StudentModel student) {
		log.info("update " + student.getNpm() + "'s name to " + student.getName());
		studentMapper.updateStudent(student);
		
	}

}
