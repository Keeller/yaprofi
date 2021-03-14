package com.yaprof.yaprofApi.Controllers;

import com.yaprof.yaprofApi.Entities.NoteEntity;
import com.yaprof.yaprofApi.Exceptions.NotFoundException;
import com.yaprof.yaprofApi.Repositories.NoteRepository;
import com.yaprof.yaprofApi.Requests.NoteRequest;
import com.yaprof.yaprofApi.ResponseData.NoteData;
import com.yaprof.yaprofApi.ResponseData.SingleNoteData;
import com.yaprof.yaprofApi.Responsec.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/notes")
public class NoteController implements BaseController {

    private NoteRepository noteRepository;
    @Value("${notes.title.size}")
    private Integer titleSize;


    @Autowired
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Возвращает все заметки
     * @return список заметок
     */
    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse getAll(){
        try {
            List<NoteEntity> result = new ArrayList<>();
            noteRepository.findAll().forEach(result::add);
            return new BaseResponse("200", "", new NoteData(result));
        }
        catch (Exception ex){
            return fallback("500",ex.getMessage());
        }
    }

    /**
     * Добавляет сущность в базу
     * @param noteRequest сущность запроса
     * @return добавленная заметка
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse create(@RequestBody NoteRequest noteRequest){
        try {
            NoteEntity noteEntity=new NoteEntity();
            noteEntity.setContent(noteRequest.getContent());
            noteEntity.setTitle(noteRequest.getTitle());
            NoteEntity save = noteRepository.save(noteEntity);
            return new BaseResponse("200","",new SingleNoteData(save));
        }
        catch (Exception ex){
            return fallback("500",ex.getMessage());
        }
    }

    /**
     * получает заметку по id
     * @param id идентификатор заметки
     * @return заетка по данному id
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public BaseResponse getById(@PathVariable Integer id){
        try {
            NoteEntity noteEntity = noteRepository.findById(Long.valueOf(id)).orElseThrow(NotFoundException::new);
            if(noteEntity.getTitle()==null||noteEntity.getTitle().equals(""))
                noteEntity.setTitle(new String(noteEntity.getContent().substring(0,titleSize)));
            return new BaseResponse("200","",new SingleNoteData(noteEntity));
        }
        catch (Exception ex){
            return fallback("500",ex.getMessage());
        }
    }

    /**
     * Удаляет сущность с таким id из базы
     * @param id индентификатор заметки
     * @return отчет о выполнении
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable Integer id){
        try {
            noteRepository.deleteById(Long.valueOf(id));
            return new BaseResponse("200","",null);
        }
        catch (Exception ex){
            return fallback("500",ex.getMessage());
        }
    }

    /**
     * Обновляет заметку в базе
     * @param id идентификатор заметки
     * @param noteRequest запрос к базе
     * @return обновленная заметка
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public BaseResponse put(@PathVariable("id") Integer id,@RequestBody NoteRequest noteRequest){
        try {
            NoteEntity noteEntity = noteRepository.findById(Long.valueOf(id)).orElseThrow(NotFoundException::new);
            noteEntity.setContent(noteRequest.getContent());
            noteEntity.setTitle(noteRequest.getTitle());
            return new BaseResponse("200","",new SingleNoteData(noteRepository.save(noteEntity)));
        }
        catch (Exception ex){
            return fallback("200",ex.getMessage());
        }
    }

    /**
     * Осущетсвляет поиск по заданоому паттерну
     * @param search паттерн для поиска
     * @return список заметок по данному паттерну
     */
    @RequestMapping(params = {"search"})
    public BaseResponse findByPattern(@RequestParam(value = "search") String search){
        try {
            return new BaseResponse("200","",new NoteData(noteRepository.findByPatternContaining(search)));
        }
        catch (Exception ex){
            return fallback("500",ex.getMessage());
        }
    }


}
