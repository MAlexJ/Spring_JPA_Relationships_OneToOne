package com.malexj.service;

import com.malexj.model.oneToOneMappingWithFetchTypeLazy.NotebookStand;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
public interface NotebookStandService {

    NotebookStand save(NotebookStand notebookStand);

    NotebookStand update(NotebookStand notebookStand);

    void delete(Long id);

    NotebookStand findById(Long id);

    List<NotebookStand> findAll();
}
