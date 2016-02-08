package com.malexj.service;

import com.malexj.model.oneToOneMappinpWithCascadingAll.Cat;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
public interface CatService {

    Cat save(Cat cat);

    Cat update(Cat cat);

    void delete(Long id);

    Cat findById(Long id);

    List<Cat> findAll();

}
