package application;

import javafx.beans.property.SimpleStringProperty;
public class helpIssue {
		 
        private final SimpleStringProperty issue;
        private final SimpleStringProperty solution;
 
       public helpIssue(String issue, String solution) {
            this.issue = new SimpleStringProperty(issue);
            this.solution = new SimpleStringProperty(solution);
        }
       public String getIssue() {
           return issue.get();
       }
       public String getSolution() {
           return solution.get();
       }
}
