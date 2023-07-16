package com.acheron.audio.controllers;

import com.acheron.audio.dao.SessionDAO;
import com.acheron.audio.entity.SessionEntity;
import com.acheron.audio.find_references.DownloadYouTubeAudio;
import com.acheron.audio.find_references.SearchAudioEntity;

import com.acheron.audio.util.ConnectionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    List<String> data = new ArrayList<>();
    String ghj;
    int i = 0;
    private String nameRoom;

    @GetMapping("/createRoom")
    public String showCreateRoomForm() {
        return "create-room";
    }

    @PostMapping("/createRoom")
    public String createRoom(@RequestParam("name") String name, @RequestParam("password") String password) {
        SessionDAO sessionDAO = new SessionDAO();
        sessionDAO.save(new SessionEntity(name, password));
        nameRoom = name;
        return "redirect:/" + nameRoom;
    }

    @GetMapping("/")
    public String mainPage(Model model, HttpServletResponse response) {
        response.addCookie(new Cookie("name", "artem"));
        var sessionDAO = new SessionDAO();
        model.addAttribute("messages", sessionDAO.findAll());
        model.addAttribute("message2", ghj);
        return "hub";
    }

    @PostMapping("/res")
    public String mainPagePost(@RequestParam("textInput") String text1, Model model) {
        String value = text1;
        System.out.println(text1);
        var search = SearchAudioEntity.searchIdURL(value, 5);
//        var references = DownloadYouTubeAudio.downloadAudio(search);
//        System.out.println(references.size());
//        System.out.println(references);
//        model.addAttribute("messages", references);
        return "index";
    }

    @GetMapping("/test1")
    public String home() {
        return "first";
    }

    @GetMapping("/data")
    @ResponseBody
    public List<String> getData() {
        data.add("test%s".formatted(i));
        i++;
        return data;
    }

    @PostMapping("/{roomId}")
    public String showRoomPage(@PathVariable String roomId, Model model) {
//        var list = DownloadYouTubeAudio.downloadAudio(SearchAudioEntity.searchURL(text, 5));
        return "first";
    }

    @PostMapping("/verification/{roomId}")
    public String verificationPage(@PathVariable String roomId, Model model, @RequestParam("password") String password) {
        try (var open = ConnectionUtil.open();
             var preparedStatement = open.prepareStatement("select password from session where name=?");
             var preparedStatement1 = open.prepareStatement("select name from session where password=?");) {
            preparedStatement.setString(1, roomId);
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String ghj1 = resultSet.getString(1);
            preparedStatement1.setString(1, ghj1);
            var resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            ghj = resultSet1.getString("name");
            if (ghj1.equals(password)) {
                return "redirect:/";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ghj = null;
        return "redirect:/";
    }
}