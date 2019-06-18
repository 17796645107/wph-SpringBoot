package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
//物流记录
public class LogMessage implements Serializable {
    private Integer id;

    private Integer logId;//物流id

    private String sendPlace;//出发地

    private String arrivePlace;//目的地

    private Date sendDate;//发出时间

    private Date arriveDate;//到达时间

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getSendPlace() {
        return sendPlace;
    }

    public void setSendPlace(String sendPlace) {
        this.sendPlace = sendPlace == null ? null : sendPlace.trim();
    }

    public String getArrivePlace() {
        return arrivePlace;
    }

    public void setArrivePlace(String arrivePlace) {
        this.arrivePlace = arrivePlace == null ? null : arrivePlace.trim();
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }
}