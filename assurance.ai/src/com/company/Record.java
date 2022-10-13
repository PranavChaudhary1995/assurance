package com.company;
public class Record {
    Integer performance;
    Integer performanceRating;
    Integer verbalFeedback;
    public Record(Integer performance, Integer performanceRating, Integer verbalFeedback){
        this.performance=performance;
        this.performanceRating=performanceRating;
        this.verbalFeedback=verbalFeedback;
    }
    public Integer getPerformance(){
        return this.performance;
    }
    public Integer getPerformanceRating(){
        return this.performanceRating;
    }
    public Integer getVerbalFeedback(){
        return this.verbalFeedback;
    }
}
