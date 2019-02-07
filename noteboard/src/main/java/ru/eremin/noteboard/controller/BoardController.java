package ru.eremin.noteboard.controller;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.service.BoardService;
import ru.eremin.noteboard.service.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @autor Eremin Artem on 05.02.2019.
 */

@Controller
public class BoardController {

    private BoardService boardService;
    private UserService userService;

    @Autowired
    public BoardController(final BoardService boardService, final UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @RequestMapping(value = "/boards", method = RequestMethod.GET)
    public String boards(final Model model, @AuthenticationPrincipal final User user){
        final UserDTO userDTO = getUserDTO(user);
        if(userDTO == null) return "redirect:/logout";
        final List<BoardDTO> boards = boardService.findByAuthorId(userDTO.getId());
        model.addAttribute("boards", boards);
        return "boards-view";
    }

    @RequestMapping(value = "create-board", method = RequestMethod.POST)
    public String createBoard (@AuthenticationPrincipal final User user) {
        final UserDTO userDTO = getUserDTO(user);
        if(userDTO == null) return "redirect:/logout";
        final BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(UUID.randomUUID().toString());
        boardDTO.setAuthorId(userDTO.getId());
        boardDTO.setName("New Board");
        boardDTO.setDate(new Calendar.Builder().setInstant(new Date()).build());
        boardService.insert(boardDTO);
        return "redirect:/boards";
    }

    @Nullable
    private UserDTO getUserDTO(final User user) {
        if(user == null) return null;
        return userService.findUserByLogin(user.getUsername());
    }
}
