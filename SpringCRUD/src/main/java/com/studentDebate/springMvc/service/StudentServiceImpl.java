package com.studentDebate.springMvc.service;

import java.util.List;

import javax.swing.plaf.basic.BasicIconFactory;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentServiceImpl implements StudentService  {
	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	@Transactional
	public List<Student> findAll() {
		
		Transaction tx = session.beginTransaction();

		// find all the records from the database table
		List<Student> student = session.createQuery("from Student").list();

		tx.commit();

		return student;
	}

	@Transactional
	public Student findById(int id) {

		Student student = new Student();

		
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		student = session.get(Student.class, id);

		tx.commit();

		return student;
	}

	@Transactional
	public void save(Student theStudent) {

		
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(theStudent);

		tx.commit();

	}

	@Transactional
	public void deleteById(int id) {

	
		Transaction tx = session.beginTransaction();

		// get transaction
		Student student = session.get(Student.class, id);

		// delete record
		session.delete(student);

		tx.commit();

	}

	@Transactional
	public List<Student> searchBy(String Name, String department) {

		
		Transaction tx = session.beginTransaction();
		String query = "";
		if (Name.length() != 0 && Author.length() != 0)
			query = "from Student where name like '%" + Name + "%' or department like '%" + department + "%'";
		else if (Name.length() != 0)
			query = "from Student where name like '%" + Name + "%'";
		else if (department.length() != 0)
			query = "from Student where department like '%" + department + "%'";
		else
			System.out.println("Cannot search without input data");

		List<Student> student = session.createQuery(query).list();

		tx.commit();

		return student;
	}

	// print the loop
	@Transactional
	public void print(List<Student> student) {

		for (Student s : student) {
			System.out.println(s);
		}
	}


}
