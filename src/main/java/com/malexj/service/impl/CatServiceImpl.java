package com.malexj.service.impl;

import com.malexj.model.oneToOneMappinpWithCascadingAll.Cat;
import com.malexj.repository.CatRepository;
import com.malexj.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private CatRepository repository;

    @Override
    public Cat save(Cat cat) {
        return repository.saveAndFlush(cat);
    }

    @Override
    public Cat update(Cat cat) {
        return repository.saveAndFlush(cat);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Cat findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Cat> findAll() {
        return repository.findAll();
    }
}
