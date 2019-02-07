package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.dto.CategoryDTO;
import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.entity.Note;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface INoteService extends IService<NoteDTO, Note> {

    List<NoteDTO> findNotesByBoardId(final String id);

    List<NoteDTO> findNotesByAuthor(final UserDTO author);

    List<NoteDTO> findNotesByCategory(final CategoryDTO category);

    List<NoteDTO> findNotesByCategoryId(final String categoryId);

    List<NoteDTO> findNotesByBoard(final BoardDTO board);

}
