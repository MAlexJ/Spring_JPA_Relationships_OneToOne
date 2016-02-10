package com.malexj.service;

import com.malexj.model.oneToOneMappingPrimaryKeyAsJoinColumn.Pot;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
public interface PotService {

    Pot save(Pot pot);

    Pot update(Pot pot);

    void delete(Long id);

    Pot findById(Long id);

    List<Pot> findAll();

}
