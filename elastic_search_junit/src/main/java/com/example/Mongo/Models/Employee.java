package com.example.Mongo.Models;
 
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.regex.Matcher;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
 



@Document(indexName = "employees")
public class Employee {
 
    
    
    @Id
    private Integer employeeId;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Keyword)
    private Designation designation;
 
    @Field(type = FieldType.Keyword)
    private  String email;
 
    @Field(type = FieldType.Keyword)
    private Department department;
 
    @Field(type = FieldType.Keyword)
    private String mobile;
 
    @Field(type = FieldType.Text)
    private String location;
 
    @Field(type = FieldType.Integer)
    private Integer managerId;
 
    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateOfJoining;


    public Employee(){

    }

    

    public Employee(Integer employeeId, String name, Designation designation, Department department, String email,
            String mobile, String location,  LocalDateTime dateOfJoining,Integer managerId) {
        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.email = email;
       
        this.mobile = mobile;
        this.location = location;
        this.managerId = managerId;
        this.dateOfJoining = dateOfJoining;
    }

    

    public Employee(Integer employeeId, String name, Designation designation,Department department, String email, 
            String mobile, String location, Integer managerId, LocalDateTime dateOfJoining) {
        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
        this.email = email;
        this.department = department;
        this.mobile = mobile;
        this.location = location;
        this.managerId = managerId;
        this.dateOfJoining = dateOfJoining;
    }


    public Employee(Integer employeeId, String name, String mobile, String location, Designation designation, Department department, LocalDateTime dateOfJoining, Integer managerId,String email) {
        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
        this.email = email;
        this.department = department;
        this.mobile = mobile;
        this.location = location;
        this.managerId = managerId;
        this.dateOfJoining = dateOfJoining;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        
        this.managerId = managerId;
    }

    

    
 
    
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //@NotNull(message = "Designation can only be Account manager or associate")
    public  Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
       
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
         this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public  LocalDateTime getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDateTime dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }



   
     
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
   
    @JsonIgnore
  public boolean validDateOfJoining(){
      if(dateOfJoining!=null){
        try {
            // Create a DateTimeFormatter with the expected pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

            // Convert the LocalDateTime to a string using the formatter
            String formattedDate = dateOfJoining.format(formatter);

            // Try to parse it back to LocalDateTime to check if it's valid
            LocalDateTime.parse(formattedDate, formatter);

            // If parsing and formatting is successful, return true
            return true;
        } catch (Exception e) {
            // If any exception occurs, return false
            return false;
        }



      }return false;
  }

        // Define a regular expression pattern for a valid email address
      
        @JsonIgnore
        public  boolean isValidEmail() {
             String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
            if(email!=null){
            // Compile the regex pattern
            Pattern pattern = Pattern.compile(emailRegex);
            // Match the input email against the pattern
            Matcher matcher = pattern.matcher(email);
            // Return true if the email matches the pattern, false otherwise
            return matcher.matches();} return false;
        }
    

    @JsonIgnore
    public boolean validName(){
         String pt = "^[a-zA-Z\\s'-]+$";

    
        if(name!=null){
        // Compile the regex pattern
        Pattern pattern = Pattern.compile(pt);
        // Match the input name against the pattern
        Matcher matcher = pattern.matcher(name);
        // Return true if the name matches the pattern, false otherwise
        return matcher.matches();}

        return false;
    }

    @JsonIgnore
    public boolean chkDepartment(){
        if(department!=null){
            return true;
        }return false;
    }
   
    @JsonIgnore
    public boolean chkLocation(){

        if(location==null || location==""){
             return false;
        }
    return true;}
    @JsonIgnore
    public boolean  chkDesignation(){
       // System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+designation);
        if(designation !=null && (designation==Designation.Account_manager || designation == Designation.associate)){
           // System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx inside if");
            return true;
        }
        return false;
    }
 
    //@AssertTrue(message = "Manager ID must be zero if designation is Manager or a valid ID if designation is not Manager")
    @JsonIgnore
    public boolean isValidManagerId() {
        
        return (managerId!=null) && ((designation == Designation.Account_manager && managerId == 0) ||
               (designation != Designation.Account_manager && managerId != null && managerId > 0));
    }
    @JsonIgnore
    public boolean isValidMobileNum(){

        if(mobile!=null){
        String mobRegexPattern ="\\+[0-9]{12}$" ;
         // Compile the regex pattern
         Pattern pattern = Pattern.compile(mobRegexPattern);
         // Match the input mobile number against the pattern
         Matcher matcher = pattern.matcher(mobile);
         // Return true if the number matches the pattern, false otherwise
         return matcher.matches();}return false;
        //return  true;
    }
    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(employeeId, employee.employeeId);
}
 
@Override
public int hashCode() {
    return Objects.hash(employeeId);
}


    
   
}
// package com.example.Mongo.Models;

// import java.time.LocalDateTime;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import javax.validation.constraints.NotBlank;
// import lombok.Data;

// @Data
// @Document(collection="employee")
// public class Employee {
//     @Id
//     private String id;
//     @NotBlank(message = "name is required")
//     private String name;
//     private String  designation;
//     private String email;
//     private String department;
//     private String mobile;
//     private String location;
//     private String managerId;
//     private String dataOfJoining;
//     private LocalDateTime dateOfJoining;


    
// }
