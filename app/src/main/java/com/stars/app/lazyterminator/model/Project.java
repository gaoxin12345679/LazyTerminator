package com.stars.app.lazyterminator.model;

/**
 * Created by Administrator on 2015/9/15.
 */
public class Project {
    private String projectName;
    private String projectDesc;
    private int projectPriority;
    private String createDate;

    public Project(){}
    public Project( String projectName, int projectPriority, String projectDesc, String createDate) {
        this.projectName = projectName;
        this.projectPriority = projectPriority;
        this.projectDesc = projectDesc;
        this.createDate = createDate;
    }
    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }
    public int getProjectPriority() {
        return projectPriority;
    }

    public void setProjectPriority(int projectPriority) {
        this.projectPriority = projectPriority;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public void renameProject(String newName){
        this.projectName = newName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                ", projectName='" + projectName + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", projectPriority=" + projectPriority +
                ", createDate=" + createDate +
                '}';
    }
}
