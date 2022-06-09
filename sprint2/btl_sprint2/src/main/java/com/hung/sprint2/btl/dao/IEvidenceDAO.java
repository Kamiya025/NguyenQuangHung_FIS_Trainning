package com.hung.sprint2.btl.dao;

import fis.trainning.quang_hung.btl.model.Evidence;

import java.util.List;

public interface IEvidenceDAO {

    Evidence getById(long id);
    void add( Evidence evidence);
    Evidence update( Evidence evidence);
    Evidence remove( long id);
    List<Evidence> getAll();
}
