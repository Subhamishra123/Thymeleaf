package com.nt.repositoy;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nt.entity.Employee;

public interface IEmployee extends PagingAndSortingRepository<Employee, Integer> {

}
