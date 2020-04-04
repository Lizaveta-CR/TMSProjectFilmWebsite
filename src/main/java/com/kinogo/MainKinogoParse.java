package com.kinogo;

import com.service.SecurityServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainKinogoParse {
    public List<Film> parse(int num) throws Exception {
        String URL = "https://kinogo.by/page/";
        ThreadParser threadParser = new ThreadParser(URL, 1, num);
        List<Film> filmList = threadParser.call();
        return filmList;
    }
}