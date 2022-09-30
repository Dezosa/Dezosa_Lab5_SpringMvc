package com.studentDebate.springMvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "student")
	public class Student {

		// define fields

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;

		@Column(name = "name")
		private String name;

		@Column(name = "department")
		private String department;

		@Column(name = "country")
		private String country;

		// define constructors

		public Student() {

		}

		public Student(String name, String department, String country) {
			super();

			this.name = name;
			this.department = department;
			this.department = department;
		}

		// define getter/setter

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getdepartment() {
			return department;
		}

		public void setdepartment(String department) {
			this.department = department;
		}

		public String getcountry() {
			return country;
		}

		public void set(String country) {
			this.country = country;
		}

		// define tostring
		@Override
		public String toString() {
			return "Student [id=" + id + ", name=" + name + ", department=" + department + ", country=" + country + "]";
		}

	}


