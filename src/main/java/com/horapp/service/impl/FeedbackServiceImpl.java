package com.horapp.service.impl;

import com.horapp.persistence.entity.Category;
import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.Feedback;
import com.horapp.persistence.repository.CategoryRepository;
import com.horapp.persistence.repository.FeedbackRepository;
import com.horapp.presentation.dto.request.FeedbackRequestDTO;
import com.horapp.presentation.dto.response.FeedbackResponseDTO;
import com.horapp.service.CategoryService;
import com.horapp.service.CourseService;
import com.horapp.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public FeedbackResponseDTO save(FeedbackRequestDTO feedbackRequestDTO) {
        try {
            Feedback feedback = new Feedback();
            ModelMapper modelMapper = new ModelMapper();
            feedback.setDescriptionName(feedbackRequestDTO.getDescriptionName());
            feedback.setCategoryList(new ArrayList<>());
            feedbackRequestDTO.getCategoryId().stream()
                    .forEach(categoryId -> {
                        Category category = categoryService.findEntityById(categoryId);
                        if(feedbackRequestDTO.getCourseId() != null){
                            Course course = courseService.findEntityById(feedbackRequestDTO.getCourseId());
                            feedback.setCourse(course);
                        }
                        feedback.getCategoryList().add(category);
                    });
            feedbackRepository.save(feedback);
            FeedbackResponseDTO feedbackResponseDTO = getFeedbackResponseDTO(feedback, modelMapper);
            if(feedbackRequestDTO.getCourseId() != null){
                feedbackResponseDTO.setCourse(feedback.getCourse().getCourseName());
            }
            return feedbackResponseDTO;
        }catch (Exception e) {
            throw new RuntimeException("Something goes wrong creating the feedback: " + e.getMessage());
        }
    }

    @Override
    public List<FeedbackResponseDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return feedbackRepository.findAll().stream()
                .map(feedback -> {
                    FeedbackResponseDTO feedbackResponseDTO = getFeedbackResponseDTO(feedback, modelMapper);
                    return feedbackResponseDTO;
                })
                .collect(Collectors.toList());
    }

    private static FeedbackResponseDTO getFeedbackResponseDTO(Feedback feedback, ModelMapper modelMapper) {
        FeedbackResponseDTO feedbackResponseDTO = modelMapper.map(feedback, FeedbackResponseDTO.class);
        List<String> categoryNames = feedback.getCategoryList().stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
        feedbackResponseDTO.setCategories(categoryNames);
        return feedbackResponseDTO;
    }

    @Override
    public FeedbackResponseDTO findById(Long id) {
        try {
            Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
            if(optionalFeedback.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                Feedback feedbackFind = optionalFeedback.get();
                FeedbackResponseDTO feedbackResponseDTO = modelMapper.map(feedbackFind, FeedbackResponseDTO.class);
                List<String> categories = feedbackFind.getCategoryList().stream()
                        .map(Category::getCategoryName)
                        .toList();
                feedbackResponseDTO.setCategories(categories);
                return feedbackResponseDTO;
            }
        } catch (Exception e) {
        System.err.println("Feedback with ID: " + e.getMessage() + "don´t exists in database");
        throw new RuntimeException(e);
    }
        return null;
    }
}
