package ra.coursemanagement.business;

import ra.coursemanagement.dao.impl.StatisticImpl;
import ra.coursemanagement.model.entity.StudentInCourse;

import java.util.List;

public class BusinessStatistic {
    private StatisticImpl businessStatistic = new StatisticImpl() ;
    public List<StudentInCourse> getBusinessStatistic() {
        return  businessStatistic.getStudentInCourse();
    }
}
