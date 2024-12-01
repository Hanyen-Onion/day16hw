package vttp.batchb.ssf.day16hw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.batchb.ssf.day16hw.model.Article;
import vttp.batchb.ssf.day16hw.model.SearchParams;
import vttp.batchb.ssf.day16hw.service.NewsService;

@Controller
@RequestMapping
public class NewsController {

    @Autowired
    private NewsService newsSvc;

    @GetMapping("/search")
    public ModelAndView getSearch(@RequestParam MultiValueMap<String, String> queryParams) {

        SearchParams params = new SearchParams(
            queryParams.getFirst("query"),
            queryParams.getFirst("category"),
            queryParams.getFirst("country"));
        
        List<Article> articles = newsSvc.readSearchParams(params);

        ModelAndView mav = new ModelAndView();

        //rediect page to
        mav.setViewName("search");
        //add model
        mav.addObject("query", params.query());
        mav.addObject("articles", articles);

        System.out.println("is list empty:"+ articles.isEmpty());
        
        return mav;
    }
}
