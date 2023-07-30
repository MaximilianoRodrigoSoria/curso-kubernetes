package com.laboratory.msvc.usuarios.infraestructure.abstract_sevices;

import java.util.List;
import java.util.Optional;

public interface CrudService<RQ, RS, ID>  {

    RS findById(ID id);
    RS create(RQ request);
    RS update(RQ request, ID id);
    void delete(ID id);
}
