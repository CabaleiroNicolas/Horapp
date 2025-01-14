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
import org.webjars.NotFoundException;

import java.util.Collections;
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
    }


    @Override
    public List<MajorResponseDTO> findAll() {
        return majorRepository.findByDeletedFalse().stream()
                .map(MajorServiceImpl::getMajorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MajorResponseDTO findById(Long id) {
        Optional<Major> optionalMajor = majorRepository.findById(id);
            if(optionalMajor.isEmpty()){
                throw new NotFoundException("Major not found with Id = " + id);
            }
            Major major = optionalMajor.get();
            return getMajorResponseDTO(major);
        }


    @Override
    public Major findEntityById(Long id) {
        Optional<Major> optionalMajor = majorRepository.findById(id);
        if(optionalMajor.isEmpty()){
            throw new NotFoundException("Major not found with Id = " + id);
        }
        return  optionalMajor.get();
    }

    @Override
    public String deleteById(Long id) {
        Optional<Major> optionalMajor = majorRepository.findById(id);
        if(optionalMajor.isEmpty()){
            throw new NotFoundException("Major not found with Id = " + id);
        }
            ModelMapper modelMapper = new ModelMapper();
            Major major = optionalMajor.get();
            major.setDeleted(true);
            majorRepository.save(major);
            return "The Major with ID " + major.getIdMajor() + " was deleted successfully" ;
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
        return major.getCourseList() != null
                ? major.getCourseList().stream()
                .map(Course::getCourseName)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }
}
