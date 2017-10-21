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
public class CourseModel {
	private String idCourse;
	private String name;
	private Integer credits;
	private List<StudentModel> students;
}
