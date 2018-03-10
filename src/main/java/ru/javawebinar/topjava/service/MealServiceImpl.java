package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.repository.MealRepository;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private final MealRepository repository;

    public MealServiceImpl( MealRepository repository ) {
        this.repository = repository;
    }
}