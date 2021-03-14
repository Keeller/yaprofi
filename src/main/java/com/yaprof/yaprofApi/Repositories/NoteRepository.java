package com.yaprof.yaprofApi.Repositories;

import com.yaprof.yaprofApi.Entities.NoteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity,Long> {

    @Query("SELECT n FROM NoteEntity n WHERE n.title LIKE CONCAT('%',:pattern,'%') OR n.content LIKE CONCAT('%',:pattern,'%')")
    public List<NoteEntity> findByPatternContaining(String pattern);
}
