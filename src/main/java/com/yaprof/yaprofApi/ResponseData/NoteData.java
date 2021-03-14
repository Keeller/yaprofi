package com.yaprof.yaprofApi.ResponseData;

import com.yaprof.yaprofApi.Entities.NoteEntity;

import java.util.List;

public class NoteData implements IResponseData {

    private List<NoteEntity> entities;

    public NoteData(List<NoteEntity> entities) {
        this.entities = entities;
    }

    public List<NoteEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<NoteEntity> entities) {
        this.entities = entities;
    }
}
