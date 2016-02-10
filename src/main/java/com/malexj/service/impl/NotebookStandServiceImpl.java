package com.malexj.service.impl;

import com.malexj.model.oneToOneMappingWithFetchTypeLazy.NotebookStand;
import com.malexj.repository.NotebookStandRepository;
import com.malexj.service.NotebookStandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
@Service
public class NotebookStandServiceImpl implements NotebookStandService {

    @Autowired
    private NotebookStandRepository repository;

    @Override
    public NotebookStand save(NotebookStand notebookStand) {
        return repository.saveAndFlush(notebookStand);
    }

    @Override
    public NotebookStand update(NotebookStand notebookStand) {
        return repository.saveAndFlush(notebookStand);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public NotebookStand findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<NotebookStand> findAll() {
        return repository.findAll();
    }
}
