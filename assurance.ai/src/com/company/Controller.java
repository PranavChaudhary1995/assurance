package com.company;
import java.util.*;
public class Controller {
    /**
     *  Describe what is @code performanceDimensionMap?
     */
    Map <String,PerformanceDimension> performanceDimensionsMap = new HashMap<>();
    Integer windowSize=0;
    Integer start=0,end=0;
    //Request.Method=[POST]
    //Problem is that this method does more than one thing...not aligned with SOLID principles...less than ideal.
    public void setWindowSize(Integer windowSize){
       this.windowSize=windowSize;
       this.setWindowEndIndex(windowSize-1);
    }
    //The user does not call this method..setting the start and end index is done in the backend based on the window size.
    //It is updated every time the alignment is checked.
    public void setWindowStartIndex(Integer start){
        this.start=start;
    }
    public void setWindowEndIndex(Integer end){
        this.end=end;
    }
    //Request.Method=[POST]
    public int getWindowSize(){
        return this.windowSize;
    }
    //Request.Method=[GET]
    public int getWindowStartIndex(){
        return this.start;
    }
    //Request.Method=[GET]
    public  int getWindowEndIndex(){
        return this.end;
    }
    /**
     * @return The @object {PerformanceDimension} with the string literal name of @param {dimension}
     */
    //Request.Method=[GET]
    public PerformanceDimension getPerformanceDimension(String dimension){
        return performanceDimensionsMap.get(dimension);
    }
    /**
     * @return The direction of performance between @param [start,end] for @param {pd} the given performance dimension
     * TODO: Can have Integer overflow bug in doing a naive subtraction. Look into using compareTo method.
     */
    //Request.Method=[GET]
    public Integer getDirectionPerformance(PerformanceDimension pd,Integer start, Integer end){
        return getSign(pd.cumulativePerformance.get(end)-pd.cumulativePerformance.get(start));
    }
    /**
     * @return The direction of performance rating between [start,end] for @param {pd} the given performance dimension
     * TODO: Can have Integer overflow bug in doing a naive subtraction. Look into using compareTo method.
     */
    //Request.Method=[GET]
    public Integer getDirectionPerformanceRating(PerformanceDimension pd, Integer start, Integer end){
        return getSign(pd.cumulativeVerbalFeedback.get(end)-pd.cumulativeVerbalFeedback.get(start));
    }
    /**
     * @return The direction of verbal feedback between [start,end] for @param {pd} the given performance dimension
     * TODO: Can have Integer overflow bug in doing a naive subtraction. Look into using compareTo method.
     */
    //Request.Method=[GET]
    public Integer getDirectionVerbalFeedback(PerformanceDimension pd,Integer start, Integer end){
        return getSign(pd.cumulativeVerbalFeedback.get(end)-pd.cumulativeVerbalFeedback.get(start));
    }
    //Request.Method=[PUT]
    //Want to give the user the feature to change the window size.
    public void updateWindowSize(Integer windowSize){
        this.windowSize=windowSize;
    }
    //Request.Method=[POST]
    public void addDimension(String dimension){
        PerformanceDimension p = new PerformanceDimension(dimension);
        this.performanceDimensionsMap.put(dimension,p);
    }
    //Request.Method=[POST]
    public void addNewRecordToPerformanceDimension(String performanceDimension, Record prv){
        PerformanceDimension pd = performanceDimensionsMap.get(performanceDimension);
        pd.insertNewRecord(prv);
        generateChangeInRating(pd);
        updateCumulativeLists(pd);
    }
    private void generateChangeInRating(PerformanceDimension pd){
        if(pd.changeInRating.isEmpty()) pd.changeInRating.add(0);
        else{
            int indexOfLastRating = pd.performanceRating.size()-1;
            int indexOfSecondToLastRating=indexOfLastRating-1;
            pd.changeInRating.add(pd.performanceRating.get(indexOfLastRating)-
                    pd.performanceRating.get(indexOfSecondToLastRating));
        }
    }
    /**
     * Inputs:List1,List2,List3,cList1,cList2,cList3
     *Updates: cLists with the last entry in list
     */
    private void updateCumulativeLists(PerformanceDimension pd) {
        pd.cumulativePerformance.add(pd.cumulativePerformance.isEmpty()?
                pd.performance.get(pd.performance.size()-1):
                (pd.cumulativePerformance.get(pd.cumulativePerformance.size()-1))+
                        pd.performance.get(pd.performance.size()-1));

        pd.cumulativeVerbalFeedback.add(pd.cumulativeVerbalFeedback.isEmpty()?
                pd.verbalFeedback.get(pd.verbalFeedback.size()-1):
                (pd.cumulativeVerbalFeedback.get(pd.cumulativeVerbalFeedback.size()-1))+
                        pd.verbalFeedback.get(pd.verbalFeedback.size()-1));

        pd.cumulativeChangeInRating.add(pd.cumulativeChangeInRating.isEmpty()?
                0:(pd.cumulativeChangeInRating.get(pd.cumulativeChangeInRating.size()-1))+
                pd.changeInRating.get(pd.changeInRating.size()-1));
    }
    /**
     * Returns true if the direction of numbers in window [@param {start}, @param {end}] for
     * 1) (performance,performanceRating) &&
     * 2) (performance,verbalFeedback) &&
     * 3) (performanceRating,verbalFeedback) are the same for the given @param {performance dimension}.
     */
    //Request.Method=[GET]
    public boolean isDirectionAligned(PerformanceDimension pd ,Integer start,Integer end){
        return (getDirectionPerformance(pd,start,end)==getDirectionPerformanceRating(pd,start,end))&&
                (getDirectionPerformanceRating(pd,start,end)==getDirectionVerbalFeedback(pd,start,end))&&
                (getDirectionPerformance(pd,start,end)==getDirectionVerbalFeedback(pd,start,end));
    }
    //Request.Method=[GET]
    public boolean isSpecialCaseDetected(Integer directionPerformance, Integer directionRating, Integer directionVerbalFeedback){
        if ((directionPerformance>0 && (directionRating<0||directionVerbalFeedback<0))||(directionVerbalFeedback>0&&directionRating<0)) return true;
        else return false;
    }
    /**Determines if @param i is positive, negative or zero.
     * @return 1 if i is +ve,
     * -1 if i is -ve,
     * 0 is if i=0
     */
    private static Integer getSign(int i) {
        if (i>0) return 1;
        else if(i==0) return 0;
        else return -1;
    }
}
