package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.TownEntity;

import java.util.List;

public interface TownService {

    List<TownEntity> getAllTowns();

    TownEntity findTownByName(String townName);
}
