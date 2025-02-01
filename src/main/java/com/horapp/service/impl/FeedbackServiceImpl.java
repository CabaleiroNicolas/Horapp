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

import java.util.ArrayList;
import java.util.List;
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
