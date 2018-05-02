package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Role;
import com.se34.coursemap.entity.Student;
import com.se34.coursemap.entity.Subject;
import com.se34.coursemap.repository.StudentRepository;
import com.se34.coursemap.security.model.UserRegisterDTO;
import com.se34.coursemap.service.StudentService;
import com.se34.coursemap.utill.EmailExistsException;
import com.se34.coursemap.utill.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void editStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getStudent(int id) {
        return studentRepository.getOne(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByLogin(String login) {
        return studentRepository.findByLogin(login);
    }

    @Override
    public Student registerNewUserAccount(UserRegisterDTO accountDto) throws EmailExistsException, UsernameExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: " +  accountDto.getEmail());
        }
        if (usernameExist(accountDto.getUsername())) {
            throw new UsernameExistsException(
                    "There is an account with that username: " +  accountDto.getUsername());
        }

        Student newUser = new Student();
        newUser.setPassword(accountDto.getPassword());
        newUser.setLogin(accountDto.getUsername());
        newUser.setFirstName(accountDto.getFirstName());
        newUser.setLastName(accountDto.getLastName());
        newUser.setEmail(accountDto.getEmail());
        newUser.setEnabled(true);
        newUser.setLastPasswordResetDate(new Date());
        Role role = new Role();
        role.setRoleId(1);
        newUser.setRole(role);

        addStudent(newUser);
        return newUser;
    }



    private boolean emailExist(String email) {
        Student user = studentRepository.findByEmail(email);
        return user != null;
    }

    private boolean usernameExist(String username) {
        Student user = studentRepository.findByLogin(username);
        return user != null;
    }
}
