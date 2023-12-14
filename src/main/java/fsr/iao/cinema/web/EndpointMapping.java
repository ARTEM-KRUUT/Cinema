package fsr.iao.cinema.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/endpoint")
public class EndpointMapping {

    @GetMapping("/html")
    public String showEndpointPage() {
        return "endpoints"; // This corresponds to endpoint.html in the templates directory
    }
}