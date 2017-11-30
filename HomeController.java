package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    TweetMessageRepository tweetMessageRepository;


    @RequestMapping("/")
    public String listTweetmessages(Model model) {
        model.addAttribute("tweetmessages", tweetMessageRepository.findAll());
        return "list";
    }
        @GetMapping("/add")
        public String tweetForm(Model model)
        {
            model.addAttribute("tweetmessage", new TweetMessage());
            return "tweetform";
        }
        @PostMapping("/process")
        public String processForm(@Valid TweetMessage tweetmessage, BindingResult result, Model model)
        {
            if (result.hasErrors())
            {
                //model.addAttribute("tweetmessage", tweetMessage);
                return "tweetform";
            }
            tweetMessageRepository.save(tweetmessage);
            return "redirect:/";
        }
    @RequestMapping("/detail/{id}")
    public String showTweetMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("tweetmessage", tweetMessageRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateTweetMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("tweetmessage", tweetMessageRepository.findOne(id));
        return "tweetform";
    }
    @RequestMapping("/delete/{id}")
    public String delTweetMessage(@PathVariable("id") long id){
        tweetMessageRepository.delete(id);
        return "redirect:/";
    }
    }

