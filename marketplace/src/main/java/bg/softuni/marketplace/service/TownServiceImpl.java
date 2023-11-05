package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.TownEntity;
import bg.softuni.marketplace.repository.TownRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public List<TownEntity> getAllTowns() {
        return this.townRepository.findAll();
    }

    @Override
    public TownEntity findTownByName(String townName) {


        return this.townRepository.findByName(townName).orElse(null);
    }
}
