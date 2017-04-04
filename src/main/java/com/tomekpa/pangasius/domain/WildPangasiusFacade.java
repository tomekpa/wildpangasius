package com.tomekpa.pangasius.domain;

/**
 * Created by tom on 2017-04-04.
 */
class WildPangasiusFacade {

    HealthHeckDto healthCheck(){
        return new HealthHeckDto("Hello from WildPangasiusFacade");
    }

}
