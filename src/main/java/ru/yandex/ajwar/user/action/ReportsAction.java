package ru.yandex.ajwar.user.action;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.yandex.ajwar.user.model.Reports;
import ru.yandex.ajwar.listener.HibernateListener;
import com.opensymphony.xwork2.ActionSupport;

public class ReportsAction extends ActionSupport{

    private List<Reports> reportsList = new ArrayList<Reports>();
    private List<String> performerList=new ArrayList<String>();
    private String startDate;
    private String endDate;
    private String performer;
    private Long id;

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    public List<String> getPerformerList() {
        return performerList;
    }

    public void setPerformerList(List<String> performerList) {
        this.performerList = performerList;
    }

    public List<Reports> getReportsList() {
        return reportsList;
    }

    public void setReportsList(List<Reports> reportsList) {
        this.reportsList = reportsList;
    }

    public String addReports() throws Exception{

        /**Получение Hibernate session из контекста сервлета*/
        SessionFactory sessionFactory =
                (SessionFactory) ServletActionContext.getServletContext()
                        .getAttribute(HibernateListener.KEY_NAME);
        Query query;
        Session session = sessionFactory.openSession();
        query=session.createQuery("from Reports ");
        //конкретизация данных из формы jsp
        if (!"-1".equals(getPerformer())) {
            query = session.createQuery("from Reports where performer=:man ");
            query.setParameter("man",getPerformer());
        }else if (validationParametr(getStartDate()) && validationParametr(getEndDate())){
            query=session.createQuery("from Reports where startDate>=:start and endDate<=:end");
            query.setParameter("start",stringToDate(getStartDate()));
            query.setParameter("end",stringToDate(getEndDate()));
        }else if (validationParametr(getStartDate())) {
            query=session.createQuery("from Reports where startDate>=:start");
            query.setParameter("start",stringToDate(getStartDate()));
        }else if (validationParametr(getEndDate())) {
            query=session.createQuery("from Reports where endDate<=:end");
            query.setParameter("end",stringToDate(getEndDate()));
        }
        reportsList=query.list();

        return SUCCESS;

    }

    public String listReports() throws Exception{

        /**Получение Hibernate session из контекста сервлета*/
        SessionFactory sessionFactory =
                (SessionFactory) ServletActionContext.getServletContext()
                        .getAttribute(HibernateListener.KEY_NAME);

        Session session = sessionFactory.openSession();
        //отображение выводимых данных из БД в таблицу
        performerList = session.createQuery("select performer from Reports").list();

        return SUCCESS;

    }
    //Перевод русской даты в дату понятную для БД
    private Date stringToDate(String str) throws ParseException{
        SimpleDateFormat format=new SimpleDateFormat("MMM d,yyyy",new Locale("ru"));
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.parse(dateFormat.format(format.parse(str.trim())));
    }
    //Валидность параметров
    private boolean validationParametr(String str){
        if ("".equals(str.trim())) return false;
        else return true;
    }
}