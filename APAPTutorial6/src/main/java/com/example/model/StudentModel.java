package com.example.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StudentModel
{
	

	private String npm;
    private String name;
    private double gpa;
    private List<CourseModel> courses;
    private String title;
    

}
