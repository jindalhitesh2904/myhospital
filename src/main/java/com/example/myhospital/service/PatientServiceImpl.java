package com.example.myhospital.service;

import com.example.myhospital.dto.PatientRegistrationDto;
import com.example.myhospital.model.Patient;
import com.example.myhospital.model.Role;
import com.example.myhospital.model.User;
import com.example.myhospital.repository.PatientRepository;
import com.example.myhospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Patient save(PatientRegistrationDto patientRegistrationDto) {
//        Role role=roleService.getRoleByName("DOCTOR");
//        Patient patient=new Patient(patientRegistrationDto.getFirst_name(), patientRegistrationDto.getLast_name(),patientRegistrationDto.getEmail(), passwordEncoder.encode(patientRegistrationDto.getPassword()), patientRegistrationDto.getMobile_no(), patientRegistrationDto.getGender(), patientRegistrationDto.getAge(),Arrays.asList(role),patientRegistrationDto.getSalary(),patientRegistrationDto.getDepartment(),patientRegistrationDto.getDesignation());
//        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getAuthorities());
//        return patientRepository.save(patient);
//    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(long id){
        return patientRepository.getPatientById(id);
    }

    @Override
    @Transactional
    public void deletePatientById(long id) {
        patientRepository.deleteById(id);
    }


    @Transactional
    public Patient updatePatient(PatientRegistrationDto registrationDto){
        Patient patient=patientRepository.getPatientById(registrationDto.getId());
        User user= patient.getUser();
        user.setMobile_no(registrationDto.getMobile_no());
//        user.setFirst_name(registrationDto.getFirst_name());
//        user.setLast_name(registrationDto.getLast_name());
        user.setAge(registrationDto.getAge());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        patient.setUser(user);
        userRepository.save(user);
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientByUser_Id(long id) {
        Patient patient=patientRepository.findByUser_Id(id);
        return patient;
    }

    @Override
    public Patient getPatientByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return patientRepository.findByUser_Id(user.getId());
    }

    //    }
    @Override
    @Transactional
    public Patient save(PatientRegistrationDto patientRegistrationDto){
//        Optional<Role> role=roleService.getRoleByName("DOCTOR");
        User user=new User(patientRegistrationDto.getFirst_name(),patientRegistrationDto.getLast_name(),patientRegistrationDto.getEmail(),passwordEncoder.encode(patientRegistrationDto.getPassword()),patientRegistrationDto.getMobile_no(), patientRegistrationDto.getGender(), patientRegistrationDto.getAge(), Arrays.asList(new Role("PATIENT")));
        Patient patient=new Patient(user);
        userRepository.save(user);
        return patientRepository.save(patient);
    }

}
