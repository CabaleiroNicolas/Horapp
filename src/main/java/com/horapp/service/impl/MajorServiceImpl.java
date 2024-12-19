package com.horapp.service.impl;

import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.Major;
import com.horapp.persistence.entity.User;
import com.horapp.persistence.repository.MajorRepository;
import com.horapp.presentation.dto.request.MajorRequestDTO;
import com.horapp.presentation.dto.response.MajorResponseDTO;
import com.horapp.service.MajorService;
import com.horapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private UserService userService;

    @Override
    public MajorResponseDTO saveMajor(MajorRequestDTO majorRequestDTO) {
        try {
            Major major = new Major();
            major.setMajorName(majorRequestDTO.getMajorName());
            major.setDeleted(false);
            MajorResponseDTO majorResponseDTO = new MajorResponseDTO();
            if(majorRequestDTO.getUsername() != null){
                User user = userService.findByUsername(majorRequestDTO.getUsername());
                major.setUser(user);
                majorResponseDTO.setUsername(user.getUsername());
            }
            majorResponseDTO.setMajorName(major.getMajorName());
            List<String> courses = extractCourses(major);
            majorResponseDTO.setCourses(courses);
            majorRepository.save(major);
            return majorResponseDTO;
        } catch (Exception e) {
            throw new RuntimeException("Something goes wrong creating the major: " + e.getMessage());
        }
    }


    @Override
    public List<MajorResponseDTO> findAll() {
        return majorRepository.findByDeletedFalse().stream()
                .map(major -> {
                    MajorResponseDTO majorResponseDTO = getMajorResponseDTO(major);
                    return  majorResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public MajorResponseDTO findById(Long id) {
        try{
            Optional<Major> optionalMajor = majorRepository.findById(id);
            if(optionalMajor.isPresent()){
                Major major = optionalMajor.get();
                MajorResponseDTO majorResponseDTO = getMajorResponseDTO(major);
                return  majorResponseDTO;
            }
        } catch (Exception e) {
            System.err.println("Major with ID: " + e.getMessage() + "don´t exists in database");
        }
        return new MajorResponseDTO();
    }

    @Override
    public Major findEntityById(Long id) {
        try{
            Optional<Major> optionalMajor = majorRepository.findById(id);
            if(optionalMajor.isPresent()){
                Major major = optionalMajor.get();
                return major;
            }
        } catch (Exception e) {
            System.err.println("Major with ID: " + e.getMessage() + "don´t exists in database");
        }
        return null;
    }

    @Override
    public String deleteById(Long id) {
        try{
            Optional<Major> optionalMajor = majorRepository.findById(id);
            if(optionalMajor.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                Major major = optionalMajor.get();
                major.setDeleted(true);
                majorRepository.save(major);
                return "The Major with ID " + major.getIdMajor() + " was deleted successfully" ;
            }
        } catch (Exception e) {
            System.err.println("Major with ID: " + e.getMessage() + "don´t exists in database");
        }
        return "";
    }

    private static MajorResponseDTO getMajorResponseDTO(Major major) {
        MajorResponseDTO majorResponseDTO = new MajorResponseDTO();
        majorResponseDTO.setIdMajor(major.getIdMajor());
        majorResponseDTO.setMajorName(major.getMajorName());
        majorResponseDTO.setUsername((major.getUser()!=null) ? major.getUser().getUsername() : null);
        majorResponseDTO.setCourses(extractCourses(major));
        return majorResponseDTO;
    }

    private static List<String> extractCourses(Major major) {
        List<String> courses = major.getCourseList().stream()
                .map(Course::getCourseName)
                        .collect(Collectors.toList());
        return courses;
    }
}
