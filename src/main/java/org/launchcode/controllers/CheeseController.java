package org.launchcode.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by carlylanglois on 5/2/17.
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    //static HashMap<String, String> cheeses = new HashMap<>()
    static ArrayList<Cheese> cheeses = new ArrayList<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, String cheeseDescription){
        //String cheeseName = request.getParameter("cheeseName");
        Cheese aCheese = new Cheese();
        aCheese.setName(cheeseName);
        aCheese.setDescription(cheeseDescription);
        cheeses.add(aCheese);

        //redirect to /cheese (the path that was empty)
        return "redirect:";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Remove Cheese");

        if (cheeses.isEmpty()) {
            return "redirect:";
        }
         else {
            return "cheese/remove";
        }
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheeseToRemove){

        ArrayList<Cheese> badCheeses = new ArrayList<>();
        for (String badCheese : cheeseToRemove){
            for (Cheese cheese : cheeses){
                if(cheese.getName().equals(badCheese)) {
                    badCheeses.add(cheese);
                }
            }
        }
        cheeses.removeAll(badCheeses);

        //redirect to /cheese (the path that was empty)
        return "redirect:";
    }


}
