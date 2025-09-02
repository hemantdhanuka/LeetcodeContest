package PracticeQuestions;

import java.util.PriorityQueue;

public class Q0502 {
    public static void main(String[] args) {
        Q0502 obj = new Q0502();
        System.out.println(obj.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Project> availableProjects = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);

        PriorityQueue<Project> remainingProjects = new PriorityQueue<>((o1, o2) -> o1.minimumCapital - o2.minimumCapital);

        //insert into remaining projects
        for (int i = 0; i < profits.length; i++) {
            remainingProjects.add(new Project(profits[i], capital[i]));
        }

        int projectCompleted = 0;
        int finalProfit = w;
        while (projectCompleted < k) {
            // update available projects

            while (!remainingProjects.isEmpty()) {
                Project project = remainingProjects.peek();

                if (project.minimumCapital <= finalProfit) {
                    remainingProjects.poll();
                    availableProjects.add(project);
                } else {
                    break;
                }
            }

            if (availableProjects.isEmpty()) {
                break;
            }

            Project pickedProject = availableProjects.poll();
            finalProfit += pickedProject.profit;
            projectCompleted++;

        }

        return finalProfit;
    }

    class Project {
        int profit;
        int minimumCapital;

        public Project(int profit, int minimumCapital) {
            this.profit = profit;
            this.minimumCapital = minimumCapital;
        }
    }
}
