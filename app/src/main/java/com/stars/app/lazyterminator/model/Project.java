package com.stars.app.lazyterminator.model;

/**
 * Created by Administrator on 2015/9/15.
 */
public class Project {
    private int projectId;
    private String projectName;
    private String projectPriority;

    public Project(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectPriority = "narmal";
    }

    public Project(int projectId, String projectName, String projectPriority) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectPriority = projectPriority;
    }

    public String getProjectPriority() {
        return projectPriority;
    }

    public void setProjectPriority(String projectPriority) {
        this.projectPriority = projectPriority;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectPriority='" + projectPriority + '\'' +
                '}';
    }

    public void renameProject(String newName){
        this.projectName = newName;
    }
}
