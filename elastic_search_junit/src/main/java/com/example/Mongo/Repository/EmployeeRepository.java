package com.example.Mongo.Repository;

import com.example.Mongo.Models.Department;
import com.example.Mongo.Models.Designation;
import com.example.Mongo.Models.Employee;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, Integer> {

    // Find employees by managerId
    List<Employee> findByManagerId(Integer managerId);

    // Custom query to find employees by managerId and experience (date of joining)
    @Query("{\"bool\": {\"must\": [{\"term\": {\"managerId\": \"?0\"}}, {\"range\": {\"dateOfJoining\": {\"lte\": \"?1\"}}}]}}")
    List<Employee> findByManagerIdAndExperienceGreaterThanEqual(Integer managerId, LocalDateTime experience);

    // Custom query to find employees by years of experience (without managerId)
    @Query("{\"range\": {\"dateOfJoining\": {\"lte\": \"?0\"}}}")
    List<Employee> findByExperienceGreaterThanEqual(LocalDateTime dateOfJoiningThreshold);

    boolean existsByDepartmentAndDesignation(Department department, Designation designation);
    void deleteById(Integer employeeId);

    

    Optional<Employee> findByEmployeeId(Integer employeeId);
}

// package com.example.Mongo.Repository;

// import com.example.Mongo.Models.Department;
// import com.example.Mongo.Models.Employee;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    

//       // Find employees by managerId
//       List<Employee> findByManagerId(Integer managerId);

//       // Custom query to find employees by managerId and years of experience
//     //  @Query("{'managerId': ?0, 'dateOfJoining': {$lte: ?1}}")
//       @Query("SELECT e FROM Employee e WHERE e.managerId = :managerId AND e.dateOfJoining <= :dateOfJoiningThreshold")
//       List<Employee> findByManagerIdAndExperienceGreaterThanEqual(Integer managerId, LocalDateTime dateOfJoiningThreshold);
  
//       // Custom query to find employees by years of experience (without managerId)
//       @Query("SELECT e FROM Employee e WHERE e.dateOfJoining <= :dateOfJoiningThreshold")
//       //List<Employee> findByExperience(@Param("experienceCutoffDate") LocalDateTime experienceCutoffDate);
//       List<Employee> findByExperienceGreaterThanEqual(LocalDateTime dateOfJoiningThreshold);
      
//       boolean existsByDepartmentAndDesignation(Department department, Designation designation);
  
//       Optional<Employee> findByEmployeeId(Integer newManagerId);
  
// }

   

