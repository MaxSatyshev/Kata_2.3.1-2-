package App.controller;

import App.model.User;
import App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String printUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/show";
    }
    @GetMapping("/new_user")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/new_user";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return null;
    }

}
