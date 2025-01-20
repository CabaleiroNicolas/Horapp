package com.horapp.service.impl;

import com.horapp.persistence.entity.Category;
import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.Feedback;

import com.horapp.persistence.repository.FeedbackRepository;
import com.horapp.presentation.dto.request.FeedbackRequestDTO;
import com.horapp.presentation.dto.response.FeedbackResponseDTO;
import com.horapp.service.CategoryService;
import com.horapp.service.CourseService;
import com.horapp.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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


    @Override
    public FeedbackResponseDTO save(FeedbackRequestDTO feedbackRequestDTO) {
            List<Category> categoryList = new ArrayList<>();
            feedbackRequestDTO.categoryId().forEach(categoryId -> {
                Category category = new Category(categoryId);
                categoryList.add(category);
            });
            Feedback feedback = new Feedback(
                    categoryList,
                    feedbackRequestDTO.courseId() != null ? new Course(feedbackRequestDTO.courseId()) : null,
                    feedbackRequestDTO.descriptionName()
                    );
            feedbackRepository.save(feedback);

            return buildFeedbackResponseDTO(feedback);

    }

    @Override
    public List<FeedbackResponseDTO> findAll() {
        return feedbackRepository.findAll().stream()
                .map(this::buildFeedbackResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public FeedbackResponseDTO findById(Long id) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if(optionalFeedback.isEmpty()){
            throw new NotFoundException("Feedback not found with Id = " + id);
        }
            Feedback feedbackFind = optionalFeedback.get();
        return buildFeedbackResponseDTO(feedbackFind);
    }

    private FeedbackResponseDTO buildFeedbackResponseDTO(Feedback feedback) {
        List<String> categoryNames = feedback.getCategoryList().stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());

        String courseName = feedback.getCourse() != null ? feedback.getCourse().getCourseName() : null;

        return new FeedbackResponseDTO(
                feedback.getIdFeedback(),
                feedback.getDescriptionName(),
                categoryNames,
                courseName
        );
    }
}
