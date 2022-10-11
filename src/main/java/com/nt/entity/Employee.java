package com.nt.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "emp")
public class Employee {
	@Id
	@SequenceGenerator(name = "gen1",allocationSize = 1,initialValue = 3000)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer empno;
	private String ename;
	private String job;
	@Column(name = "sal")
	private Float salary;
	private Integer deptno;

}
