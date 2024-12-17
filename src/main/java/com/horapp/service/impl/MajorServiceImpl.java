package com.horapp.service.impl;

import com.horapp.persistence.entity.Major;
import com.horapp.persistence.repository.MajorRepository;
import com.horapp.presentation.dto.MajorDTO;
import com.horapp.service.MajorService;
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

    @Override
    public MajorDTO saveMajor(MajorDTO majorDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Major major = modelMapper.map(majorDTO, Major.class);
            majorRepository.save(major);
            return majorDTO;
        } catch (Exception e) {
            throw new RuntimeException("Something goes wrong creating the major: " + e.getMessage());
        }
    }

    @Override
    public List<MajorDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return majorRepository.findByDeletedFalse().stream()
                .map(major -> modelMapper.map(major, MajorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MajorDTO findById(Long id) {
        try{
            Optional<Major> optionalMajor = majorRepository.findById(id);
            if(optionalMajor.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                Major major = optionalMajor.get();
                return modelMapper.map(major, MajorDTO.class);
            }
        } catch (Exception e) {
            System.err.println("Major with ID: " + e.getMessage() + "don´t exists in database");
        }
        return new MajorDTO();
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
    public Optional<MajorDTO> updateById(MajorDTO majorDTO, long id) {
        return Optional.empty();
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
}
