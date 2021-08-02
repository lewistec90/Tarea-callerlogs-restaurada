package com.example.callerlogs.entidades;

public class Datasurvey2 {
    private Integer id;
    private Boolean used;
    private String ans;

    public Datasurvey2(Integer id, Boolean used, String ans) {
        this.id = id;
        this.ans = ans;
        this.used = used;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getUsed(){
        return used;
    }
    public void setUsed(Boolean used){
        this.used = used;
    }

    public String getAns() {
        return ans;
    }

    public void setAns1(String ans) {
        this.ans = ans;
    }
}
