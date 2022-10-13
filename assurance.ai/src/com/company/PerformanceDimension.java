package com.company;
import java.util.*;
public class PerformanceDimension{
    String dimension="";
    List<Integer> performance;
    List<Integer> performanceRating;
    List<Integer> changeInRating;
    List<Integer> verbalFeedback;
    List<Integer> cumulativePerformance;
    List<Integer> cumulativeChangeInRating;
    List<Integer> cumulativeVerbalFeedback;
    /**
     * @param dimension
     * Constructor creates a new performance dimension and instantiates arrays to track it.
     */
    public PerformanceDimension(String dimension){
        this.dimension=dimension;
        performance=new ArrayList<>();
        performanceRating=new ArrayList<>();
        changeInRating=new ArrayList<>();
        verbalFeedback=new ArrayList<>();
        cumulativePerformance=new ArrayList<>();
        cumulativeChangeInRating=new ArrayList<>();
        cumulativeVerbalFeedback=new ArrayList<>();
    }
    /**
     * Updates: Add @param p to the end of performance list
     *          Add @param r to the end of rating list
     *          Add @param v to the end of verbalFeedback list
     */
    public void insertNewRecord (Record prv){
        performance.add(prv.getPerformance());
        performanceRating.add(prv.getPerformanceRating());
        verbalFeedback.add(prv.getVerbalFeedback());
    }
    public int getNumberRecords(){
        return this.performance.size();
    }
    public String getDimensionName(){
        return this.dimension;
    }
}







