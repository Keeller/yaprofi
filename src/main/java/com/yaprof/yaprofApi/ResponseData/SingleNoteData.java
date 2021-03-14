package com.yaprof.yaprofApi.ResponseData;

import com.yaprof.yaprofApi.Entities.NoteEntity;

public class SingleNoteData implements IResponseData {
    private NoteEntity data;

    public SingleNoteData(NoteEntity data) {
        this.data = data;
    }

    public NoteEntity getData() {
        return data;
    }

    public void setData(NoteEntity data) {
        this.data = data;
    }
}
