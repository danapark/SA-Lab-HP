package models;

import javax.persistence.Id;

import play.db.ebean.Model;

public class History extends Model {
    private static final long serialVersionUID = 1L;
    private static Finder<Long, History> find = new Finder<Long, History>(
            Long.class, History.class);
    
    @Id
    public Long id;
    public String career;
    public String place;
    public Integer duration_begin;
    public Integer duration_end;
    
    
}
