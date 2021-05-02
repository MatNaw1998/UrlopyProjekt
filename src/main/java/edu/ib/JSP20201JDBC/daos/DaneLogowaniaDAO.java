package edu.ib.JSP20201JDBC.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaneLogowaniaDAO implements DAO<DaneLogowania> {

    private List<DaneLogowania> users = new ArrayList<>();
    @Override
    public Optional<DaneLogowania> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<DaneLogowania> getAll() {
        return null;
    }

    @Override
    public void save(DaneLogowania daneLogowania) {

    }

    @Override
    public void update(DaneLogowania daneLogowania, String[] params) {

    }

    @Override
    public void delete(DaneLogowania daneLogowania) {

    }
}
