package pe.nasqa.values.control;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.nasqa.values.model.entity.Menu;

@Controller
@RequestMapping(value="/gestiontelef")
public class GestionTelefControl {
	
	@RequestMapping(value={"/index.htm"}) 
	public String index(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D1.name());
		
		return "gestiontelef/index";
	}
}
