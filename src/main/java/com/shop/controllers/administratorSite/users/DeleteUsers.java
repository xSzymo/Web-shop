package com.shop.controllers.administratorSite.users;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.tables.User;
import com.shop.data.tables.UserRole;
import com.shop.others.RepositoriesAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("administratorSite/users")
public class DeleteUsers {

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteSite(Model model) {
        Iterable<User> users = RepositoriesAccess.usersRepository.findAll();
        Iterable<UserRole> roles = RepositoriesAccess.userRolesRepository.findAll();

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administratorSite/usersManager/delete";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public RedirectView deleteFromButton(@PathVariable Long id, Model model, RedirectAttributes red) {
        User foundUser = RepositoriesAccess.usersRepository.findById(id);

        if (foundUser == null)
            red.addFlashAttribute("msg", "not found");
        else {
            RepositoriesAccess.usersRepository.delete(foundUser);
            red.addFlashAttribute("msg", "Succes, back to delete more");
        }
        Iterable<User> users = RepositoriesAccess.usersRepository.findAll();
        model.addAttribute("users", users);

        return new RedirectView(ApplicationProperties.PROJECT_NAME + "administratorSite/users/delete");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteFromInputText(@RequestParam("userName") String userName, Model model) {
        User foundUser = RepositoriesAccess.usersRepository.findByLogin(userName);

        if (foundUser == null)
            model.addAttribute("msg", "not found");
        else {
            RepositoriesAccess.usersRepository.delete(foundUser);
            model.addAttribute("msg", "Succes");
        }

        Iterable<User> users = RepositoriesAccess.usersRepository.findAll();
        model.addAttribute("users", users);
        return "/administratorSite/usersManager/delete";
    }
}
