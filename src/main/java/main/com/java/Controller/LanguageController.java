package main.com.java.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class LanguageController {

    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("lang") String lang, HttpSession session) {
        switch (lang.toLowerCase()) {
            case "hi":
                session.setAttribute("locale", new Locale("hi", "IN"));
                break;
            case "mr":
                session.setAttribute("locale", new Locale("mr", "IN"));
                break;
            default:
                session.setAttribute("locale", Locale.ENGLISH);
        }
        return "redirect:/dashboard";
    }
}
