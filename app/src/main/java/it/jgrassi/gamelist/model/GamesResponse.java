package it.jgrassi.gamelist.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by j.grassi on 28/11/2017.
 */

public class GamesResponse implements Serializable {

    public String response;
    public String currency;
    public List<Game> data;
}
