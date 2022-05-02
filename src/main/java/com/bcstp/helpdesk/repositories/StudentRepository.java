package com.bcstp.helpdesk.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bcstp.helpdesk.models.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	//List<Student> findAllByPrice(String name, Pageable pageable);

	//Page<Student> findAll(Pageable pageable);

	/*@Query("SELECT s FROM Student s WHERE s.name "
			+ "CONCAT " + ""
					    + "(s.name) LIKE %?1%")
	*/
	@Query("SELECT s FROM Student s WHERE s.name LIKE %?1%"
            + " OR s.department LIKE %?1%"
            + " OR CONCAT(s.department, '') LIKE %?1%")
    public List<Student> search(String keyword);

}
