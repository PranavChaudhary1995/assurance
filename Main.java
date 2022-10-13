package com.company;
import java.util.*;
public class Main {
    static boolean specialCase=false;
    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner myScanner = new Scanner(System.in);
        //Step 1: User sets window size
        System.out.print("Enter the window size: ");
        Integer windowSize = myScanner.nextInt();
        myScanner.nextLine();
        controller.setWindowSize(windowSize);
        //Step 2: User enters the performance dimensions
        boolean hasNextPerformanceDimension = true;
        while (hasNextPerformanceDimension){
            System.out.print("Enter the name of the performance dimension/performance metric (ex:business acumen,soft skills etc.): ");
            String performanceDimension = myScanner.nextLine();
            controller.addDimension(performanceDimension);
            System.out.print("Have you entered all performance dimensions on which your performance is evaluated ? [Type y for yes, n for no]: ");
            String hasEnteredAllPerformanceDimensions = myScanner.nextLine();
            if (hasEnteredAllPerformanceDimensions.equals("y")) hasNextPerformanceDimension = false;
        }
        //Now the user has created all performance dimensions/performance metrics on which their performance is evaluated
        //Step 3: For each performance dimension, the User creates a record which is the performance, performance rating and verbal feedback at
        // end of a given performance evaluation.
        boolean hasNextRecord=true;
        int evaluationNumber=0;
        while (hasNextRecord){
            //Iterate over each performance dimension
            for(String dimension: controller.performanceDimensionsMap.keySet()){
                PerformanceDimension pd=controller.getPerformanceDimension(dimension);
                System.out.print("Enter your performance for "+dimension+ " at the end of performance evaluation " + (evaluationNumber + 1) + ":");
                Integer performance = myScanner.nextInt();
                System.out.print("Enter your performance rating for " +dimension+ " at the end of performance evaluation " + (evaluationNumber + 1) + ":");
                Integer performanceRating = myScanner.nextInt();
                System.out.print("Enter the verbal feedback you received for " +dimension+ " at the end of performance evaluation " + (evaluationNumber + 1) + ":");
                Integer verbalFeedback = myScanner.nextInt();
                controller.addNewRecordToPerformanceDimension(dimension,new Record(performance,performanceRating,verbalFeedback));
                if (pd.cumulativePerformance.size()-1==controller.getWindowEndIndex()){
                    //check current alignment
                    boolean isCurrentDirectionAligned=controller.isDirectionAligned(pd,controller.getWindowStartIndex(),controller.getWindowEndIndex());
                    if(!isCurrentDirectionAligned){
                        Integer currentDirectionPerformance=controller.getDirectionPerformance(pd,controller.getWindowStartIndex(),controller.getWindowEndIndex());
                        Integer currentDirectionPerformanceRating=controller.getDirectionPerformanceRating(pd,controller.getWindowStartIndex(),controller.getWindowEndIndex());
                        Integer currentDirectionVerbalFeedback=controller.getDirectionVerbalFeedback(pd,controller.getWindowStartIndex(),controller.getWindowEndIndex());
                        System.out.println("Current direction for "+ dimension +" is not aligned");
                        System.out.println("Current direction of performance: "+ currentDirectionPerformance);
                        System.out.println("Current direction of performance rating: "+ currentDirectionPerformanceRating);
                        System.out.println("Current direction of verbal feedback: "+ currentDirectionVerbalFeedback);
                        boolean specialCase=controller.isSpecialCaseDetected(currentDirectionPerformance,currentDirectionPerformanceRating,currentDirectionVerbalFeedback);
                        System.out.println(specialCase?"special case detected for "+dimension+" in the last "+controller.getWindowSize()+" " +
                                "performance evaluations":"");
                    }
                    //check overall alignment
                    boolean isOverallDirectionAligned=controller.isDirectionAligned(pd,0,controller.getWindowEndIndex());
                    if(!isOverallDirectionAligned){
                        Integer overallDirectionPerformance=controller.getDirectionPerformance(pd,0,controller.getWindowEndIndex());
                        Integer overallDirectionPerformanceRating=controller.getDirectionPerformanceRating(pd,0,controller.getWindowEndIndex());
                        Integer overallDirectionVerbalFeedback=controller.getDirectionVerbalFeedback(pd,0,controller.getWindowEndIndex());
                        System.out.println("Overall direction for "+ dimension +" is not aligned");
                        System.out.println("Overall direction of performance: "+overallDirectionPerformance);
                        System.out.println("Overall direction of performance rating: "+overallDirectionPerformanceRating);
                        System.out.println("Overall direction of verbal feedback: "+overallDirectionVerbalFeedback);
                        boolean specialCase=controller.isSpecialCaseDetected(overallDirectionPerformance,overallDirectionPerformanceRating,overallDirectionVerbalFeedback);
                        System.out.println(specialCase?"special case detected for "+dimension+" since the initial performance evaluation":"");
                    }
                    //update start and end index
                    controller.setWindowStartIndex(controller.getWindowEndIndex());
                    controller.setWindowEndIndex(controller.getWindowEndIndex()+controller.windowSize-1);
                }
            }
            myScanner.nextLine();
            System.out.print("Will there be more performance evaluations in the future [Type y for yes, n for no]: ");
            String input = myScanner.nextLine();
            if (input.equals("n")) hasNextRecord = false;
            evaluationNumber++;
        }
        myScanner.close();
    }
}