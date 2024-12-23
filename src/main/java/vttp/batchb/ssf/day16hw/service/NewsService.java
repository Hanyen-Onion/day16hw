package vttp.batchb.ssf.day16hw.service;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batchb.ssf.day16hw.model.Article;
import vttp.batchb.ssf.day16hw.model.SearchParams;

@Service
public class NewsService {
    
    public static final String SEARCH_URL = "https://newsapi.org/v2/top-headlines";
    //public static final String TEST_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=60146948831d4d3e8cbed9edf853bd57&category=general";

    @Value("${news.api.key}")
    private String apiKey;

    public List<Article> readSearchParams(SearchParams params) {

        //build url
        String url = UriComponentsBuilder
            .fromUriString(SEARCH_URL)
            .queryParam("apiKey", apiKey)
            .queryParam("q", params.query())
            .queryParam("category", params.category())
            .queryParam("country", params.country())
            .toUriString();
        
        //System.out.println(url);

        //construct the request
        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();

            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp;

            List<Article> articles = new LinkedList<>();

        try {
            resp = template.exchange(req, String.class);
            //get payload
            String payload = resp.getBody();
            //return getNews(payload);
        
            //create json reader
            JsonReader reader = Json.createReader(new StringReader(payload));
            
            //read json file first obj
            JsonObject result = reader.readObject();
            JsonArray articleArray = result.getJsonArray("articles");

            //loop json array
            for (int i = 0; i < articleArray.size(); i++) {
                Article article = new Article();
                //read strings to get info
                String title = articleArray.getJsonObject(i)
                                 .getString("title");
                String description = articleArray.getJsonObject(i)
                                .getString("description");
                String articleUrl = articleArray.getJsonObject(i)
                                .getString("url");
                String imageUrl = articleArray.getJsonObject(i).getString("urlToImage", null);
                System.out.println(imageUrl);
                //String imgUrl = articleArray.getJsonObject(i)
                //                .getString("urlToImage");
                
                article.setTitle(title);
                article.setUrl(articleUrl);
                article.setImgUrl(imageUrl);
                article.setDescription(description);

                //System.out.println(article.toString());
                articles.add(article);    
            }
        return articles;   

        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

//     private List<Article> getNews(String json) {

//         //create json reader
//         JsonReader reader = Json.createReader(new StringReader(json));
        
//         //read json file first obj
//         JsonObject result = reader.readObject();
//         JsonArray articleArray = result.getJsonArray("articles");
   
//         List<Article> articles = new LinkedList<>();
        
//         //loop json array
//         for (int i = 0; i < articleArray.size(); i++) {
//             JsonObject articleObj = articleArray.getJsonObject(i);
//             //read strings to get info
//             String title = articleObj
//                             .getString("title");
//             String description = articleObj
//                             .getString("description");
//             String articleUrl = articleObj
//                             .getString("url");
//             String imgUrl = articleObj
//                             .getString("urlToImage");
//             // String content = articleArray.getJsonObject(i)
//             //                 .getString("content");

//             Article article = new Article();
//             article.setTitle(title);
//             article.setUrl(articleUrl);
//             article.setImgUrl(imgUrl);
//             article.setDescription(description);
//             // article.setContent(content);

//             //System.out.println(article.toString());
//             articles.add(article);    
//         }
//         return articles;
//     }
}
