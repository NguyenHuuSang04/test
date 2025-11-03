package iuh.fit.controller;


import iuh.fit.entities.Busroute;
import iuh.fit.services.BusrouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/busroutes")
public class BusrouteController {

    @Autowired
    private BusrouteService busrouteService;

    @GetMapping()
    public String showAll(Model model) {
        List<Busroute> busroutes = busrouteService.getAll();
        model.addAttribute("busroutes", busroutes);
        return "/busroutes";

    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Integer id, Model model) {
        Busroute busroute = busrouteService.getById(id);
        model.addAttribute("busroute", busroute);
        return "/detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, Model model) {
        List<Busroute> busroutes = busrouteService.getByName(name);
        model.addAttribute("busroutes", busroutes);
        return "/busroutes";
    }

    // d
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showFormAdd(Model model) {
        model.addAttribute("busroute", new Busroute());
        return "form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String add(@ModelAttribute Busroute busroute) throws IOException{
        busrouteService.save(busroute);
        return "redirect:/busroutes";
    }

    // d
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit")
    public String showFormEdit(@PathVariable Integer id, Model model) {
        Busroute busroute = busrouteService.getById(id);
        model.addAttribute("busroute", busroute);
        return "form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,
                       @ModelAttribute Busroute busroute) throws IOException {
        busroute.setId(id);
        ;
        busrouteService.save(busroute);
        return "redirect:/busroutes";
    }

}
