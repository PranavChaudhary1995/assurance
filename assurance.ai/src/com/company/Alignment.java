package com.company;
import java.util.*;

public class Alignment{
        List<Boolean> overallAlignment;
        List<Boolean> currentAlignment;
public Alignment(){
        overallAlignment= new ArrayList<>();
        currentAlignment= new ArrayList<>();
        }

public void setOverallAlignment(boolean isAligned){
        overallAlignment.set(0,isAligned);
        }
public void setOverallPerformancePerformanceRatingAlignment(boolean isAligned){
        overallAlignment.set(1,isAligned);
        }
public void setOverallPerformanceVerbalFeedbackAlignment(boolean isAligned){
        overallAlignment.set(2,isAligned);
        }
public void setOverallPerformanceRatingVerbalFeedbackAlignment(boolean isAligned){
        overallAlignment.set(3,isAligned);
        }
public void setOverallSpecialCase(boolean specialCase){
        overallAlignment.set(4,specialCase);
        }
public void getOverallAlignment(){
        overallAlignment.get(0);
        }
public void getOverallPerformancePerformanceRatingAlignment(){
        overallAlignment.get(1);
        }
public void getOverallPerformanceVerbalFeedbackAlignment(){
        overallAlignment.get(2);
        }
public void getOverallPerformanceRatingVerbalFeedbackAlignment(){
        overallAlignment.get(3);
        }
public void getOverallSpecialCase(){
        overallAlignment.get(4);
        }
public void setCurrentAlignment(boolean isAligned){
        currentAlignment.set(0,isAligned);
        }
public void setCurrentPerformancePerformanceRatingAlignment(boolean isAligned){
        currentAlignment.set(1,isAligned);
        }
public void setCurrentPerformanceVerbalFeedbackAlignment(boolean isAligned){
        currentAlignment.set(2,isAligned);
        }
public void setCurrentPerformanceRatingVerbalFeedbackAlignment(boolean isAligned){
        currentAlignment.set(3,isAligned);
        }
public void setCurrentSpecialCase(boolean specialCase){
        currentAlignment.set(4,specialCase);
        }
public void getCurrentAlignment(){
        currentAlignment.get(0);
        }
public void getCurrentPerformancePerformanceRatingAlignment(){
        currentAlignment.get(1);
        }
public void getCurrentPerformanceVerbalFeedbackAlignment(){
        currentAlignment.get(2);
        }
public void setCurrentPerformanceVerbalFeedbackAlignment(){
        currentAlignment.get(3);
        }
public void setCurrentSpecialCase(){
        currentAlignment.get(4);
        }

}
