package com.laboratory.msvc.courses.infraestructure.abstract_sevices;

public interface CrudService<RQ, RS, ID>  {

    RS findById(ID id);
    RS create(RQ request);
    RS update(RQ request, ID id);
    void delete(ID id);
}
