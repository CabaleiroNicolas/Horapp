package com.horapp.service.impl;

import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.TimeTable;
import com.horapp.persistence.entity.User;
import com.horapp.persistence.repository.TimeTableRepository;
import com.horapp.presentation.dto.request.TimeTableRequestDTO;
import com.horapp.presentation.dto.response.TimeTableResponseDTO;
import com.horapp.service.TimeTableService;
import com.horapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<TimeTableResponseDTO> findAll() {
        return timeTableRepository.findByDeletedFalse().stream()
                .map(TimeTableServiceImpl::getTimeTableResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TimeTableResponseDTO findById(Long id) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if(optionalTimeTable.isEmpty()){
            throw new NotFoundException("TimeTable not found with Id = " + id);
        }
        return getTimeTableResponseDTO(optionalTimeTable.get());
    }

    @Override
    public TimeTable findEntityById(Long id) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if(optionalTimeTable.isEmpty()){
            throw new NotFoundException("TimeTable not found with Id = " + id);
        }
        return optionalTimeTable.get();
    }


    @Override
    public TimeTableResponseDTO save(TimeTableRequestDTO timeTableRequestDTO) {
            User user = userService.findByUsername(timeTableRequestDTO.getUsername());
            TimeTable timeTable = new TimeTable();
            timeTable.setUser(user);
            TimeTableResponseDTO timeTableResponseDTO = new TimeTableResponseDTO();
            timeTableRepository.save(timeTable);
            timeTableResponseDTO.setUsername(user.getUsername());
            return timeTableResponseDTO;
    }

    @Override
    public String deleteById(Long id) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if(optionalTimeTable.isEmpty()) {
            throw new NotFoundException("TimeTable not found with Id = " + id);
        }
        TimeTable timeTable = optionalTimeTable.get();
        timeTable.setDeleted(true);
        timeTableRepository.save(timeTable);
        return "The TimeTable with id " + id + " has been deleted successfully";
    }

    private static TimeTableResponseDTO getTimeTableResponseDTO(TimeTable timeTable) {
        TimeTableResponseDTO timeTableResponseDTO = new TimeTableResponseDTO();
        timeTableResponseDTO.setUsername(timeTable.getUser().getUsername());
        timeTableResponseDTO.setId(timeTable.getIdTimeTable());
        List<String> courses = timeTable.getCourseList().stream().map(Course::getCourseName).collect(Collectors.toList());
        timeTableResponseDTO.setCourses(courses);
        return timeTableResponseDTO;
    }

}
