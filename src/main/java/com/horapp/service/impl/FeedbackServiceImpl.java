package com.horapp.service.impl;

import com.horapp.persistence.entity.Category;
import com.horapp.persistence.entity.Feedback;
import com.horapp.persistence.repository.CategoryRepository;
import com.horapp.persistence.repository.FeedbackRepository;
import com.horapp.presentation.dto.FeedbackRequestDTO;
import com.horapp.presentation.dto.FeedbackResponseDTO;
import com.horapp.service.CategoryService;
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
    private CategoryRepository categoryRepository;

    @Override
    public FeedbackRequestDTO save(FeedbackRequestDTO feedbackRequestDTO) {
        try {
            Feedback feedback = new Feedback();
            ModelMapper modelMapper = new ModelMapper();
            feedback.setDescriptionName(feedbackRequestDTO.getDescriptionName());
            feedback.setCategoryList(new ArrayList<>());
            feedbackRequestDTO.getCategoryId().stream()
                    .forEach(categoryId -> {
                        Category category = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new RuntimeException("Category not found"));
                        feedback.getCategoryList().add(category);
                    });
            feedbackRepository.save(feedback);
            return modelMapper.map(feedback, FeedbackRequestDTO.class);
        }catch (Exception e) {
            throw new RuntimeException("Something goes wrong creating the feedback: " + e.getMessage());
        }
    }

    @Override
    public List<FeedbackResponseDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return feedbackRepository.findAll().stream()
                .map(feedback -> {
                    FeedbackResponseDTO feedbackResponseDTO = modelMapper.map(feedback, FeedbackResponseDTO.class);
                    List<String> categoryNames = feedback.getCategoryList().stream()
                            .map(Category::getCategoryName)
                            .collect(Collectors.toList());
                    feedbackResponseDTO.setCategories(categoryNames);
                    return feedbackResponseDTO;
                })
                .collect(Collectors.toList());
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
