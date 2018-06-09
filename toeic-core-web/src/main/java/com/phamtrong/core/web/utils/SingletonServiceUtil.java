package com.phamtrong.core.web.utils;

import com.phamtrong.servicece.impl.*;

public class SingletonServiceUtil {
    private static UserServiceceImpl userServiceceImpl = null;
    private static RoleServiceeImpl roleServiceceImpl = null;
    private static ListenGuidelineServiceceImpl listenGuidelineServiceceImpl = null;
    private static CommentServiceceImpl commentServiceImpl = null;
    private static ExaminationQuestionServiceceImpl examinationQuestionServiceImpl = null;
    private static ExaminationServiceceImpl examinationServiceImpl = null;
    private static ExerciseQuestionServiceceImpl exerciseQuestionServiceImpl = null;
    private static ExerciseServiceceImpl exerciseServiceImpl = null;
    //private static ResultServiceImpl resultServiceImpl = null;

    public static UserServiceceImpl getUserServiceInstance(){
        if(userServiceceImpl==null){
            userServiceceImpl= new UserServiceceImpl();
        }
        return userServiceceImpl;
    }

    public static RoleServiceeImpl getRoleServiceInstance(){
        if(roleServiceceImpl==null){
            roleServiceceImpl= new RoleServiceeImpl();
        }
        return roleServiceceImpl;
    }

    public static ListenGuidelineServiceceImpl getListenGuidelineServiceInstance(){
        if(listenGuidelineServiceceImpl==null){
            listenGuidelineServiceceImpl= new ListenGuidelineServiceceImpl();
        }
        return listenGuidelineServiceceImpl;
    }

    public static CommentServiceceImpl getCommentServiceInstance() {
        if (commentServiceImpl == null) {
            commentServiceImpl = new CommentServiceceImpl();
        }
        return commentServiceImpl;
    }

    public static ExaminationQuestionServiceceImpl getExaminationQuestionServiceInstance() {
        if (examinationQuestionServiceImpl == null) {
            examinationQuestionServiceImpl = new ExaminationQuestionServiceceImpl();
        }
        return examinationQuestionServiceImpl;
    }

    public static ExaminationServiceceImpl getExaminationServiceInstance() {
        if (examinationServiceImpl == null) {
            examinationServiceImpl = new ExaminationServiceceImpl();
        }
        return examinationServiceImpl;
    }

    public static ExerciseQuestionServiceceImpl getExerciseQuestionServiceInstance() {
        if (exerciseQuestionServiceImpl == null) {
            exerciseQuestionServiceImpl = new ExerciseQuestionServiceceImpl();
        }
        return exerciseQuestionServiceImpl;
    }

    public static ExerciseServiceceImpl getExerciseServiceInstance() {
        if (exerciseServiceImpl == null) {
            exerciseServiceImpl = new ExerciseServiceceImpl();
        }
        return exerciseServiceImpl;
    }

//    public static ResultServiceImpl getResultServiceInstance() {
//        if (resultServiceImpl == null) {
//            resultServiceImpl = new ResultServiceImpl();
//        }
//        return resultServiceImpl;
//    }
}
