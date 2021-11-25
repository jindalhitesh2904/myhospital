package com.example.myhospital.service;

import com.example.myhospital.dto.ReceptionistRegistrationDto;
import com.example.myhospital.model.Receptionist;
import com.example.myhospital.model.Role;
import com.example.myhospital.model.User;
import com.example.myhospital.repository.ReceptionistRepository;
import com.example.myhospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class ReceptionistServiceImpl implements ReceptionistService{
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Receptionist save(ReceptionistRegistrationDto receptionistRegistrationDto) {
//        Role role=roleService.getRoleByName("DOCTOR");
//        Receptionist receptionist=new Receptionist(receptionistRegistrationDto.getFirst_name(), receptionistRegistrationDto.getLast_name(),receptionistRegistrationDto.getEmail(), passwordEncoder.encode(receptionistRegistrationDto.getPassword()), receptionistRegistrationDto.getMobile_no(), receptionistRegistrationDto.getGender(), receptionistRegistrationDto.getAge(),Arrays.asList(role),receptionistRegistrationDto.getSalary(),receptionistRegistrationDto.getDepartment(),receptionistRegistrationDto.getDesignation());
//        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getAuthorities());
//        return receptionistRepository.save(receptionist);
//    }

    @Override
    public List<Receptionist> getAllReceptionists() {
        return receptionistRepository.findAll();
    }

    @Override
    public Receptionist getReceptionistById(long id){
        return receptionistRepository.getReceptionistById(id);
    }

    @Override
    @Transactional
    public void deleteReceptionistById(long id) {
        receptionistRepository.deleteById(id);
    }


    @Transactional
    public Receptionist updateReceptionist(ReceptionistRegistrationDto registrationDto){
        Receptionist receptionist=receptionistRepository.getReceptionistById(registrationDto.getId());
        User user= receptionist.getUser();
        user.setMobile_no(registrationDto.getMobile_no());
//        user.setFirst_name(registrationDto.getFirst_name());
//        user.setLast_name(registrationDto.getLast_name());
        user.setAge(registrationDto.getAge());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        receptionist.setUser(user);
        receptionist.setSalary(registrationDto.getSalary());
        receptionist.setDepartment(registrationDto.getDepartment());
        userRepository.save(user);
        return receptionistRepository.save(receptionist);
    }

    //    }
    @Override
    @Transactional
    public Receptionist save(ReceptionistRegistrationDto receptionistRegistrationDto){
//        Optional<Role> role=roleService.getRoleByName("DOCTOR");
        User user=new User(receptionistRegistrationDto.getFirst_name(),receptionistRegistrationDto.getLast_name(),receptionistRegistrationDto.getEmail(),passwordEncoder.encode(receptionistRegistrationDto.getPassword()),receptionistRegistrationDto.getMobile_no(), receptionistRegistrationDto.getGender(), receptionistRegistrationDto.getAge(), Arrays.asList(new Role("RECEPTIONIST")));
        Receptionist receptionist=new Receptionist(user,receptionistRegistrationDto.getSalary(),receptionistRegistrationDto.getDepartment());
        userRepository.save(user);
        return receptionistRepository.save(receptionist);
    }
}
